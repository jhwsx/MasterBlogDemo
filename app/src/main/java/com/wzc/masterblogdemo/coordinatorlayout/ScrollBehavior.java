package com.wzc.masterblogdemo.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650820891&idx=1&sn=ced7264dbc82746907474befec43c23b&scene=38#wechat_redirect
 * 实现跟随滚动的行为
 *
 * @author wzc
 * @date 2018/10/18
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = ScrollBehavior.class.getSimpleName();

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 当 CoordinatorLayout 的子类尝试开始嵌套滚动时调用
     * <p>与CoordinatorLayout的任何直接子节点关联的任何行为都可以响应此事件并返回true以指示
     * CoordinatorLayout应该充当此滚动的嵌套滚动父节点。只有从此方法返回true的行为才会收到后续嵌套的滚动事件。
     * <p/>
     *  <p>一次嵌套滚动只调用一次<p/>
     * @param coordinatorLayout 与这个 Behavior 关联的 View 的 CoordinatorLayout 父View
     * @param child             与 Behavior 相关联的 CoordinatorLayout 的子 View
     * @param directTargetChild CoordinatorLayout 的子 View，是或者包含嵌套滚动操作目标
     * @param target            启动嵌套滚动的 CoordinatorLayout 的子 View
     * @param nestedScrollAxes  嵌套滚动使用的轴，水平轴 还是 竖直轴
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        Log.d(TAG, "onStartNestedScroll: coordinatorLayout=" + coordinatorLayout.getTag() + ", child=" + child.getTag()
                + ", directTargetChild=" + directTargetChild.getTag() + ", target=" + target.getTag() + ", nestedScrollAxes=" + nestedScrollAxes);
        if (target.getTag().equals("don't follow me")) {
            return false;
        }
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0; // 这里我们关心竖向滚动
    }

    /**
     * 当 CoordinatorLayout 接受嵌套滚动时被回调
     *  <p>一次嵌套滚动只调用一次<p/>
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     */
    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        Log.d(TAG, "onNestedScrollAccepted: ");
    }

    /**
     * 嵌套滚动结束时回调
     * <p>一次嵌套滚动只调用一次<p/>
     * <p>标明单个嵌套滚动事件结束。这是清理与嵌套滚动相关的任何状态的好地方。<p/>
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        Log.d(TAG, "onStopNestedScroll: ");
    }

    /**
     * 当嵌套滚动正在更新并且目标已滚动或尝试滚动时调用
     * <p>一次嵌套滚动会调用多次<p/>
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d(TAG, "onNestedScroll: ");
    }

    /**
     * 当进展中的嵌套滚动要更新时，在目标消耗任何滚动距离前调用。
     * <p>一次嵌套滚动会调用多次<p/>
     * @param coordinatorLayout
     * @param child             与 Behavior 相关联的 CoordinatorLayout 的子 View
     * @param target            执行嵌套滚动的 CoordinatorLayout的子 View
     * @param dx                用户尝试滚动的原始水平像素数
     * @param dy                用户尝试滚动的原始竖直像素数
     * @param consumed          输出参数。 consume [0]应设置为消耗的dx的距离，消耗[1]应设置为消耗的dy的距离
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        Log.d(TAG, "onNestedPreScroll: coordinatorLayout=" + coordinatorLayout.getTag() + ", child=" + child.getTag()
                + ", target=" + target.getTag() + ", dx=" + dx + ", dy=" + dy + ", consumed[0]=" + consumed[0] + ", consumed[1]=" + consumed[1]);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        int leftScrolled = target.getScrollY();
        child.setScrollY(leftScrolled); // 让 child 的 scrollY 的值等于目标的 scrollY 的值
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target,
                                 float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "onNestedFling: ");
        ((NestedScrollView) child).fling((int) velocityY); // 将现在的y轴上的速度传递传递给child，让child fling起来

        return true;
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target,
                                    float velocityX, float velocityY) {
        Log.d(TAG, "onNestedPreFling: ");

        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }
}
