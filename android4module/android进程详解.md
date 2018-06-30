###ZygoteInit启动流程

Android系统的Zygote进程是所有的Android进程的父进程，包括SystemServer和各种应用进程都通过Zygote进程fork出来。
Zygote相当于Android系统的跟进程，而Zygote进程则是通过Linux系统的init进程启动的。

Init进程——》Zygote进程——》systemServer进程——》各种应用进程


**Zygote进程的启动流程**

````java
public static void main(String argv[]) {
    ZygoteServer zygoteServer = new ZygoteServer();

    // Mark zygote start. This ensures that thread creation will throw
    // an error.
    ZygoteHooks.startZygoteNoThreadCreation();

    // Zygote goes into its own process group.
    try {
        Os.setpgid(0, 0);
    } catch (ErrnoException ex) {
        throw new RuntimeException("Failed to setpgid(0,0)", ex);
    }

    try {
        // Report Zygote start time to tron unless it is a runtime restart
        if (!"1".equals(SystemProperties.get("sys.boot_completed"))) {
            MetricsLogger.histogram(null, "boot_zygote_init",
                    (int) SystemClock.elapsedRealtime());
        }

        String bootTimeTag = Process.is64Bit() ? "Zygote64Timing" : "Zygote32Timing";
        BootTimingsTraceLog bootTimingsTraceLog = new BootTimingsTraceLog(bootTimeTag,
                Trace.TRACE_TAG_DALVIK);
        bootTimingsTraceLog.traceBegin("ZygoteInit");
        RuntimeInit.enableDdms();//调用enableDdms,设置DDMS启动
        // Start profiling the zygote initialization.
        SamplingProfilerIntegration.start();

        boolean startSystemServer = false;
        String socketName = "zygote";
        String abiList = null;
        boolean enableLazyPreload = false;
        //解析main方法的参数获取是否要启动SystemServer进程，获取abi列表，获取socket连接名称
        for (int i = 1; i < argv.length; i++) {
            if ("start-system-server".equals(argv[i])) {
                startSystemServer = true;
            } else if ("--enable-lazy-preload".equals(argv[i])) {
                enableLazyPreload = true;
            } else if (argv[i].startsWith(ABI_LIST_ARG)) {
                abiList = argv[i].substring(ABI_LIST_ARG.length());
            } else if (argv[i].startsWith(SOCKET_NAME_ARG)) {
                socketName = argv[i].substring(SOCKET_NAME_ARG.length());
            } else {
                throw new RuntimeException("Unknown command line argument: " + argv[i]);
            }
        }

        if (abiList == null) {
            throw new RuntimeException("No ABI list supplied.");
        }
        //调用registerServerSocket为Zygote注册socket
        zygoteServer.registerServerSocket(socketName);
        // In some configurations, we avoid preloading resources and classes eagerly.
        // In such cases, we will preload things prior to our first fork.
        if (!enableLazyPreload) {
            bootTimingsTraceLog.traceBegin("ZygotePreload");
            EventLog.writeEvent(LOG_BOOT_PROGRESS_PRELOAD_START,
                SystemClock.uptimeMillis());
            //调用系统的preload,初始化系统内容
            preload(bootTimingsTraceLog);
            EventLog.writeEvent(LOG_BOOT_PROGRESS_PRELOAD_END,
                SystemClock.uptimeMillis());
            bootTimingsTraceLog.traceEnd(); // ZygotePreload
        } else {
            Zygote.resetNicePriority();
        }

        // Finish profiling the zygote initialization.
        SamplingProfilerIntegration.writeZygoteSnapshot();

        // Do an initial gc to clean up after startup
        bootTimingsTraceLog.traceBegin("PostZygoteInitGC");
        gcAndFinalize();
        bootTimingsTraceLog.traceEnd(); // PostZygoteInitGC

        bootTimingsTraceLog.traceEnd(); // ZygoteInit
        // Disable tracing so that forked processes do not inherit stale tracing tags from
        // Zygote.
        Trace.setTracingEnabled(false);

        // Zygote process unmounts root storage spaces.
        Zygote.nativeUnmountStorageOnInit();

        // Set seccomp policy
        Seccomp.setPolicy();

        ZygoteHooks.stopZygoteNoThreadCreation();

        if (startSystemServer) {
            //调用startSystemServer，fork出SystemServer
            startSystemServer(abiList, socketName, zygoteServer);
        }

        Log.i(TAG, "Accepting command socket connections");
        zygoteServer.runSelectLoop(abiList);

        zygoteServer.closeServerSocket();
    } catch (Zygote.MethodAndArgsCaller caller) {
        caller.run();
    } catch (Throwable ex) {
        Log.e(TAG, "System zygote died with exception", ex);
        zygoteServer.closeServerSocket();
        throw ex;
    }
}
````
* 调用enableDdms,设置DDMS启动
* 解析main方法的参数获取是否要启动SystemServer进程，获取abi列表，获取socket连接名称
* 调用registerServerSocket为Zygote注册socket

