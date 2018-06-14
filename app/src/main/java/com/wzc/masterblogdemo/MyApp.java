package com.wzc.masterblogdemo;

import android.app.Application;

import com.wzc.masterblogdemo.util.CrashHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * @author wzc
 * @date 2018/5/30
 */
public class MyApp extends Application {

    private String CER_12306 = "-----BEGIN CERTIFICATE-----\n" +
            "MIICmjCCAgOgAwIBAgIIbyZr5/jKH6QwDQYJKoZIhvcNAQEFBQAwRzELMAkGA1UEBhMCQ04xKTAn\n" +
            "BgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMB4X\n" +
            "DTA5MDUyNTA2NTYwMFoXDTI5MDUyMDA2NTYwMFowRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNp\n" +
            "bm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMIGfMA0GCSqGSIb3\n" +
            "DQEBAQUAA4GNADCBiQKBgQDMpbNeb34p0GvLkZ6t72/OOba4mX2K/eZRWFfnuk8e5jKDH+9BgCb2\n" +
            "9bSotqPqTbxXWPxIOz8EjyUO3bfR5pQ8ovNTOlks2rS5BdMhoi4sUjCKi5ELiqtyww/XgY5iFqv6\n" +
            "D4Pw9QvOUcdRVSbPWo1DwMmH75It6pk/rARIFHEjWwIDAQABo4GOMIGLMB8GA1UdIwQYMBaAFHle\n" +
            "tne34lKDQ+3HUYhMY4UsAENYMAwGA1UdEwQFMAMBAf8wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDov\n" +
            "LzE5Mi4xNjguOS4xNDkvY3JsMS5jcmwwCwYDVR0PBAQDAgH+MB0GA1UdDgQWBBR5XrZ3t+JSg0Pt\n" +
            "x1GITGOFLABDWDANBgkqhkiG9w0BAQUFAAOBgQDGrAm2U/of1LbOnG2bnnQtgcVaBXiVJF8LKPaV\n" +
            "23XQ96HU8xfgSZMJS6U00WHAI7zp0q208RSUft9wDq9ee///VOhzR6Tebg9QfyPSohkBrhXQenvQ\n" +
            "og555S+C3eJAAVeNCTeMS3N/M5hzBRJAoffn3qoYdAO1Q8bTguOi+2849A==\n" +
            "-----END CERTIFICATE-----";

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);

        // 修改缓存路径和大小,最好在Application中初始化，初始化没有耗时操作
        File cacheFile = new File(this.getCacheDir(), "cache_master");
        CacheWebView.getCacheConfig().init(this, cacheFile.getAbsolutePath(), 1024 * 1024 * 100, 1024 * 1024 * 10)
                .enableDebug(true);//100M 磁盘缓存空间,10M 内存缓存空间


        try {
            // 方式一: 证书放在 assets 目录下
            InputStream inputStream = getAssets().open("srca.cer");
            InputStream inputStream1 = getAssets().open("wzc_server.cer");
            InputStream inputStream2 = getAssets().open("wzc_client.bks");
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{inputStream,inputStream1}, inputStream2, "123456");
            // 方式二: 使用字符串替代证书, 生成方式
            // keytool -printcert -rfc -file srca.cer
//            InputStream inputStream1 = new Buffer().writeUtf8(CER_12306).inputStream();
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{inputStream1}, null, null);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
            OkHttpUtils.initClient(okHttpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





































