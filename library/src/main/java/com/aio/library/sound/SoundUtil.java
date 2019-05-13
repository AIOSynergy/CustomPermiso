package com.aio.library.sound;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;

/**
 * Created by aio-synergy on 5/12/17.
 */

public class SoundUtil {
    public static void setSpeakerMute(Context context) {
        if (context == null) return;
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (isSpeakerPhoneOn(context)) {
            audioManager.setSpeakerphoneOn(false);
            audioManager.setMode(0);
            return;
        }
        audioManager.setMode(2);
        audioManager.setSpeakerphoneOn(true);
    }

    public static void setMicMute(Context context) {
        if (context == null) return;
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (isMicrophoneMute(context)) {
            audioManager.setMicrophoneMute(false);
            audioManager.setMode(0);
            return;
        }
        audioManager.setMode(2);
        audioManager.setMicrophoneMute(true);
    }

    public static boolean isSpeakerPhoneOn(Context context) {
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            return audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
        } catch (Exception ex) {
            ex.fillInStackTrace();
            return false;
        }
    }

    public static boolean isMicrophoneMute(Context context) {
        if (context == null) return false;
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.isMicrophoneMute();
    }

    public static boolean isHeadsetOn(Context context) {
        if (context == null) return false;
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        Intent iStatus = context.registerReceiver(null, iFilter);
        assert iStatus != null;
        return iStatus.getIntExtra("state", 0) == 1;
    }

    public static void cancelNoiseWhenRecord(Context context) {
        if (context == null) return;
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setParameters("noise_suppression=auto");
    }

    public static boolean validateMicAvailability() {
        Boolean available = true;
        AudioRecord recorder =
                new AudioRecord(MediaRecorder.AudioSource.MIC, 44100,
                        AudioFormat.CHANNEL_IN_MONO,
                        AudioFormat.ENCODING_DEFAULT, 44100);
        try {
            if (recorder.getRecordingState() != AudioRecord.RECORDSTATE_STOPPED) {
                available = false;

            }
            recorder.startRecording();
            if (recorder.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
                recorder.stop();
                available = false;
            }
            recorder.stop();
        } finally {
            recorder.release();
            recorder = null;
        }
        return available;
    }
}
