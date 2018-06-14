package com.wzc.masterblogdemo.preference;

import android.support.v4.app.Fragment;

import com.wzc.masterblogdemo.BaseSingleFragmentActivity;

/**
 * 文章:
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0502/7901.html
 * @author wzc
 * @date 2018/5/28
 */
public class SettingsActivity extends BaseSingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SettingsFragment.newInstance();
    }
}
