package com.wzc.masterblogdemo.zoominwebviewimg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wzc.masterblogdemo.R;

/**
 * https://mp.weixin.qq.com/s?__biz=MjM5NDkxMTgyNw%3D%3D&mid=2653058641&idx=1&sn=8f4a2f9c4fe806ae8fd2a51050126cd7&chksm=bd56556d8a21dc7b47bc171e100ba184cf3ecd31433ff53af929df98e7b02ed13eb22895e735
 * @author wzc
 * @date 2018/7/25
 */
public class ClickZoominWebViewImgActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_zoomin_webview_img);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
//        mWebView.loadUrl("file:///android_asset/img.html");
        mWebView.loadUrl("http://www.wanandroid.com/blog/show/2246");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //这段js函数的功能就是注册监听，遍历所有的img标签，并添加onClick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
                mWebView.loadUrl("javascript:(function(){"
                        + "var objs = document.getElementsByTagName(\"img\"); "
                        + "for(var i=0;i<objs.length;i++)  " + "{"
                        + "    objs[i].onclick=function()  " + "    {  "
                        + "        window.imagelistner.openImage(this.src);  "
                        + "    }  " + "}" + "})()");
            }
        });
    }
    // js通信接口
    public class JavascriptInterface {

        private Context context;


        public JavascriptInterface(Context context) {
            this.context = context;
        }

        @android.webkit.JavascriptInterface
        public void openImage(String img) {
            Intent intent = new Intent();
            intent.putExtra("img", img);
            intent.setClass(context, ImageActivity.class);
            context.startActivity(intent);
            System.out.println(img);
        }
    }
}
