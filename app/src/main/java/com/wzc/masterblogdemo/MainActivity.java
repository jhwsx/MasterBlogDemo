package com.wzc.masterblogdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wzc.masterblogdemo.cachewebview.UseCacheWebViewLibActivity;
import com.wzc.masterblogdemo.cachewebview.WebViewCacheActivity;
import com.wzc.masterblogdemo.canvas.WhereAreCanvasFromActivity;
import com.wzc.masterblogdemo.constraintlayout1_1_x.ConstraintLayoutActivity;
import com.wzc.masterblogdemo.deeplink.DeepLinkActivity;
import com.wzc.masterblogdemo.dialogfragment.MyActivity;
import com.wzc.masterblogdemo.difference.GetWidthDifferenceActivity;
import com.wzc.masterblogdemo.https.OkHttpHttpsActivity;
import com.wzc.masterblogdemo.layoutinflator.LayoutInflaterSetFactoryActivity;
import com.wzc.masterblogdemo.preference.SettingsActivity;
import com.wzc.masterblogdemo.textwachter.TextWatcherActivity;
import com.wzc.masterblogdemo.zoominwebviewimg.ClickZoominWebViewImgActivity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private LinkedHashMap<String,Class<?>> mHashMap = new LinkedHashMap<>();
    private Class[] mClassArr;

    {
        mHashMap.put("Android 探究 LayoutInflater setFactory", LayoutInflaterSetFactoryActivity.class);
        mHashMap.put("创建 Android 设置界面", SettingsActivity.class);
        mHashMap.put("WebView实现离线缓存阅读", WebViewCacheActivity.class);
        mHashMap.put("使用CacheWebView开源库", UseCacheWebViewLibActivity.class);
        mHashMap.put("从源码的角度分析，getWidth() 与 getMeasuredWidth() 的不同之处", GetWidthDifferenceActivity.class);
        mHashMap.put("Android Https相关完全解析 当OkHttp遇到Https", OkHttpHttpsActivity.class);
        mHashMap.put("ConstraintLayout 1.1.x", ConstraintLayoutActivity.class);
        mHashMap.put("Using DialogFragments", MyActivity.class);
        mHashMap.put("Android 文本监听接口TextWatcher详解", TextWatcherActivity.class);
        mHashMap.put("探究Android View 绘制流程，Canvas 的由来", WhereAreCanvasFromActivity.class);
        mHashMap.put("在开发中实现点击 WebView 中的图片，调用原生控件放大展示", ClickZoominWebViewImgActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleIntent();
        mListView = (ListView) findViewById(R.id.listview);
        Set<String> keySet = mHashMap.keySet();
        String[] names = new String[keySet.size()];
        names =   keySet.toArray(names);

        Collection<Class<?>> values = mHashMap.values();
        mClassArr = new Class[values.size()];
        mClassArr =  values.toArray(mClassArr);

        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1,names));
        mListView.setOnItemClickListener(this);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
            if (uri != null) {
                String host = uri.getHost();
                if ("buydress".equals(host)) {
                    // 跳转到卖裙子界面
                    startActivity(new Intent(MainActivity.this, DeepLinkActivity.class));
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 覆盖之前的 intent
        setIntent(intent);

        handleIntent();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        startActivity(new Intent(MainActivity.this, mClassArr[position]));
    }
}
