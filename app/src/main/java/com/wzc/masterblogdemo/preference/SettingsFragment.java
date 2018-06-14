package com.wzc.masterblogdemo.preference;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/5/28
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    public static SettingsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_preferences);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // 在这里进行判断, 如果是自定义的 Preference, 就自己处理;
        // 否则, 就调用 super 方法.
        DialogFragment dialogFragment = null;
        if (preference instanceof TimePreference) {
            // 构造一个 TimePreferenceDialogFragmentCompat 的实例
             dialogFragment = TimePreferenceDialogFragmentCompat
                    .newInstance(preference.getKey());
        }
        // 是自定义的 Preference, 就显示对话框
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getFragmentManager(),
                    "android.support.v7.preference" +
                    ".PreferenceFragment.DIALOG");
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }
}
