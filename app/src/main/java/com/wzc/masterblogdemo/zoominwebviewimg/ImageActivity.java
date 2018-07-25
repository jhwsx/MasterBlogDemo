package com.wzc.masterblogdemo.zoominwebviewimg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author wzc
 * @date 2018/7/25
 */
public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        setContentView(imageView);
        String url = "";
        if (getIntent() != null) {
            url =  getIntent().getStringExtra("img");
        }
        Glide.with(this).load(url).into(imageView);
    }
}
