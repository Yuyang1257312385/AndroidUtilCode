package com.lyj.mypermission.permission;

import android.support.annotation.NonNull;

/**
 * Created by yu on 2017/6/28.
 *
 * 请求权限的接口 链式调用
 */

public interface Request<T extends Request> {
    /**
     * Here to fill in all of this to apply for permission, can be a, can be more.
     *
     * @param permissions one or more permissions.
     * @return {@link Request}.
     */
    @NonNull
    T permission(String... permissions);

    /**
     * Request code.
     *
     * @param requestCode int, the first parameter in callback {@code onRequestPermissionsResult(int, String[],
     *                    int[])}}.
     * @return {@link Request}.
     */
    @NonNull
    T requestCode(int requestCode);

    /**
     * Set the callback object.
     *
     * @return {@link Request}.
     */
    T callback(Object callback);

    void resume();

    /**
     * Request permission.
     */
    void start();
}
