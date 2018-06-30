package com.captain.wds.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.captain.wds.IMyAidlInterface;

/**
 * Created by wudongsheng on 2018/4/24.
 */

public class AIDLService extends Service {
    @Override public void onCreate() {
        Log.e("TAG", "onCreate");
        super.onCreate();
    }

    @Nullable @Override public IBinder onBind(Intent intent) {
        Log.e("TAG", "onBind");
        return mBinder;
    }

    @Override public void onRebind(Intent intent) {
        Log.e("TAG", "onRebind");
        super.onRebind(intent);
    }

    @Override public void unbindService(ServiceConnection conn) {
        Log.e("TAG", "unbindService");
        super.unbindService(conn);
    }

    @Override public boolean onUnbind(Intent intent) {
        Log.e("TAG", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override public void onDestroy() {
        Log.e("TAG", "onDestroy");
        super.onDestroy();
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {

        @Override public int add(int x, int y) throws RemoteException {
            return x + y;
        }

        @Override public int min(int x, int y) throws RemoteException {
            return Math.min(x, y);
        }
    };

}
