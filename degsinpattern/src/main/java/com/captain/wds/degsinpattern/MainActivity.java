package com.captain.wds.degsinpattern;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.captain.wds.degsinpattern.contextState.LoginContext;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){

        }
    };

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button forward = findViewById(R.id.forward);
        HashMap<String, String> hashMap = new HashMap<>();
        String put = hashMap.put("WDS", "wds");
//        ListView listView = new ListView(this);
//        MyListAdapter adapter = new MyListAdapter();
//
//        listView.setAdapter(adapter);
//
//        adapter.notifyDataSetChanged();
//        new AlertDialog.Builder(this)
//                .setTitle("")
//                .setView(new TextView(MainActivity.this))
//                .setPositiveButton("确定", (dialog, which) -> {
//
//                })
//                .setNegativeButton("取消", null)
//                .show();
//
//        WindowManager windowManager = getWindowManager();
//
//
//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

    }

    public void forward(View view) {
        LoginContext.getLoginContext().forward(this);
    }

    public void comment(View view) {

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
