package bupt.com.vr_bupt.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * Created by joycezhao on 17/1/25.
 */

public class PermissionHelper /*implements EasyPermissions.PermissionCallbacks*/ {
      private static final String TAG=PermissionHelper.class.getSimpleName();
  /*  private static final int RC_CALL_PERM = 121;
    private static final int RC_WRITE_EXTERNAL_PERM = 122;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_SETTINGS = 124;*/
    private static PermissionHelper instance;

    public static PermissionHelper getHelper() {
        if (instance == null)
            instance = new PermissionHelper();
        return instance;
    }
    //检查权限
    public static boolean checkPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) { //Manifest.permission.CAMERA //拍照
            LogUtil.i(TAG, "没有获得权限");
            return false;
        }
        LogUtil.i(TAG, "已经拥有权限");
        return true;
    }



    //申请授权
    public static void getPermission(Activity activity, String permission, int REQUESTCODE) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUESTCODE);

    }

    public static void getPermission(Fragment fragment,String permission,int REQUESTCODE ){
        fragment.requestPermissions(new String[]{permission},REQUESTCODE);
    }
//    public void requestWriteExternalPerm(Context context) {
//
//    }
//
//    public void requestCallPerm() {
//
//    }
//
//    public void requestCameraPerm() {
//
//    }
//
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//    }
}
