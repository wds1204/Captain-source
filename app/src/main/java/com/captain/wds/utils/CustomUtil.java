package com.captain.wds.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Build;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.renderscript.Type;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;

/**
 * Created by wangzhi on 2015/12/4.
 */
public class CustomUtil {

  private static final String TAG = CustomUtil.class.getSimpleName();

  private static CustomUtil sInstance = null;
  private Context mContext;
  RenderScript mRenderScript;
  ScriptIntrinsicYuvToRGB mIntrinsicYuvToRGB;

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
  private CustomUtil(Context context) {
    mContext = context;
    mRenderScript = RenderScript.create(context);
    mIntrinsicYuvToRGB = ScriptIntrinsicYuvToRGB.create(mRenderScript, Element.U8_4(mRenderScript));
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
  public static CustomUtil getInstance(Context context) {
    if (sInstance == null) {
      sInstance = new CustomUtil(context);
    }
    return sInstance;
  }

  public static String getString(Context context, int resId) {
    return context.getString(resId);
  }

  public static boolean isLandScape(Context context) {
    Configuration mConfiguration = context.getResources().getConfiguration(); //获取设置的配置信息
    int ori = mConfiguration.orientation; //获取屏幕方向

    if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
      //横屏
      return true;
    } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
      //竖屏
      return false;
    }
    return false;
  }

  /**
   * 工具方法 base64编码
   *
   * @param bitmap
   * @return
   */
  public static String bitmapToBase64(Bitmap bitmap) {
    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bStream);
    return Base64.encodeToString(bStream.toByteArray(), 0);
  }

  /**
   * 采集照片
   *
   * @param data
   * @return
   */
  public Bitmap collectBitmap(byte[] data, Camera camera) {
    Bitmap collectBitmap = null;
    Camera.Parameters parameters = camera.getParameters();
    int imageFormat = parameters.getPreviewFormat();
    if (imageFormat == ImageFormat.NV21) {
      int w = parameters.getPreviewSize().width;
      int h = parameters.getPreviewSize().height;
      Rect rect = new Rect(0, 0, w, h);
      YuvImage img = new YuvImage(data, ImageFormat.NV21, w, h, null);
      ByteArrayOutputStream os = new ByteArrayOutputStream(
          data.length);
      if (!img.compressToJpeg(new Rect(0, 0, w, h), 100, os)) {
        return null;
      }
      File dirFile = new File(
          Environment.getExternalStorageDirectory() + "/DCIM/");
      if (!dirFile.exists()) {
        dirFile.mkdir();
      }
      byte[] tmp = os.toByteArray();
      Bitmap bmp = BitmapFactory.decodeByteArray(tmp, 0, tmp.length);
      Bitmap bitmap1 = getPicOrientation(bmp, 90, 0);
      collectBitmap = bitmap1;
    }
    return collectBitmap;
  }

  /**
   * 采集照片
   *
   * @param data
   * @return
   */
  public Bitmap collectBitmap(byte[] data, int width, int height, int orientation, int cameraId) {
    Logs.i(TAG, "orientation:" + orientation);
//        orientation = 0;

    Bitmap collectBitmap = null;
    Rect rect = new Rect(0, 0, width, height);
    YuvImage img = new YuvImage(data, ImageFormat.NV21, width, height, null);
    ByteArrayOutputStream os = new ByteArrayOutputStream(
        data.length);
    if (!img.compressToJpeg(new Rect(0, 0, width, height), 100, os)) {
      return null;
    }
    byte[] tmp = os.toByteArray();
    Bitmap bmp = BitmapFactory.decodeByteArray(tmp, 0, tmp.length);
    Bitmap bitmap1 = getPicOrientation(bmp, orientation, cameraId);
    collectBitmap = bitmap1;
    return collectBitmap;
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
  public Bitmap rsYuvToRgb(byte[] data, int width, int height, int orientation, int cameraId) {
    Type.Builder yuvType = new Type.Builder(mRenderScript, Element.U8(mRenderScript)).setX(data.length);
    Allocation in = Allocation.createTyped(mRenderScript, yuvType.create(), Allocation.USAGE_SCRIPT);

    Type.Builder rgbaType = new Type.Builder(mRenderScript, Element.RGBA_8888(mRenderScript)).setX(width).setY(height);
    Allocation out = Allocation.createTyped(mRenderScript, rgbaType.create(), Allocation.USAGE_SCRIPT);

    in.copyFrom(data);

    mIntrinsicYuvToRGB.setInput(in);
    mIntrinsicYuvToRGB.forEach(out);

    Bitmap temp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    out.copyTo(temp);

    Bitmap outBitmap = getPicOrientation(temp, orientation, cameraId);
    return outBitmap;
  }

  public void writeBitmapToDisk(Bitmap bitmap, String filePath) {
    FileUtils.makeDirs(filePath);
    FileOutputStream out = null;
    try {
      out = new FileOutputStream(filePath);
      bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private Bitmap getPicOrientation(Bitmap bmp, int orientation, int cameraId) {
    Camera.CameraInfo info =
        new Camera.CameraInfo();
    Camera.getCameraInfo(cameraId, info);

    Log.i(TAG, "orientation:" + info.orientation);
    Matrix matrix = new Matrix();
    if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
      switch (orientation) {
        case 90:
          matrix.preRotate(-90f);
          break;
        case 0:
          break;
        case 270:
          matrix.preRotate(270f);
          break;
        case 180:
          matrix.preRotate(180f);
          break;
      }
    } else {
      switch (orientation) {
        case 90:
          matrix.preRotate(90f);
          break;
        case 0:
          break;
        case 270:
          matrix.preRotate(270f);
          break;
        case 180:
          matrix.preRotate(180f);
          break;
      }
    }

    // 旋转图片

    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(),
        matrix, true);
    return bmp;
  }
}
