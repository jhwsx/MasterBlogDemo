package com.wzc.masterblogdemo.preference;

import android.os.Bundle;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/5/28
 */
public class TimePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    private TimePicker mTimePicker;

    public static TimePreferenceDialogFragmentCompat newInstance(String key) {

        final TimePreferenceDialogFragmentCompat
                fragment = new TimePreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mTimePicker = (TimePicker) view.findViewById(R.id.edit);

        if (mTimePicker == null) {
            throw new IllegalStateException("Dialog view must contain an TimePicker with id" +
                    " @+id/edit");
        }
        ////////////////////////////////////////////////////
        // TimePicker 显示的值总是从 Preference 中取出来的值 //
        ////////////////////////////////////////////////////
        // 从相关的 Preference 中获取时间的值
        Integer minutesAfterMidnight = null;
        DialogPreference preference = getPreference();
        if (preference instanceof TimePreference) {
            minutesAfterMidnight = ((TimePreference) preference).getTime();
        }

        // 把时间值设置给 TimePicker
        if (minutesAfterMidnight != null) {
            int hours = minutesAfterMidnight / 60;
            int minutes = minutesAfterMidnight % 60;
            boolean is24Hour = DateFormat.is24HourFormat(getContext());

            mTimePicker.setIs24HourView(is24Hour);
            mTimePicker.setCurrentHour(hours);
            mTimePicker.setCurrentMinute(minutes);

        }
    }
    // 点击 ok 按钮后,保存选择的时间
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // 用户选择的时间
            int hours = mTimePicker.getCurrentHour();
            int minutes = mTimePicker.getCurrentMinute();
            int minutesAfterMidnight = hours * 60 + minutes;

            // 保存时间值给相关的 Preference
            DialogPreference preference = getPreference();
            if (preference instanceof TimePreference) {
                TimePreference timePreference = (TimePreference) preference;
                // Call this method after the user changes the preference, but before the
                // internal state is set. This allows the client to ignore the user value.
                if (timePreference.callChangeListener(minutesAfterMidnight)) {
                    timePreference.setTime(minutesAfterMidnight);
                }
            }
        }
    }
}
