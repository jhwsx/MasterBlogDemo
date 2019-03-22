package com.wzc.masterblogdemo.jikelikeview;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author wzc
 * @date 2019/1/11
 */
public class Utils {
    private Utils() {

    }

    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
