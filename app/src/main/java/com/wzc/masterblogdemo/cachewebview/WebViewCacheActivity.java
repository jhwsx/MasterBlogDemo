package com.wzc.masterblogdemo.cachewebview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.wzc.masterblogdemo.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wzc
 * @date 2018/5/30
 */
public class WebViewCacheActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView webView;
    private Button nightModeBtn;
    private Button lightModeBtn;
    private String url; // 网页url
    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_cache);

        webView = (WebView) this.findViewById(R.id.webview);
        nightModeBtn = (Button) this.findViewById(R.id.btn_nightmode);
        lightModeBtn = (Button) this.findViewById(R.id.btn_daymode);

        nightModeBtn.setOnClickListener(this);
        lightModeBtn.setOnClickListener(this);

        WebSettings settings = webView.getSettings();
        // 设置javaScript可用
        settings.setJavaScriptEnabled(true);
        // 绑定javaScript接口，可以实现在javaScript中调用我们的Android代码
//      webView.addJavascriptInterface(new WebAppInterface(this), "Android");
//      webView.setWebViewClient(new MyWebViewClient());

        // 加载assets目录下的html页面
//        webView.loadUrl("file:///android_asset/web.html");

        url = "https://blog.csdn.net/wwj_748/article/details/44810283";

        findView();
    }

    private void findView() {
        initWebView();
        webView.setWebViewClient(new WebViewClient(){
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.contains("3_wwj_748.jpg")) {

                    InputStream inputStream = null;
                    try {
                        inputStream =  getAssets().open("logo.png");
                        // 参数1：http请求里该图片的Content-Type,此处图片为image/png
                        // 参数2：编码类型
                        // 参数3：存放着替换资源的输入流（上面创建的那个）
                        WebResourceResponse response = new WebResourceResponse("image/png",
                                "utf-8", inputStream);

                        return response;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                return super.shouldInterceptRequest(view, url);
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().contains("3_wwj_748.jpg")) {

                    InputStream inputStream = null;
                    try {
                        inputStream =  getAssets().open("logo.png");
                        // 参数1：http请求里该图片的Content-Type,此处图片为image/png
                        // 参数2：编码类型
                        // 参数3：存放着替换资源的输入流（上面创建的那个）
                        WebResourceResponse response = new WebResourceResponse("image/png",
                                "utf-8", inputStream);

                        return response;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                return super.shouldInterceptRequest(view, request);
            }
        });
        // https://avatar.csdn.net/C/C/8/3_wwj_748.jpg
        webView.loadUrl(url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH); // 渲染线程的优先级
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        webView.getSettings().setDomStorageEnabled(true); // 开启DOM storage API 功能
        webView.getSettings().setDatabaseEnabled(true); // 开启database storage API功能
        webView.getSettings().setAppCacheEnabled(true); // 开启Application Cache功能
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        webView.getSettings().setDatabasePath(cacheDirPath); // 设置数据库缓存路径, 没有什么用了
        webView.getSettings().setAppCachePath(cacheDirPath); // 设置Application caches缓存目录
    }

    /**
     * 用于控制页面导航
     * @author wwj_748
     *
     */
    private class MyWebViewClient extends WebViewClient {
        /**
         * 当用于点击链接，系统调用这个方法
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("www.baidu.com")) {
                // 这个是我的网页，所以不要覆盖，让我的WebView来加载页面
                return false;
            }
            // 否则，这个链接不是我的网站页面，因此启用浏览器来处理urls
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 检查是否为返回事件，如果有网页历史记录
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // 如果不是返回键或没有网页浏览历史，保持默认
        // 系统行为（可能会退出该活动）
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_nightmode:
                webView.loadUrl("javascript:load_night()");
                break;
            case R.id.btn_daymode:
                webView.loadUrl("javascript:load_day()");
                break;

            default:
                break;
        }
    }
}
