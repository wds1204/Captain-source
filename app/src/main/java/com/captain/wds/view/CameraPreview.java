package com.captain.wds.view;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by wds on 2018/4/13.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    public static final String TAG = CameraPreview.class.getSimpleName();

    private SurfaceHolder holder;
    private Camera camera;

    public CameraPreview(Context context,Camera camera) {
        super(context);
        this.camera = camera;
        init(context);
    }

    private void init(Context context) {
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //The Surface has been created, now tell the camera where to draw the previe
        //
        try {
            //准备一个实时的Camera图像预览界面。
            camera.setPreviewDisplay(holder);
            //设置开始预览了
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.
        if (holder.getSurface() == null) {
            // preview surface does not exist
            return;
        }
        try {
            camera.stopPreview();
        } catch (Exception e) {

        }
        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();

        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //手动释放 一定得加！
//        camera.stopPreview();
//        camera.release();
//
//        camera=null;
    }
}
