package com.wzc.masterblogdemo.coordinatorlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author wzc
 * @date 2018/10/25
 */
public class MyRecyclerView extends RecyclerView {
    private static final String TAG = MyRecyclerView.class.getSimpleName();
    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        Log.d(TAG, "isNestedScrollingEnabled: ");
        return super.isNestedScrollingEnabled();
    }
    //
    @Override
    public boolean startNestedScroll(int axes) {
        boolean startNestedScroll = super.startNestedScroll(axes);
        Log.d(TAG, "startNestedScroll: " + startNestedScroll);
        return startNestedScroll;
    }

    @Override
    public void stopNestedScroll() {
        Log.d(TAG, "stopNestedScroll: ");
        super.stopNestedScroll();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        Log.d(TAG, "dispatchNestedScroll: ");
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.d(TAG, "dispatchNestedPreScroll: ");
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "dispatchNestedFling: ");
        return super.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.d(TAG, "dispatchNestedPreFling: ");
        return super.dispatchNestedPreFling(velocityX, velocityY);
    }
}
