package com.wzc.masterblogdemo.noscrollviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author wzc
 * @date 2018/9/9
 */
public class MyViewPager extends ViewPager {
    private static final String TAG = MyViewPager.class.getSimpleName();

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        Log.d(TAG, "canScroll: v = " + v + ", checkV = " + checkV + ", dx = " +dx + ", x = " + x + ", y = " + y);
        return false;
    }
}
