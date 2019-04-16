### android四大组件

四大组件他们分别是Activity、Sevice、BroadcastReceiver和ContentProvider


### Activity的工作过程
我们从Activity的startActivity方法开始分析，startActivity有很多重载方法，但最终还是会调用
1) startActivityForResult方法，resqusetCode=-1
2) Instrumentation.excuStartActivity()
   *  ActivityManagerService.startActivity()
   *  CheckStartActivity()-检查activity的启动结果，我们常见的"Unable to find explicit activity class，have you declared this activity in your AndroidManifest.xml?"
3) AMS.startActivity()->startActivityAsUser()->startActivityLocked():
4) 发送请求后，把intent和flag相关的信息交付给ActivityStarter,然后再交给ActivityStackSupervisior/ActivityStack处理Activity进栈相关流程。
5) 最后会在ActivityStackSupervisior中调用realStartActivityLocked方法,其中有一段如下代码
````java
    //app.thread的类型为IApplicationThread，它的实现类就是ActivityThread中的内部类ApplicationThread
    app.thread.scheduleLaunchActivity(new Intent(r.intent), r.appToken,
                     System.identityHashCode(r), r.info,
                     // TODO: Have this take the merged configuration instead of separate global and
                     // override configs.
                     mergedConfiguration.getGlobalConfiguration(),
                     mergedConfiguration.getOverrideConfiguration(), r.compat,
                     r.launchedFromPackage, task.voiceInteractor, app.repProcState, r.icicle,
                     r.persistentState, results, newIntents, !andResume,
                     mService.isNextTransitionForward(), profilerInfo);
````
6) Activity的启动回到了ActivitynThread，通过调用scheduleLaunchActivity方法启动Activity
````java
@Override
        public final void scheduleLaunchActivity(Intent intent, IBinder token, int ident,
                ActivityInfo info, Configuration curConfig, Configuration overrideConfig,
                CompatibilityInfo compatInfo, String referrer, IVoiceInteractor voiceInteractor,
                int procState, Bundle state, PersistableBundle persistentState,
                List<ResultInfo> pendingResults, List<ReferrerIntent> pendingNewIntents,
                boolean notResumed, boolean isForward, ProfilerInfo profilerInfo) {

            updateProcessState(procState, false);

            ActivityClientRecord r = new ActivityClientRecord();

            r.token = token;
            r.ident = ident;
            r.intent = intent;
            r.referrer = referrer;
            r.voiceInteractor = voiceInteractor;
            r.activityInfo = info;
            r.compatInfo = compatInfo;
            r.state = state;
            r.persistentState = persistentState;

            r.pendingResults = pendingResults;
            r.pendingIntents = pendingNewIntents;

            r.startsNotResumed = notResumed;
            r.isForward = isForward;

            r.profilerInfo = profilerInfo;

            r.overrideConfig = overrideConfig;
            updatePendingConfiguration(curConfig);

            sendMessage(H.LAUNCH_ACTIVITY, r);
        }
````
7) 发送一个Activity的消息交给Handler处理，利用ClassLoader去加载Activity、创建Activity实例，并回调Activity的onCreate()方法。这样便完成了Activity的启动。

````java
public void handleMessage(Message msg) {
            if (DEBUG_MESSAGES) Slog.v(TAG, ">>> handling: " + codeToString(msg.what));
            switch (msg.what) {
                case LAUNCH_ACTIVITY: {
                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityStart");
                    final ActivityClientRecord r = (ActivityClientRecord) msg.obj;

                    r.packageInfo = getPackageInfoNoCheck(
                            r.activityInfo.applicationInfo, r.compatInfo);
                    handleLaunchActivity(r, null, "LAUNCH_ACTIVITY");
                    Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
                } break;
                //....
            }
}

 private void handleLaunchActivity(ActivityClientRecord r, Intent customIntent, String reason) {
        // If we are getting ready to gc after going to the background, well
        // we are back active so skip it.
        unscheduleGcIdler();
        mSomeActivitiesChanged = true;

        if (r.profilerInfo != null) {
            mProfiler.setProfiler(r.profilerInfo);
            mProfiler.startProfiling();
        }

        // Make sure we are running with the most recent config.
        handleConfigurationChanged(null, null);

        if (localLOGV) Slog.v(
            TAG, "Handling launch of " + r);

        // Initialize before creating the activity
        WindowManagerGlobal.initialize();

        Activity a = performLaunchActivity(r, customIntent);

        if (a != null) {
            r.createdConfig = new Configuration(mConfiguration);
            reportSizeConfigurations(r);
            Bundle oldState = r.state;
            handleResumeActivity(r.token, false, r.isForward,
                    !r.activity.mFinished && !r.startsNotResumed, r.lastProcessedSeq, reason);

          //....
        } 
        //...
    }
````



###ContentProvider的工作过程


是一种内容共享型组件，它通过binder向其他组件乃至其他应用提供数据。

当ContentProvider所在的进程启动时，ContentProvider会同时启动并被发布到AMS中。
ContentProvider的onCreat要优先于Application的onCreate执行


ContentProvider 提供增删改查四个接口，这四个方法都是通过Binder来调用的，外界无法直接访问ContentProvider，
它只能通过AMS根据Uri来获取对应的ContentProvider的Binder接口IContentProvider，然后再通过IContentProvider
来访问ContentProvider中的数据源。

ActivityThread的main方法中，首先会创建ActivityThread实例并调用attach方法，在attach方法中会将ApplicationThread对象通过AMS的attachApplication方法跨进程传递给AMS，最终在AMS中完成ContentProvide的创建，通过各种方法的透传，有调用了ApplicationThread的bindApplication（这也是夸进程），在bindApplication方法中发送一个消息给mH,接到消息后调用ActivityThread的handleBindApplication方法。在handleBindApplication中完成Application的创建和ContentPrivate的创建
            
