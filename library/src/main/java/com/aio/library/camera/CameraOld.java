package com.aio.library.camera;

import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import com.aio.library.listener.OnCameraSupport;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;
import static android.hardware.Camera.getCameraInfo;
import static android.hardware.Camera.getNumberOfCameras;

/**
 * Created by aio-synergy on 4/14/17.
 */

@SuppressWarnings("deprecation")
public class CameraOld implements OnCameraSupport {

    private Camera camera;

    @Override
    public OnCameraSupport open(final int cameraId) {
        try {
            this.camera = Camera.open(cameraId);
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return this;
    }

    @Override
    public int getOrientation(final int cameraId) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        getCameraInfo(cameraId, info);
        return info.orientation;
    }

    @Override
    public CameraFacingType getLensFacing(int cameraId) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        getCameraInfo(cameraId, info);
        if (info.facing == CAMERA_FACING_FRONT) {
            return CameraFacingType.FRONT;
        } else if (info.facing == CAMERA_FACING_BACK) {
            return CameraFacingType.NOT_SUPPORT;
        }
        return CameraFacingType.NOT_SUPPORT;
    }

    @Override
    public int getBackCameraId() {
        try {
            int numberOfCameras = getNumberOfCameras();
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            for (int i = 0; i < numberOfCameras; i++) {
                getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    return i;
                }
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return 0;
    }

    @Override
    public int getFrontCameraId() {
        try {
            int numberOfCameras = getNumberOfCameras();
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            for (int i = 0; i < numberOfCameras; i++) {
                getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    return i;
                }
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return 0;
    }

    @Override
    public CameraDevice getNewCameraDevice() {
        return null;
    }

    @Override
    public Camera getOldCameraDevice() {
        return camera;
    }

    @Override
    public void releaseCamera() {
        try {
            if (camera != null) {
                camera.stopPreview();
                camera.release();
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
    }
}
