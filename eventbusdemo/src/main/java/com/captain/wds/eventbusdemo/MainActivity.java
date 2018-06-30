package com.captain.wds.eventbusdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.captain.wds.IMyAidlInterface;
import com.captain.wds.eventbusdemo.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;
    private TextView tv;
    private ServiceConnection conn=new ServiceConnection() {
        @Override public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        EventBus.getDefault().register(this);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecAct.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt_bind).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.captain.wds.IMyAidlInterface");
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.bt_unbind).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (conn != null) {
                    unbindService(conn);
                }
            }
        });
        findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (iMyAidlInterface != null) {
                    try {
                        int add = iMyAidlInterface.add(12, 24);
                        tv.setText(String.valueOf(add));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "服务被杀死，重新绑定", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.bt_min).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (iMyAidlInterface != null) {
                    try {
                        int min = iMyAidlInterface.min(12, 24);
                        tv.setText(String.valueOf(min));

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "服务被杀死，重新绑定", Toast.LENGTH_SHORT).show();

                }
            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(conn!=null) {
            unbindService(conn);
        }
    }
    @Subscribe(threadMode = ThreadMode.POSTING )
    public void onEvent(MessageEvent messageEvent) {
        Toast.makeText(MainActivity.this, "收到消息：" + messageEvent.msg, Toast.LENGTH_SHORT).show();
        tv.setText(messageEvent.msg);

    }
//    @Subscribe(threadMode = ThreadMode.POSTING )
//    public void onEvent1(MessageEvent messageEvent) {
//        Toast.makeText(MainActivity.this, "收到消息：" + messageEvent.msg+"WDS", Toast.LENGTH_SHORT).show();
//        tv.setText(messageEvent.msg+"WDS");
//
//    }
}