````java
void registerServerSocket(String socketName) {
        if (mServerSocket == null) {
            int fileDesc;
            final String fullSocketName = ANDROID_SOCKET_PREFIX + socketName;
            try {
                String env = System.getenv(fullSocketName);
                fileDesc = Integer.parseInt(env);
            } catch (RuntimeException ex) {
                throw new RuntimeException(fullSocketName + " unset or invalid", ex);
            }

            try {
                FileDescriptor fd = new FileDescriptor();
                fd.setInt$(fileDesc);
                mServerSocket = new LocalServerSocket(fd);
            } catch (IOException ex) {
                throw new RuntimeException(
                        "Error binding to local socket '" + fileDesc + "'", ex);
            }
        }
    }
   ````
  * 接着调用系统方法preLoad()
  ````java
  static void preload(BootTimingsTraceLog bootTimingsTraceLog) {
          Log.d(TAG, "begin preload");
          bootTimingsTraceLog.traceBegin("BeginIcuCachePinning");
          beginIcuCachePinning();
          bootTimingsTraceLog.traceEnd(); // BeginIcuCachePinning
          bootTimingsTraceLog.traceBegin("PreloadClasses");
          preloadClasses();//用于初始化Zygote中需要的class类
          bootTimingsTraceLog.traceEnd(); // PreloadClasses
          bootTimingsTraceLog.traceBegin("PreloadResources");
          preloadResources();//用于初始化系统资源
          bootTimingsTraceLog.traceEnd(); // PreloadResources
          Trace.traceBegin(Trace.TRACE_TAG_DALVIK, "PreloadOpenGL");
          preloadOpenGL();//用于初始化OpenGL
          Trace.traceEnd(Trace.TRACE_TAG_DALVIK);
          preloadSharedLibraries();//用于初始化系统libraries
          preloadTextResources();//用于初始化文字资源
          // Ask the WebViewFactory to do any initialization that must run in the zygote process,
          // for memory sharing purposes.
          WebViewFactory.prepareWebViewInZygote();//用于初始化webview
          endIcuCachePinning();
          warmUpJcaProviders();
          Log.d(TAG, "end preload");
  
          sPreloadComplete = true;
      }
````
* 调用startSystemServer(abiList, socketName, zygoteServer);


###SystemServer进程

SystemServer的主要作用是启动各种系统服务，比如ActivityManagerServer、PackageManagerServer和WindowManagerServer等服务.

startBootstrapServices()//主要用于启动系统boot级服务
   * 创建服务类Install类(系统安装apk时的服务类)
   * 启动ActivityManagerService服务(四大组件的启动、切换、调度)
startCoreServices()//主要用于启动系统核心的服务
startOtherServices()//启动其他服务
    * 启动LauncherAppsService

SystemServer————》SystemServerManager————》SystemServerManager.startServer

###Launcher启动流程
Launcher程序就是我们平时看到的桌面程序，它其实也是一个android应用程序，
只不过这个应用程序是系统默认第一个启动的应用程序，这里我们就简单的分析一下Launcher应用的启动流程。
