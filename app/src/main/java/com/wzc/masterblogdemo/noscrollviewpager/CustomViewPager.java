package com.wzc.masterblogdemo.noscrollviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 这是不好的做法：
 * 导致 setCurrentItem 不能使用
 * 导致 ViewPager 不动，但是上面的 tab 却可以动
 * @author wzc
 * @date 2018/9/9
 */
public class CustomViewPager extends ViewPager {
    private boolean isScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isScroll() {
        return isScroll;
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    @Override
    public void scrollTo(int x, int y){
        if (isScroll){
            super.scrollTo(x, y);
        }
    }

}
