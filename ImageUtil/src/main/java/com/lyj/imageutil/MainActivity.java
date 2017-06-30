package com.lyj.imageutil;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.lyj.imageutil.imageload.DownLoadCallBack;
import com.lyj.imageutil.imageload.ImageLoaderUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String url = "http://www.phosion.cn/uploadfiles/image/201703/543.jpg";

    private Button btn_load,btn_download;
    private ImageView iv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAction();
    }

    private void initView() {
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_download = (Button) findViewById(R.id.btn_download);
        iv_show = (ImageView) findViewById(R.id.iv_show);
    }

    private void initAction() {
        btn_load.setOnClickListener(this);
        btn_download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_load:
                ImageLoaderUtil.loadImage(this,url,iv_show);
                break;
            case R.id.btn_download:
                ImageLoaderUtil.downloadImage(this, url, new DownLoadCallBack() {
                    @Override
                    public void onSucc(File resource, GlideAnimation<? super File> glideAnimation) {
                        Toast.makeText(MainActivity.this,resource.getPath(),Toast.LENGTH_LONG).show();
                        ImageLoaderUtil.loadImage(MainActivity.this,resource.getAbsoluteFile(),iv_show);

                    }

                    @Override
                    public void onFail(Exception e, Drawable errorDrawable) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }
}
