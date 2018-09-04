package com.wzc.masterblogdemo.okhttpwebsocket;

import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * WebSocketListener 的实例类
 * @author wzc
 * @date 2018/9/4
 */
public class EchoWebSocketListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private static final String TAG = EchoWebSocketListener.class.getSimpleName();
    public EchoWebSocketListener() {
        super();
    }

    // 当 WebSocket 已经被远程的对端接收时调用，这时可以开始传输信息。
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        Log.d(TAG, "onOpen: ");
        // 传送 3 条信息给远程的 WebSocket 服务，然后关闭 client。
        webSocket.send("Knock, knock!");
        webSocket.send("Hello!");
        webSocket.send(ByteString.decodeHex("deadbeef"));
        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye!");
    }
    // 当一条信息已经被接收时回调，这个方法用于文本信息。
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        Log.d(TAG, "onMessage: receiving = " + text);
    }
    // 当一条信息已经被接收时回调，这个方法用于二进制信息。
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        Log.d(TAG, "onMessage: receiving = " + bytes.hex());
    }
    // 当远程的 WebSocket service 表明没有信息要传输时调用。
    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        Log.d(TAG, "onClosing: code = " + code + ", reason = " + reason);
    }
    // 当远程的 WebSocket service 正常关闭时回调
    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        Log.d(TAG, "onClosed: code = " + code + ", reason = " + reason);
    }
    // 当远程的 WebSocket 由于网络读写错误而被关闭时调用
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        Log.d(TAG, "onFailure: " + t.toString());
    }
}
