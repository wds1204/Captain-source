package com.captain.wds.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wudongsheng on 2018/4/21.
 */

public class MyService extends Service {
    @Override public void onCreate() {
        Log.e("TAG", "onCreate");
        super.onCreate();
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable @Override public IBinder onBind(Intent intent) {
        Log.e("TAG", "onBind");
        return null;
    }

    @Override public boolean onUnbind(Intent intent) {
        Log.e("TAG", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override public void onDestroy() {
        Log.e("TAG", "onDestroy");
        super.onDestroy();
    }
}
