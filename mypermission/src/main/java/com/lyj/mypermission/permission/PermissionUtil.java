package com.lyj.mypermission.permission;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.lyj.mypermission.permission.AndPermission;
import com.lyj.mypermission.permission.PermissionListener;

/**
 * Created by yu on 2017/6/28.
 */

public class PermissionUtil {

    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    public static void requestPermission(Context context, PermissionListener callback, String... permissons){
        AndPermission.with(context)
                .permission(permissons)
                .callback(callback)
                .start();
    }



    public static void showSettingTip(final Context context, String tipMsg) {

        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(tipMsg);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings(context);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // 启动应用的设置
    private static void startAppSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + context.getPackageName()));
        context.startActivity(intent);
    }
}
