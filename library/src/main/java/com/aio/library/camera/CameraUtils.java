package com.aio.library.camera;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import com.aio.library.listener.OnCameraSupport;

/**
 * QuickBlox team
 */
public class CameraUtils {

    private static final String TAG = CameraUtils.class.getSimpleName();

    public static Camera.CameraInfo getCameraInfo(int deviceId) {

        Camera.CameraInfo info = null;

        try {
            info = new Camera.CameraInfo();
            Camera.getCameraInfo(deviceId, info);
        } catch (Exception var3) {
            info = null;
            Log.e(TAG, "getCameraInfo failed on device " + deviceId);
        }
        return info;
    }

    public static boolean isCameraFront(int deviceId) {
        Camera.CameraInfo cameraInfo = getCameraInfo(deviceId);
        return (cameraInfo != null && cameraInfo.facing == 1);
    }

    public static boolean isCameraFront(int deviceId, Context context) {
        if (deviceId < 0) {
            return true;
        }
        OnCameraSupport cameraSupport = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cameraSupport = new CameraNew(context);
            return cameraSupport.getLensFacing(deviceId) == CameraFacingType.FRONT;
        } else {
            cameraSupport = new CameraOld();
            return cameraSupport.getLensFacing(deviceId) == CameraFacingType.FRONT;
        }
    }

    public static boolean checkCameraSupport(int deviceId, Context context) {
        if (checkDeviceCamera(deviceId)) {
            return true;
        } else {
            OnCameraSupport cameraSupport = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraSupport = new CameraNew(context);
                cameraSupport.open(deviceId);
                return cameraSupport.getNewCameraDevice() != null;
            } else {
                cameraSupport = new CameraOld();
                cameraSupport.open(deviceId);
                return cameraSupport.getOldCameraDevice() != null;
            }
        }
    }

    public static boolean checkCameraSupportAndClose(int deviceId, Context context) {
        if (checkDeviceCamera(deviceId)) {
            return true;
        } else {
            OnCameraSupport cameraSupport = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraSupport = new CameraNew(context);
                cameraSupport.open(deviceId);
                cameraSupport.releaseCamera();
                return cameraSupport.getNewCameraDevice() != null;
            } else {
                cameraSupport = new CameraOld();
                cameraSupport.open(deviceId);
                cameraSupport.releaseCamera();
                return cameraSupport.getOldCameraDevice() != null;
            }
        }
    }

    public static boolean checkCameraBackSupport(Context context) {
        if (checkDeviceCamera()) {
            return true;
        } else {
            OnCameraSupport cameraSupport = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraSupport = new CameraNew(context);
                cameraSupport.open(cameraSupport.getBackCameraId());
                return cameraSupport.getNewCameraDevice() != null;
            } else {
                cameraSupport = new CameraOld();
                cameraSupport.open(cameraSupport.getBackCameraId());
                return cameraSupport.getOldCameraDevice() != null;
            }
        }
    }

    public static boolean checkDeviceCamera() {
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            if (mCamera != null) {
                mCamera.stopPreview();
                mCamera.release();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkDeviceCamera(int id) {
        Camera mCamera = null;
        try {
            mCamera = Camera.open(id);
            if (mCamera != null) {
                mCamera.stopPreview();
                mCamera.release();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkCameraBackSupportAndClose(Context context) {
        if (checkDeviceCamera()) {
            return true;
        } else {
            try {
                OnCameraSupport cameraSupport = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cameraSupport = new CameraNew(context);
                    cameraSupport.open(cameraSupport.getBackCameraId());
                    cameraSupport.releaseCamera();
                    return cameraSupport.getNewCameraDevice() != null;
                } else {
                    cameraSupport = new CameraOld();
                    cameraSupport.open(cameraSupport.getBackCameraId());
                    cameraSupport.releaseCamera();
                    return cameraSupport.getOldCameraDevice() != null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static boolean checkCameraFrontSupport(Context context) {
        if (checkDeviceCamera()) {
            return true;
        } else {
            OnCameraSupport cameraSupport = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraSupport = new CameraNew(context);
                cameraSupport.open(cameraSupport.getBackCameraId());
                return cameraSupport.getNewCameraDevice() != null;
            } else {
                cameraSupport = new CameraOld();
                cameraSupport.open(cameraSupport.getBackCameraId());
                return cameraSupport.getOldCameraDevice() != null;
            }
        }
    }

    public static Integer getFrontCamera(Context context) {
        OnCameraSupport cameraSupport = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cameraSupport = new CameraNew(context);
            return cameraSupport.getBackCameraId();
        } else {
            cameraSupport = new CameraOld();
            return cameraSupport.getBackCameraId();
        }
    }

    public static boolean checkCameraFrontSupportAndClose(Context context) {
        if (checkDeviceCamera()) {
            return true;
        } else {
            OnCameraSupport cameraSupport = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraSupport = new CameraNew(context);
                cameraSupport.open(cameraSupport.getBackCameraId());
                cameraSupport.releaseCamera();
                return cameraSupport.getNewCameraDevice() != null;
            } else {
                cameraSupport = new CameraOld();
                cameraSupport.open(cameraSupport.getBackCameraId());
                cameraSupport.releaseCamera();
                return cameraSupport.getOldCameraDevice() != null;
            }
        }
    }
}
