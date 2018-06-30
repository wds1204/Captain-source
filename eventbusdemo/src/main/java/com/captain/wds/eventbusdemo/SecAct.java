package com.captain.wds.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.captain.wds.eventbusdemo.event.MessageEvent;
import com.captain.wds.eventbusdemo.event.PostMessageEvent;

import org.greenrobot.eventbus.EventBus;

public class SecAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.msg = "发送一个消息";
                EventBus.getDefault().post(messageEvent);
                finish();
            }
        });
        findViewById(R.id.bt_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostMessageEvent messageEvent = new PostMessageEvent();
                messageEvent.msg = "发送一个消息";
                EventBus.getDefault().postSticky(messageEvent);
            }
        });
    }
}
