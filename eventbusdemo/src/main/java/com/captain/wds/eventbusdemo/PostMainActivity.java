package com.captain.wds.eventbusdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PostMainActivity extends AppCompatActivity {
    public static Intent getIntent(Context context) {
        return new Intent(context, PostMainActivity.class);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_main);
    }
}
