package com.lyj.mypermission.permission;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by yu on 2017/6/28.
 */

public interface PermissionListener {
    void onSucceed(int requestCode, @NonNull List<String> grantPermissions);

    void onFailed(int requestCode, @NonNull List<String> deniedPermissions);
}
