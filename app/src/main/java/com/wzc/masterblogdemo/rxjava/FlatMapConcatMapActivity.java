package com.wzc.masterblogdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wzc.masterblogdemo.R;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * https://www.jianshu.com/p/f67e05d7cd30
 *
 * @author wzc
 * @date 2018/8/1
 */
public class FlatMapConcatMapActivity extends AppCompatActivity {
    private static final String TAG = FlatMapConcatMapActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatmap_concatmap);

        findViewById(R.id.btn_no_flatmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noflatmap();
            }
        });

        findViewById(R.id.btn_flatmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatmap();
            }
        });

        findViewById(R.id.button_flatmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatmap2();
            }
        });

        findViewById(R.id.button_concatmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                concatmap();
                Observable.fromCallable(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        Log.d(TAG, "call: " + Thread.currentThread().getName());
                        return new Object();
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                Log.d(TAG, "accept: "+ Thread.currentThread().getName());
                            }
                        });

            }
        });


    }

    private void concatmap() {
        // 如果要想经过变换后，最终输出的序列和原序列一致，那就会用到另外一个操作符，concatMap。concatMap采用的是连接(concat)
        Observable.fromArray(1, 2, 3, 4, 5)
                .concatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        int delay = 0;
                        if (integer == 3) {
                            delay = 500;
                        }
                        return Observable.just(integer * 10).delay(delay, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "concatMap accept: " + integer);
                    }
                });

    }

    private void flatmap2() {
        // 经过 flatMap 操作变换后, 最后输出的序列有可能是交错的, 因为flatMap最后合并结果采用的是merge操作符。
        Observable.fromArray(1, 2, 3, 4, 5)
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        int delay = 0;
                        if (integer == 3) {
                            delay = 500;
                        }
                        return Observable.just(integer * 10).delay(delay, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "flatMap accept: " + integer);
                    }
                });

    }

    private void flatmap() {
        Flowable.fromIterable(MockData.getStudents())
                .flatMap(new Function<Student, Publisher<Source>>() {
                    @Override
                    public Publisher<Source> apply(Student student) throws Exception {
                        Log.d(TAG, "apply: " + student.name + ", this = " + this);
                        return Flowable.fromIterable(student.mSources);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Source>() {
                    @Override
                    public void accept(Source source) throws Exception {
                        Log.d(TAG, "accept: " + source + ", this = " + this);
                    }
                });
    }

    // 打印该班的每个同学的每一门课程成绩
    private void noflatmap() {
        List<Student> students = MockData.getStudents();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(student.name);
            List<Source> sources = student.mSources;
            for (int j = 0; j < sources.size(); j++) {
                Source source = sources.get(j);
                stringBuilder.append(source);
            }
            Log.d(TAG, "noflatmap: " + stringBuilder.toString());
        }
    }
}
