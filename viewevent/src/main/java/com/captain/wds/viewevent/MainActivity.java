package com.captain.wds.viewevent;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.captain.wds.viewevent.scroller.MyViewGroup;

public class MainActivity extends AppCompatActivity {

    private MyViewGroup my_view_group;
    private String TAG = getClass().getName().toString();
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        layoutInflater = getLayoutInflater();
        my_view_group = findViewById(R.id.my_view_group);
        int screenWidth = getScreenSize(this)[0];
        int screenHeight = getScreenSize(this)[1];

        for (int i = 0; i < 3; i++) {
            View view = layoutInflater.inflate(R.layout.content_layout, my_view_group, false);
            view.getLayoutParams().height = screenHeight;
            view.getLayoutParams().width = screenWidth;
            TextView tv = view.findViewById(R.id.tv);
            tv.setText("page " + (i + 1));
            my_view_group.addView(view);

        }
    }

    public  int[] getScreenSize(Context activity) {
        // 获取屏幕密度（方法2）
        DisplayMetrics dm = new DisplayMetrics();
        Log.d(TAG, "activity" + activity);
        dm = activity.getResources().getDisplayMetrics();
        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
        int[] screens = {screenWidth, screenHeight};
        return screens;
    }
}
