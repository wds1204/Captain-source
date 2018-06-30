package com.captain.wds;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.LruCache;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.captain.wds.aidl.AIDLService;
import com.captain.wds.lruCache.TestLruCache;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class MainActivity extends FragmentActivity {

    private ServiceConnection conn=new ServiceConnection() {
        @Override public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override public void onServiceDisconnected(ComponentName name) {

        }
    };

    private static class MyHandler extends Handler{
        private WeakReference<MainActivity> weakReference ;


        public MyHandler(MainActivity activity) {
            this.weakReference = new WeakReference<MainActivity>(activity);

        }

        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        doBeforeSetcontentView();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThridActivity.class);
                startActivity(intent);

            }
        });

        MyHandler myHandler = new MyHandler(this);
        Message obtain = Message.obtain();

        myHandler.sendMessage(obtain);
        myHandler.post(new Runnable() {
            @Override public void run() {

            }
        });
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("wds", "1");
        hashMap.put("wds", "1");

        //LruChche的是使用
        LruCache<String, Bitmap> lruCache = TestLruCache.getLruCache();

//        Intent service = new Intent(this, MyService.class);
         /*startService的方式开启服务，第二次开启的时候不会在执行onCreate方法*/
//        startService(service);
//
//        startService(service);

//        startService(service);
         /*bindService方式开启的服务，第二次绑定时候不会在执行onCreate onBind方法*/
//        bindService(service, conn, BIND_AUTO_CREATE);
//        bindService(service, conn, BIND_AUTO_CREATE);


        Intent intent = new Intent(this, AIDLService.class);
        startService(intent);

    }
    /*===========lruccache put操作的源码 写入缓存============*/

    //1.插入元素，并相应增加当前缓存的数量
    //2.调用trimToSize方法开启死循环，不断地删除表头的数据，知道当前的缓存容量小于最大的容量
//    public final V put(K key, V value) {
//        if (key == null || value == null) {
//            throw new NullPointerException("key == null || value == null");
//        }
//
//        V previous;//加锁，线程安全
//        synchronized (this) {
//            putCount++;//插入的数量自增
//            size += safeSizeOf(key, value);//调用safeSizeof方法，并增加缓存数量的大小
//            previous = map.put(key, value);//插入此项
//            if (previous != null) {//previous如果不为空，则说明该项在原来的链表中以及存在，已有缓存大小size恢复到
//                size -= safeSizeOf(key, previous);
//            }
//        }
//
//        if (previous != null) {
//            entryRemoved(false, key, previous, value);
//        }
//
//        trimToSize(maxSize);//调整缓存大小
//        return previous;
//    }
//    public void trimToSize(int maxSize) {
//        while (true) {//开启死循环
//            K key;
//            V value;
//            synchronized (this) {
//                if (size < 0 || (map.isEmpty() && size != 0)) {
//                    throw new IllegalStateException(getClass().getName()
//                            + ".sizeOf() is reporting inconsistent results!");
//                }
//
//                if (size <= maxSize) {//如果缓存未满直接返回
//                    break;
//                }
//
//                Map.Entry<K, V> toEvict = map.eldest();//返回链表表头的数据
//                if (toEvict == null) {
//                    break;
//                }
//
//                key = toEvict.getKey();
//                value = toEvict.getValue();
//                map.remove(key);//删除表头的数据
//                size -= safeSizeOf(key, value);//重新计算缓存大小
//                evictionCount++;//被删除项的数量
//            }
//
//            entryRemoved(true, key, value, null);
//        }
//    }

    /*===========lrucache get操作的源码 读取缓存============*/
    //1.调用LinkedHashMap的get()方法，注意如果该元素存在，且accessOrder为true，这个方法会将该元素移动到表尾.
    //2.当获取不到和key对应的元素时，尝试调用create()方法创建建元素，以下就是创建的过程，和put()方法流程相同。
//    public final V get(K key) {
//        if (key == null) {
//            throw new NullPointerException("key == null");
//        }
//
//        V mapValue;
//        synchronized (this) {
//            //读取该元素，调用LinkedHashMap个get方法，如果该元素存在，则会把该元素移到链表的表尾
//            mapValue = map.get(key);
//            if (mapValue != null) {
//                hitCount++;
//                return mapValue;
//            }
//            missCount++;
//        }
//
//        V createdValue = create(key);//回调create方法， 当获取不到和key对应的元素时，尝试调用create()方法
//        if (createdValue == null) {
//            return null;
//        }
//
//        synchronized (this) {//加锁，安全.下面就和put 的操作流程相同
//            createCount++;
//            mapValue = map.put(key, createdValue);
//
//            if (mapValue != null) {
//                // There was a conflict so undo that last put
//                map.put(key, mapValue);
//            } else {
//                size += safeSizeOf(key, createdValue);
//            }
//        }
//
//        if (mapValue != null) {
//            entryRemoved(false, key, createdValue, mapValue);
//            return mapValue;
//        } else {
//            trimToSize(maxSize);
//            return createdValue;
//        }
//    }
    
    /*===========lrucache remove方法源码 删除缓存============*/
//    public final V remove(K key) {
//        if (key == null) {
//            throw new NullPointerException("key == null");
//        }
//
//        V previous;
//        synchronized (this) {
//            previous = map.remove(key);
//            if (previous != null) {
//                size -= safeSizeOf(key, previous);
//            }
//        }
//
//        if (previous != null) {
//            entryRemoved(false, key, previous, null);
//        }
//
//        return previous;
//    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {

        // 无标题
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // This example uses decor view, but you can use any visible view.

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            // This example uses decor view, but you can use any visible view.
            View decorView = getWindow().getDecorView();
//			int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
//			decorView.setSystemUiVisibility(0);

            // 无标题
//			requestWindowFeature(Window.FEATURE_NO_TITLE);
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    // 开启多个进程带来的问题
    // <p>
    // 1:静态成员和单例模式完全失败
    // 2:线程同步机制失效
    // 3：Application多次创建
    // </p>
    ///////////////////////////////////////////////////////////////////////////


    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
