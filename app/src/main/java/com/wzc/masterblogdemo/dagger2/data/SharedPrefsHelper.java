package com.wzc.masterblogdemo.dagger2.data;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author wzc
 * @date 2018/6/2
 */
@Singleton
public class SharedPrefsHelper {
    public static String PREF_KEY_ACCESS_TOKEN = "access-token";

    private SharedPreferences mSharedPreferences;

    @Inject // 这个在构造函数上的注解表示这个类会通过 dagger 获取 SharedPreferences 依赖
    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }












































}
