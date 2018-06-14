package com.wzc.masterblogdemo.https;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wzc.masterblogdemo.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 参考文章:
 * https://blog.csdn.net/lmj623565791/article/details/48129405
 *
 * 报错:onError : Hostname 172.16.40.10 not verified:
 * 解决: 在hongyang大哥的基础上，后面加上 -ext san=ip:192.168.56.1（后面的ip是你电脑的ip地址哈）
 * 比如第一次生成jks就是这样：keytool -genkey -alias wzc_server -keyalg RSA -keystore wzc_server.jks -validity 3600 -storepass 123456 -ext san=ip:172.16.40.10
 *
 * 报错: 把jks 转成 bks, 文章中说使用java -jar bcprov.jar即可打开GUI界面, 应该是 java -jar portecle.jar.
 * @author wzc
 * @date 2018/6/10
 */
public class OkHttpHttpsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = OkHttpHttpsActivity.class.getSimpleName();
    private TextView mTvContent;
    private Button mBtnHttpsHtml;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_https);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mBtnHttpsHtml = (Button) findViewById(R.id.httpsHtml);

        mBtnHttpsHtml.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.httpsHtml:
//                String url = "https://kyfw.12306.cn/otn/";
                String url = "https://172.16.40.10:8443/ottergram/index.html";
                OkHttpUtils.get()
                        .url(url).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTvContent.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mTvContent.setText(response);
                    }
                });
                break;
            default:
                break;
        }
    }
}
