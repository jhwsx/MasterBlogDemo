package com.wzc.masterblogdemo.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/5/28
 */
public class TimePreference extends DialogPreference {

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, defStyleAttr);
    }

    public TimePreference(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimePreference(Context context) {
        this(context,null);
    }

    private int mTime;
    private int mDialogLayoutResId = R.layout.pref_dialog_time;

    public int getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
        // Save to sp.
        persistInt(time);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
//        return super.onGetDefaultValue(a, index);
        return a.getInt(index, 0);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
//        super.onSetInitialValue(restorePersistedValue, defaultValue);
        setTime(restorePersistedValue ? getPersistedInt(mTime) : (int) defaultValue);
    }

    // 为dialog设置layout resource
    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResId;
    }
}
