package com.aio.library.permiso;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.aio.library.R;
import com.aio.library.camera.CameraUtils;
import com.aio.library.sound.SoundUtil;
import com.greysonparrelli.permiso.Permiso;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by aio-synergy on 3/31/16.
 */
public class CustomPermisoUtil {
    private static final int PERMISSION_RQ = 84;

    public static void getPermissionReadPhoneState(final Context context, final PermissionListener listener) {

    }

    public static void getPermissionScanQRCode(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_scan_qr_code);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.CAMERA);
    }

    public static void getPermissionPickPictureVideo(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_pick_picture);
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_pick_video);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static void getPermissionTakePicture(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_take_picture);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static void getPermissionTakeVideo(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_take_video);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static void getPermissionVoiceMessage(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_voice_messaging);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static void getPermissionVoiceCall(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_voice_call);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.RECORD_AUDIO);
    }

    public static void getPermissionVideoCall(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_video_call);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO);
    }

    public static void getPermissionShareLocation(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_share_location);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    public static void getPermissionGetUserLocation(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_share_location);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    public static void getPermissionAutoFillAreaCode(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_auto_fill_area_code);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    public static void getPermissionRecommendNewFriends(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_recommend_new_friends);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.READ_CONTACTS);
    }

    public static void getPermissionReadFromContacts(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_read_from_the_contacts);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.READ_CONTACTS);
    }


    public static void getPermissionSaveMediaToStorage(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_save_media_to_storage);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    public static void getPermissionSaveOpenMedia(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_save_media_to_storage);
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_open_media_from_storage);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static void getPermissionWhiteboardCall(final Context context, final PermissionListener listener) {
        ArrayList<Integer> rationaleStringResourceArrayList = new ArrayList<>();
        rationaleStringResourceArrayList.add(R.string.permission_rationale_feature_whiteboard_call);
        getPermission(context, listener, rationaleStringResourceArrayList, Manifest.permission.RECORD_AUDIO);
    }

    private static boolean lackOfCameraPermission(Context context, String... permissions) {
        if (permissions != null && permissions.length > 0) {
            String[] arrayPermissions = permissions.clone();
            for (int i = 0; i < arrayPermissions.length; i++) {
                if (arrayPermissions[i].equals(Manifest.permission.CAMERA)) {
                    return !CameraUtils.checkCameraBackSupportAndClose(context);
                }
            }
        }
        return false;
    }

    private static boolean lackOfAudioPermission(String... permissions) {
        if (permissions != null && permissions.length > 0) {
            String[] arrayPermissions = permissions.clone();
            for (int i = 0; i < arrayPermissions.length; i++) {
                if (arrayPermissions[i].equals(Manifest.permission.RECORD_AUDIO)) {
                    return !SoundUtil.validateMicAvailability();
                }
            }
        }
        return false;
    }

    public static void getPermission(final Context context, final PermissionListener listener, final ArrayList<Integer> rationaleStringResourceArrayList, final String... permissions) {
        try {
            Permiso.getInstance().requestPermissions(new Permiso.IOnPermissionResult() {
                @Override
                public void onPermissionResult(Permiso.ResultSet resultSet) {
                    boolean hasPermanentlyDeniedPermission = false;
                    if (resultSet.areAllPermissionsGranted() && !lackOfCameraPermission(context, permissions) && !lackOfAudioPermission(permissions)) {
                        listener.onPermissionGranted();
                    } else {
                        ArrayList<String> permissionsDeniedArrayList = new ArrayList<>();
                        for (String strPermission : permissions) {
                            if (resultSet.toMap().containsKey(strPermission)) {
                                if (!resultSet.isPermissionGranted(strPermission)) {
                                    permissionsDeniedArrayList.add(strPermission);
                                }
                                if (resultSet.isPermissionPermanentlyDenied(strPermission)) {
                                    hasPermanentlyDeniedPermission = true;
                                }
                            }
                        }
                        String strDialogMessage = context.getString(R.string.permission_denied_general) +
                                getPermissionGroupStrings(context, permissionsDeniedArrayList) +
                                context.getString(R.string.permission_denied_general_part2) +
                                formatRationaleStrings(context, rationaleStringResourceArrayList);
                        if (context instanceof Activity && !((Activity) context).isFinishing()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle(R.string.app_name)
                                    .setCancelable(false);
                            if (hasPermanentlyDeniedPermission) {
                                strDialogMessage += "\n" + context.getString(R.string.permission_denied_general_instructions);
                                builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        listener.onPermissionDenied();
                                    }
                                })
                                        .setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                listener.onPermissionDenied();
                                                PermissionUtil.startInstalledAppDetailsActivity(context);
                                            }
                                        });

                            } else {
                                builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        listener.onPermissionDenied();
                                    }
                                });
                            }
                            try {
                                builder.setMessage(strDialogMessage)
                                        .create()
                                        .show();
                            } catch (Exception ex) {
                                ex.fillInStackTrace();
                                listener.onPermissionDenied();
                            }
                        }
                    }
                }

                @Override
                public void onRationaleRequested(Permiso.IOnRationaleProvided callback, String... permissions) {
                    Permiso.getInstance().showRationaleInDialog(context.getString(R.string.app_name),
                            context.getString(R.string.permission_rationale_general) +
                                    getPermissionGroupStrings(context, permissions) +
                                    context.getString(R.string.permission_rationale_general_part2) +
                                    formatRationaleStrings(context, rationaleStringResourceArrayList),
                            null,
                            callback);
                }
            }, permissions);
        } catch (Exception e) {
            e.fillInStackTrace();
            listener.onPermissionDenied();
        }
    }

    public interface PermissionListener {
        void onPermissionGranted();

        void onPermissionDenied();

    }

    private static String formatRationaleStrings(Context context, ArrayList<Integer> rationaleStringResourceArrayList) {
        String formattedRationaleStrings = "";
        for (int rationaleStringResource : rationaleStringResourceArrayList) {
            formattedRationaleStrings += context.getString(R.string.permission_four_spaces) + context.getString(rationaleStringResource) + "\n";
        }
        return formattedRationaleStrings;
    }

    private static String getPermissionGroupStrings(Context context, String... permissions) {
        String permissionGroupStrings = "";
        ArrayList<String> permissionStringArrayList = removeRepetitivePermissionGroup(context, permissions);
        for (String permission : permissionStringArrayList) {
            permissionGroupStrings += context.getString(R.string.permission_four_spaces) + permission + "\n";
        }
        return permissionGroupStrings;
    }

    private static String getPermissionGroupStrings(Context context, ArrayList<String> permissions) {
        String permissionGroupStrings = "";
        ArrayList<String> permissionStringArrayList = removeRepetitivePermissionGroup(context, permissions);
        for (String permission : permissionStringArrayList) {
            permissionGroupStrings += context.getString(R.string.permission_four_spaces) + permission + "\n";
        }
        return permissionGroupStrings;
    }

    private static ArrayList<String> removeRepetitivePermissionGroup(Context context, String... permissions) {
        HashSet<String> permissionSet = new HashSet();
        ArrayList<String> permissionStringArrayList = new ArrayList<>();

        for (String permission : permissions) {
            permissionSet.add(context.getString(getPermissionGroupResourceString(permission)));
        }
        permissionStringArrayList.addAll(permissionSet);
        return permissionStringArrayList;
    }

    private static ArrayList<String> removeRepetitivePermissionGroup(Context context, ArrayList<String> permissions) {
        HashSet<String> permissionSet = new HashSet();
        ArrayList<String> permissionStringArrayList = new ArrayList<>();

        for (String permission : permissions) {
            permissionSet.add(context.getString(getPermissionGroupResourceString(permission)));
        }
        permissionStringArrayList.addAll(permissionSet);
        return permissionStringArrayList;
    }

    private static int getPermissionGroupResourceString(String permission) {
        if (permission.equalsIgnoreCase(Manifest.permission.READ_CALENDAR) ||
                permission.equalsIgnoreCase(Manifest.permission.WRITE_CALENDAR)) {
            return R.string.permission_group_calendar;
        } else if (permission.equalsIgnoreCase(Manifest.permission.CAMERA)) {
            return R.string.permission_group_camera;
        } else if (permission.equalsIgnoreCase(Manifest.permission.READ_CONTACTS) ||
                permission.equalsIgnoreCase(Manifest.permission.WRITE_CONTACTS) ||
                permission.equalsIgnoreCase(Manifest.permission.GET_ACCOUNTS)) {
            return R.string.permission_group_contacts;
        } else if (permission.equalsIgnoreCase(Manifest.permission.ACCESS_FINE_LOCATION) ||
                permission.equalsIgnoreCase(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            return R.string.permission_group_location;
        } else if (permission.equalsIgnoreCase(Manifest.permission.RECORD_AUDIO)) {
            return R.string.permission_group_microphone;
        } else if (permission.equalsIgnoreCase(Manifest.permission.READ_PHONE_STATE) ||
                permission.equalsIgnoreCase(Manifest.permission.CALL_PHONE) ||
                permission.equalsIgnoreCase(Manifest.permission.READ_CALL_LOG) ||
                permission.equalsIgnoreCase(Manifest.permission.WRITE_CALL_LOG) ||
                permission.equalsIgnoreCase(Manifest.permission.ADD_VOICEMAIL) ||
                permission.equalsIgnoreCase(Manifest.permission.USE_SIP) ||
                permission.equalsIgnoreCase(Manifest.permission.PROCESS_OUTGOING_CALLS)) {
            return R.string.permission_group_phone;
        } else if (permission.equalsIgnoreCase(Manifest.permission.BODY_SENSORS)) {
            return R.string.permission_group_sensors;
        } else if (permission.equalsIgnoreCase(Manifest.permission.SEND_SMS) ||
                permission.equalsIgnoreCase(Manifest.permission.RECEIVE_SMS) ||
                permission.equalsIgnoreCase(Manifest.permission.READ_SMS) ||
                permission.equalsIgnoreCase(Manifest.permission.RECEIVE_WAP_PUSH) ||
                permission.equalsIgnoreCase(Manifest.permission.RECEIVE_MMS)) {
            return R.string.permission_group_sms;
        } else if (permission.equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE) ||
                permission.equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return R.string.permission_group_storage;
        } else {
            return R.string.permission_group_phone;
        }
    }

    public static boolean checkStoragePermission(Activity activity) {
        PermissionUtil.PermissionStatus permissionStatus =
                PermissionUtil.getPermissionStatus(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});
        return permissionStatus == PermissionUtil.PermissionStatus.GRANTED;
    }
}
