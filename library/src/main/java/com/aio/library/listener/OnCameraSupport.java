package com.aio.library.listener;

import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import com.aio.library.camera.CameraFacingType;

/**
 * Created by aio-synergy on 4/14/17.
 */

public interface OnCameraSupport {
    OnCameraSupport open(int cameraId);

    int getOrientation(int cameraId);

    CameraFacingType getLensFacing(int cameraId);

    int getBackCameraId();

    int getFrontCameraId();

    CameraDevice getNewCameraDevice();

    Camera getOldCameraDevice();

    void releaseCamera();
}
