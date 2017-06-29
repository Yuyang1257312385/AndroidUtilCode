package com.lyj.mypermission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lyj.mypermission.permission.PermissionListener;
import com.lyj.mypermission.permission.PermissionUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CALL_PHONE = 0x01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //**************使用封装好的Util*****************//
        /**
         * 1. 将permission拷贝到项目中
         * 2.主配置清单注册PermissionActivity
         */
        PermissionUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                //// TODO: 2017/6/28  获取权限后的操作
                call();
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                //// TODO: 2017/6/28  获取权限失败的提示
                PermissionUtil.showSettingTip(MainActivity.this,"需要该权限,请到设置页面设置");
            }
        }, Manifest.permission.CALL_PHONE);

    }

    /**
     *
     * @param activity
     * @param permission
     * @param requestCode
     * @return 无需授权，返回true，否则（请求或提示）返回false
     */
    private boolean requestPermission(Activity activity, String permission,int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(activity,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        permission)) {
                    //// TODO: 2017/6/28  解释 或提示到设置页面
                } else {
                    //请求权限
                    ActivityCompat.requestPermissions(activity,
                            new String[]{permission},
                            requestCode);
                }
            }else {
                return true;
            }
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //// TODO: 2017/6/28  授权成功后的操作
                    call();
                } else {
                    //// TODO: 2017/6/28   授权失败后的操作
                }
                return;
            }

        }
    }

    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10001"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


}
