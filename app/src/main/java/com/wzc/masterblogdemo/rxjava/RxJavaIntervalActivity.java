package com.wzc.masterblogdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wzc.masterblogdemo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * https://blog.csdn.net/carson_ho/article/details/78256466
 * @author wzc
 * @date 2018/9/5
 */
public class RxJavaIntervalActivity extends AppCompatActivity {
    private static final String TAG = RxJavaIntervalActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_interval);
    }

    public void translate(View view) {
        // interval 操作符 无限次轮询 参一：第一次延时时间；参二：间隔时间；参三：时间单位
//        Observable.interval(2, 1, TimeUnit.SECONDS)
        // intervalRange() 操作符 有限次轮询 参一：开始的次数；参二：轮询的次数；参三：第一次延时时间；参四：间隔时间；参五：时间单位
        Observable.intervalRange(3, 10,2,1,TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept: " + aLong);
                        final Observable<Translation> translate = RetrofitClient.getInstance().translate("zh", "en", "早上好");
                        translate.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Translation>() {
                                    @Override
                                    public void accept(Translation translation) throws Exception {
                                        if (translation == null) {
                                            return;
                                        }
                                        Log.d(TAG, "accept: " + translation);
                                    }
                                });
                    }
                }).subscribe();
    }
}
