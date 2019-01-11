package com.wzc.masterblogdemo.coordinatorlayout;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

/**
 * https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650820891&idx=1&sn=ced7264dbc82746907474befec43c23b&scene=38#wechat_redirect
 * 实现跟随移动的行为
 * @author wzc
 * @date 2018/10/18
 */
public class DependentBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = DependentBehavior.class.getSimpleName();
    // 带有参数的这个构造必须要重载，因为在CoordinatorLayout里利用反射去获取这个Behavior的时候就是拿的这个构造。
    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "DependentBehavior: ");
    }

    /**
     * 确定提供的子视图是否具有另一个特定的兄弟视图作为布局依赖关系
     * <p>响应布局请求时，这个方法至少被调用一次。如果对于给定的子View和依赖这个方法返回 true，
     * 那么父 CoordinatorLayout就会：</p>
     * <ol>
     *     <li>总是在依赖View布局后再布局这个子View，不管子View的顺序是什么样的</li>
     *     <li>当依赖View的布局或位置改变时，就会调用 {@link #onDependentViewChanged}</li>
     * </ol>
     * @param parent 给定子View的父View
     * @param child 子View
     * @param dependency 子View推荐的依赖
     * @return 如果子View的布局依赖于推荐依赖的布局，就返回 true；否则返回 false
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, "layoutDependsOn: ");
        return dependency instanceof Button;
    }

    /**
     * 当子View的依赖View改变时回调
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, "onDependentViewChanged: ");
        child.setX(dependency.getX());
        child.setY(dependency.getY() + 200);
        int color = Color.rgb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        child.setBackgroundColor(color);
        return true;
    }

}
