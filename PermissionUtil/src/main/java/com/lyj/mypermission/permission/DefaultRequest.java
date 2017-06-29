package com.lyj.mypermission.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;

import com.lyj.mypermission.permission.target.Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yu on 2017/6/28.
 */

public class DefaultRequest implements Request,PermissionActivity.PermissionListener {

    private Target target;
    private int mRequestCode;
    private String[] mPermissions;
    private PermissionListener mCallBack;
    private String[] mDeniedPermissions;//被拒绝的权限

    public DefaultRequest(Target target) {
        this.target = target;
    }

    @NonNull
    @Override
    public Request permission(String... permissions) {
        mPermissions = permissions;
        return this;
    }

    @NonNull
    @Override
    public Request requestCode(int requestCode) {
        mRequestCode = requestCode;
        return this;
    }

    @Override
    public Request callback(Object callback) {
        mCallBack = (PermissionListener) callback;
        return this;
    }

    @Override
    public void start() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //6.0之下，直接掉成功
            callbackSucceed();
        } else {
            mDeniedPermissions = getDeniedPermissions(target.getContext(), mPermissions);
            // Denied mPermissions size > 0.
            if (mDeniedPermissions.length > 0) {
                resume();
            } else { // All permission granted.
                callbackSucceed();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void resume() {
        PermissionActivity.setPermissionListener(this);
        Intent intent = new Intent(target.getContext(), PermissionActivity.class);
        intent.putExtra(PermissionActivity.KEY_INPUT_PERMISSIONS, mDeniedPermissions);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        target.startActivity(intent);
    }

    /**
     * 获取被拒绝的权限
     *
     * @param context
     * @param permissions 申请的权限
     * @return
     */
    private static String[] getDeniedPermissions(Context context, @NonNull String... permissions) {
        List<String> deniedList = new ArrayList<>(1);
        for (String permission : permissions)
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                deniedList.add(permission);
        return deniedList.toArray(new String[deniedList.size()]);
    }


    @Override
    public void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
        List<String> deniedList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++)
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                deniedList.add(permissions[i]);

        if (deniedList.isEmpty())
            callbackSucceed();
        else
            callbackFailed(deniedList);
    }

    private void callbackSucceed() {
        if (mCallBack != null) {
            mCallBack.onSucceed(mRequestCode, Arrays.asList(mPermissions));
        }
    }

    private void callbackFailed(List<String> deniedList) {
        if (mCallBack != null) {
            if (mCallBack instanceof PermissionListener)
                ((PermissionListener) mCallBack).onFailed(mRequestCode, deniedList);
        }
    }
}
