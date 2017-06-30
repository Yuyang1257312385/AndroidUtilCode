package com.lyj.imageutil.imageload;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.lyj.imageutil.imageload.DownLoadCallBack;

import java.io.File;

/**
 * Created by yu on 2017/6/30.
 */

public class DownloadImageTarget implements Target<File> {

    private static final String TAG = "DownloadImageTarget";

    public DownLoadCallBack downLoadCallBack;

    public DownloadImageTarget(){}

    public DownloadImageTarget(DownLoadCallBack downLoadCallBack){
        this.downLoadCallBack = downLoadCallBack;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        downLoadCallBack.onFail(e, errorDrawable);
    }

    @Override
    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
        //图片下载完成之后就会回调到这里，我在这个方法中只是打印了一下下载的图片文件的路径
        //Log.d(TAG, resource.getPath());
        downLoadCallBack.onSucc(resource,glideAnimation);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
    }

    @Override
    public void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    @Override
    public void setRequest(Request request) {
    }

    @Override
    public Request getRequest() {
        return null;
    }


}
