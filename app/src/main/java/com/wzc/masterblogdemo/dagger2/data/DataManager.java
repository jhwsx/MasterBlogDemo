package com.wzc.masterblogdemo.dagger2.data;

import android.content.Context;
import android.content.res.Resources;

import com.wzc.masterblogdemo.dagger2.data.model.User;
import com.wzc.masterblogdemo.dagger2.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author wzc
 * @date 2018/6/2
 */
@Singleton
public class DataManager {
    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken() {
       return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public Long createUser(User user) {
       return mDbHelper.insertUser(user);
    }

    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getUser(userId);
    }
}
