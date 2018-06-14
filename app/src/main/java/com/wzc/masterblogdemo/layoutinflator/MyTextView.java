package com.wzc.masterblogdemo.layoutinflator;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author wzc
 * @date 2018/5/17
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.parseColor("#44ff0000"));
    }
}
