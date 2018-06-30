package com.captain.wds.leakcanary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cat cat = new Cat();
        Box box = new Box();
        box.hiddenCat = cat;

        Docker.containter = box;


        RefWatcher refWatcher = RefWatcher.DISABLED;

        refWatcher.watch(cat);
//        startActivity(new Intent(this,));
//
//        setResult();
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
