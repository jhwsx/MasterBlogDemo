package com.wzc.masterblogdemo.dagger2.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.wzc.masterblogdemo.dagger2.di.ApplicationContext;
import com.wzc.masterblogdemo.dagger2.di.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzc
 * @date 2018/6/2
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
