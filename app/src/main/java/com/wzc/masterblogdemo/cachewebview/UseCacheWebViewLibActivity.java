package com.wzc.masterblogdemo.cachewebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wzc.masterblogdemo.R;

import ren.yale.android.cachewebviewlib.CacheWebView;
import ren.yale.android.cachewebviewlib.WebViewCache;

/**
 * @author wzc
 * @date 2018/5/30
 */
public class UseCacheWebViewLibActivity extends AppCompatActivity {

    private CacheWebView mCacheWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_cachewebview);

        initViews();

        mCacheWebView.loadUrl("https://www.baidu.com/");
    }

    private void initViews() {
        mCacheWebView = (CacheWebView) findViewById(R.id.cachedwebview);
        mCacheWebView.setCacheStrategy(WebViewCache.CacheStrategy.FORCE);
    }
}
