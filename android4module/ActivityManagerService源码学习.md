#### ActivityManagerService

<img src="https://img-blog.csdn.net/20161107165725175"/>

在学习SystemServer的我们知道通过SystemServerManager启动ActivityManagerServer等服务.
在此之前有一个准备过程
````
// Initialize the system context.
createSystemContext(); //创建Context保存在mSystemContext中
// Create the system service manager.
//使用刚刚创建的mSystemContext创建SystemServiceManager
mSystemServiceManager = new SystemServiceManager(mSystemContext);
mSystemServiceManager.setRuntimeRestarted(mRuntimeRestart);
LocalServices.addService(SystemServiceManager.class, mSystemServiceManager);
````


这里最关键的方法是createSystemContext
````
   private void createSystemContext() {
        //调用ActivityThread的systemMain函数，其中会创建出系统对应的Context对象
        ActivityThread activityThread = ActivityThread.systemMain();
        //取出上面函数创建的Context对象，保存在mSystemContext中
        mSystemContext = activityThread.getSystemContext();
        mSystemContext.setTheme(DEFAULT_SYSTEM_THEME);//设置系统主题

        final Context systemUiContext = activityThread.getSystemUiContext();
        systemUiContext.setTheme(DEFAULT_SYSTEM_THEME);
    }
 ````
 再次进入到ActivityThread的systemMain方法中

````
public static ActivityThread systemMain() {
        // The system process on low-memory devices do not get to use hardware
        // accelerated drawing, since this can add too much overhead to the
        // process.
        if (!ActivityManager.isHighEndGfx()) {
            //关闭系统渲染
            ThreadedRenderer.disable(true);
        } else {
            ThreadedRenderer.enableForegroundTrimming();
        }
        //创建ActivityThread
        ActivityThread thread = new ActivityThread();
        //调用attach方法
        thread.attach(true);
        return thread;
    }
````
创建ActivityThread对象，调用其attache方法
````
private void attach(boolean system) {
        sCurrentActivityThread = this;
        mSystemThread = system;
        if (!system) {
            //...
        } else {
            // Don't set application object here -- if the system crashes,
            // we can't display an alert, we just want to die die die.
            android.ddm.DdmHandleAppName.setAppName("system_process",
                    UserHandle.myUserId());
            try {
                mInstrumentation = new Instrumentation();
                ContextImpl context = ContextImpl.createAppContext(
                        this, getSystemContext().mPackageInfo);
                mInitialApplication = context.mPackageInfo.makeApplication(true, null);
                mInitialApplication.onCreate();
            } catch (Exception e) {
                throw new RuntimeException(
                        "Unable to instantiate Application():" + e.toString(), e);
            }
        }

        // add dropbox logging to libcore
        DropBox.setReporter(new DropBoxReporter());

        ViewRootImpl.ConfigChangedCallback configChangedCallback
                = (Configuration globalConfig) -> {
            synchronized (mResourcesManager) {
                // We need to apply this change to the resources immediately, because upon returning
                // the view hierarchy will be informed about it.
                if (mResourcesManager.applyConfigurationToResourcesLocked(globalConfig,
                        null /* compat */)) {
                    updateLocaleListFromAppContext(mInitialApplication.getApplicationContext(),
                            mResourcesManager.getConfiguration().getLocales());

                    // This actually changed the resources! Tell everyone about it.
                    if (mPendingConfiguration == null
                            || mPendingConfiguration.isOtherSeqNewer(globalConfig)) {
                        mPendingConfiguration = globalConfig;
                        sendMessage(H.CONFIGURATION_CHANGED, globalConfig);
                    }
                }
            }
        };
        ViewRootImpl.addConfigCallback(configChangedCallback);
    }
````
在attach中主要工作是创建Instrumentataion、Application和Context
在ActivityThread中几个关键的成员变量
````
定义AMS与应用程序通信的接口
final ApplicationThread mAppThread = new ApplicationThread();
