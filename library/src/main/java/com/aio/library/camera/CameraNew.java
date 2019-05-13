package com.aio.library.camera;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import com.aio.library.listener.OnCameraSupport;

/**
 * Created by aio-synergy on 4/14/17.
 */

public class CameraNew implements OnCameraSupport {

    private CameraDevice camera;
    private CameraManager manager;
    private Context mContext;
    private CameraFacingType cameraFacingType = CameraFacingType.NOT_SUPPORT;
    private boolean isRelease = false;
    private String TAG = CameraNew.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CameraNew(final Context context) {
        mContext = context;
        this.manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    public CameraDevice getCamera() {
        return camera;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public OnCameraSupport open(final int cameraId) {
        Log.i(TAG, "open");
        try {
            String[] cameraIds = manager.getCameraIdList();
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                manager.openCamera(cameraIds[cameraId], new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(CameraDevice camera) {
                        Log.i(TAG, "onOpened");
                        CameraNew.this.camera = camera;
                        if (isRelease) {
                            releaseCamera();
                        }
                    }

                    @Override
                    public void onDisconnected(CameraDevice camera) {
                        Log.i(TAG, "onDisconnected");
                        CameraNew.this.camera = camera;
                        releaseCamera();
                        // TODO handle
                    }

                    @Override
                    public void onError(CameraDevice camera, int error) {
                        Log.i(TAG, "onError");
                        Log.i(TAG, "onError:" + error);
                        CameraNew.this.camera = camera;
                        releaseCamera();
                        // TODO handle
                    }
                }, null);
            }
        } catch (Exception e) {
            // TODO handle
            e.fillInStackTrace();
        }
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getOrientation(final int cameraId) {
        try {
            String[] cameraIds = manager.getCameraIdList();
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraIds[cameraId]);
            return characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        } catch (CameraAccessException e) {
            // TODO handle
            return 0;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public CameraFacingType getLensFacing(int cameraId) {
        try {
            String[] cameraIds = manager.getCameraIdList();
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraIds[cameraId]);
            if (characteristics.get(CameraCharacteristics.LENS_FACING) == CameraMetadata.LENS_FACING_FRONT) {
                cameraFacingType = CameraFacingType.FRONT;
            } else if (characteristics.get(CameraCharacteristics.LENS_FACING) == CameraMetadata.LENS_FACING_BACK) {
                cameraFacingType = CameraFacingType.BACK;
            }
        } catch (CameraAccessException | NullPointerException e) {
            e.printStackTrace();
        }
        return cameraFacingType;
    }


    /* if null then the camera not support*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getBackCameraId() {
        try {
            String[] cameraIds = manager.getCameraIdList();
            for (int i = 0; i < cameraIds.length; i++) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraIds[i]);
                if (characteristics.get(CameraCharacteristics.LENS_FACING) == CameraMetadata.LENS_FACING_BACK) {
                    return i;
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /* if null then the camera not support*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getFrontCameraId() {
        try {
            String[] cameraIds = manager.getCameraIdList();
            for (int i = 0; i < cameraIds.length; i++) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraIds[i]);
                if (characteristics.get(CameraCharacteristics.LENS_FACING) == CameraMetadata.LENS_FACING_FRONT) {
                    return i;
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public CameraDevice getNewCameraDevice() {
        return camera;
    }

    @Override
    public Camera getOldCameraDevice() {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void releaseCamera() {
        isRelease = true;
        try {
            if (camera != null) {
                camera.close();
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
    }
}
