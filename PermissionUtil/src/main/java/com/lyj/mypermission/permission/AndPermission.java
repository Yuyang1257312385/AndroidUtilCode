package com.lyj.mypermission.permission;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lyj.mypermission.permission.target.AppActivityTarget;
import com.lyj.mypermission.permission.target.AppFragmentTarget;
import com.lyj.mypermission.permission.target.ContextTarget;
import com.lyj.mypermission.permission.target.SupportFragmentTarget;

/**
 * Created by yu on 2017/6/28.
 */

public class AndPermission {

    private AndPermission() {
    }

    /**
     * In the Activity.
     *
     * @param activity {@link Activity}.
     * @return {@link Request}.
     */
    public static
    @NonNull
    Request with(@NonNull Activity activity) {
        return new DefaultRequest(new AppActivityTarget(activity));
    }

    /**
     * In the Activity.
     *
     * @param fragment {@link android.support.v4.app.Fragment}.
     * @return {@link Request}.
     */
    public static
    @NonNull
    Request with(@NonNull android.support.v4.app.Fragment fragment) {
        return new DefaultRequest(new SupportFragmentTarget(fragment));
    }

    /**
     * In the Activity.
     *
     * @param fragment {@link android.app.Fragment}.
     * @return {@link Request}.
     */
    public static
    @NonNull
    Request with(@NonNull android.app.Fragment fragment) {
        return new DefaultRequest(new AppFragmentTarget(fragment));
    }

    /**
     * Anywhere..
     *
     * @param context {@link Context}.
     * @return {@link Request}.
     */
    public static
    @NonNull
    Request with(@NonNull Context context) {
        return new DefaultRequest(new ContextTarget(context));
    }


}
