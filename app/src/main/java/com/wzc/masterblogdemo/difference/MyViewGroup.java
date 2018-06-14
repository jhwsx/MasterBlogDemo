package com.wzc.masterblogdemo.difference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author wzc
 * @date 2018/6/10
 */
public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        View view = getChildAt(0);
//
//        measureChild(view, MeasureSpec.EXACTLY + 50, MeasureSpec.EXACTLY + 100);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View view = getChildAt(0);

        view.layout(0,0,200,200);
    }
}
