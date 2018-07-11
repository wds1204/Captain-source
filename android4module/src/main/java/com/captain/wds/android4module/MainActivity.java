package com.captain.wds.android4module;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(this, SecActivity.class);
		startActivity(intent);
		Log.e("TAG", "onCreate :" + savedInstanceState);
	}

	@Override protected void onStart() {
		Log.e("TAG", "onStart");
		super.onStart();
	}

	@Override protected void onResume() {
		Log.e("TAG", "onResume");
		super.onResume();
	}

	@Override protected void onPause() {
		Log.e("TAG", "onPause");
		super.onPause();
	}

	@Override protected void onStop() {
		Log.e("TAG", "onStop");
		super.onStop();
	}

	@Override protected void onDestroy() {
		Log.e("TAG", "onDestroy");
		super.onDestroy();
	}

	@Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}
}
