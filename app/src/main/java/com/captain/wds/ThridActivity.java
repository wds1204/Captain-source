package com.captain.wds;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.captain.wds.view.CameraPreview;

public class ThridActivity extends AppCompatActivity {

	private static final int CAMERA_OK = 1;

	private Camera			camera	= null;

	private FrameLayout		mCameralayout;

	private CameraPreview	mPreview;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thrid);



	}

	// 获取相机
	public  Camera getCameraInstance() {
		Camera c = null;
		try {
			if (Build.VERSION.SDK_INT > 22) {
				if (ContextCompat.checkSelfPermission(ThridActivity.this,
						android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(ThridActivity.this,
							new String[]{android.Manifest.permission.CAMERA},CAMERA_OK);
				} else {
					c = Camera.open();
				}

			} else {
				c = Camera.open();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case CAMERA_OK:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					//这里已经获取到了摄像头的权限，想干嘛干嘛了可以
					getCameraInstance();
				} else {
					//这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以。
					Toast.makeText(ThridActivity.this, "请手动打开相机权限", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
		}
	}


	// 判断相机是否支持
	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			return true;
		} else {
			return false;
		}
	}

	@Override protected void onResume() {
		super.onResume();
		if (camera == null) {
			camera = getCameraInstance();
		}
		// 必须放在onResume中，不然会出现Home键之后，再回到该APP，黑屏
		CameraPreview preview = new CameraPreview(this, camera);
		mCameralayout = findViewById(R.id.fl);
		mCameralayout.addView(preview);
	}

	@Override protected void onPause() {
		super.onPause();
		camera.release();
		camera = null;
	}

	@Override protected void onStop() {
		super.onStop();
		camera.stopPreview();
		camera.release();
		camera = null;
	}

	@Override protected void onDestroy() {
		super.onDestroy();
		camera.stopPreview();
		camera.release();
		camera = null;
	}
}
