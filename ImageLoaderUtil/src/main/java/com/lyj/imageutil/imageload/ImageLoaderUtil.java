package com.lyj.imageutil.imageload;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lyj.imageutil.R;
import com.lyj.imageutil.imageload.DownLoadCallBack;
import com.lyj.imageutil.imageload.DownloadImageTarget;

import java.io.File;

/**
 * Created by yu on 2017/6/30.
 *
 * todo 更多用法参照loadImage(Context context, String url, ImageView imageView)中注释部分
 */

public class ImageLoaderUtil {

    /**
     * 加载url对象
     * @param context
     * @param url  链接或路径
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)//加载占位图
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)//异常占位图
                //.diskCacheStrategy(DiskCacheStrategy.NONE)//不使用硬盘缓存
                //.skipMemoryCache(true)//禁用内存缓存
                //加载静态图片，若是gif，会显示第一帧
                //.asBitmap()
                //加载动态图片，若传入的资源是静态图片，则会加载失败
                //.asGif()
                //将图片加载成100*100像素的尺寸
                //.override(100, 100)
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target,
//                                               boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model,
//                                                   Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        return false;
//                    }
//                })
                .into(imageView);
    }

    /**
     * 加载本地图片
     * @param context
     * @param file
     * @param imageView
     */
    public static void loadImage(Context context, File file, ImageView imageView){
        Glide.with(context).load(file).into(imageView);
    }

    /**
     * 加载应用资源
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void loadImage(Context context, int resId, ImageView imageView){
        Glide.with(context).load(resId).into(imageView);
    }

    /**
     * 加载二进制流
     * @param context
     * @param bytes
     * @param imageView
     */
    public static void loadImage(Context context, Byte[] bytes, ImageView imageView){
        Glide.with(context).load(bytes).into(imageView);
    }

    /**
     * 加载Uri对象
     * @param context
     * @param uri
     * @param imageView
     */
    public static void loadImage(Context context, Uri uri, ImageView imageView){
        Glide.with(context).load(uri).into(imageView);
    }




    /**
     * 预加载
     *
     * 加载时要调用loadPreload
     * @param context
     * @param url
     */
    public static void perload(Context context,String url){
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .preload();
    }

    /**
     * 加载已经预加载过的url
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadPreload(Context context,String url,ImageView imageView){
        Glide.with(context)
                .load(url)//加载占位图
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)//异常占位图
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//此行必须要
                .into(imageView);
    }

    /**
     * 下载图片
     * @param context
     * @param url
     * @param callBack
     */
    public static void downloadImage(Context context,String url,DownLoadCallBack callBack) {
        Glide.with(context)
                .load(url)
                .downloadOnly(new DownloadImageTarget(callBack));
    }

}
