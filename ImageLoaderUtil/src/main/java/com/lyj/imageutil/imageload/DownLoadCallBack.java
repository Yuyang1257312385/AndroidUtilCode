package com.lyj.imageutil.imageload;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.animation.GlideAnimation;

import java.io.File;

/**
 * Created by yu on 2017/6/30.
 */

public interface DownLoadCallBack {
    void onSucc(File resource, GlideAnimation<? super File> glideAnimation);
    void onFail(Exception e, Drawable errorDrawable);
}
