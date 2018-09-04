package com.wzc.masterblogdemo.okhttpwebsocket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wzc.masterblogdemo.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * 这篇文章好长啊：https://www.jianshu.com/p/13ceb541ade9
 * 这篇文章的例子：https://howtoprogram.xyz/2016/12/24/websocket-client-example-okhttp/
 * @author wzc
 * @date 2018/9/4
 */
public class OkHttpWebsocketActivity extends AppCompatActivity {

    private String mTestUrl = "ws://echo.websocket.org";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_websocket);
    }

    public void websockettest(View view) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(mTestUrl).build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
}
