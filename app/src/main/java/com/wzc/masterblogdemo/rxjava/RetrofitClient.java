package com.wzc.masterblogdemo.rxjava;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wzc
 * @date 2018/9/5
 */
public class RetrofitClient {
    private static Api sInstance;

    private RetrofitClient() {
        //no instance
    }

    public static Api getInstance() {
        if (sInstance == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://fy.iciba.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            sInstance = retrofit.create(Api.class);
        }

        return sInstance;
    }
}
