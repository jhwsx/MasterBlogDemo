package com.wzc.masterblogdemo.dagger2.di.module;

import android.app.Activity;
import android.content.Context;

import com.wzc.masterblogdemo.dagger2.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzc
 * @date 2018/6/2
 */
@Module // 这是一个Module类, 提供一个类的依赖
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides // 这是一个 dependency provider method
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }
}
