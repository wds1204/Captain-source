package com.captain.wds.degsinpattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){

        }
    };

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        ListView listView = new ListView(this);
        MyListAdapter adapter = new MyListAdapter();

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        new AlertDialog.Builder(this)
                .setTitle("")
                .setView(new TextView(MainActivity.this))
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", null)
                .show();

        WindowManager windowManager = getWindowManager();


        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
