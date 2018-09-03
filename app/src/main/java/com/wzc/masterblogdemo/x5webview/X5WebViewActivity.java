package com.wzc.masterblogdemo.x5webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/8/17
 */
public class X5WebViewActivity extends AppCompatActivity {

    private FrameLayout mContainer;
    private X5WebView mWebView;
    private String mUrl = "https://www.baidu.com/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 启用硬件加速
//        try {
//            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
//                getWindow()
//                        .setFlags(
//                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
//                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
//            }
//        } catch (Exception e) {
//        }
        mUrl = "http://180.153.105.169/soft.imtt.qq.com/browser/tes/feedback.html";
        setContentView(R.layout.activity_x5webview);
        mContainer = (FrameLayout) findViewById(R.id.container);
        init();
    }

    private void init() {
        mWebView = new X5WebView(this, null);
        mContainer.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
        }
        super.onDestroy();
    }
}
