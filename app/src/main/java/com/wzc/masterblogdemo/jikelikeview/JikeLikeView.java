package com.wzc.masterblogdemo.jikelikeview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2019/1/11
 */
public class JikeLikeView extends View {

    private int mLikeNumber;
    private Bitmap mLikeBitmap;
    private Bitmap mUnlikeBitmap;
    private Bitmap mShiningBitmap;
    private Paint mTextPaint;
    private Paint mOldTextPaint;
    private Paint mBitmapPaint;
    private float mHandScale = 1.0f;
    private Rect mTextBounds;
    /**
     * 当前的点赞状态，点赞就是 true，未点赞就是 false
     */
    private boolean mIsLiked;
    /**
     *
     */
    private boolean mIsFirst;
    /**
     * 文字上下移动的最大距离
     */
    private int mTextMaxMove;
    private float mShiningAlpha = 1f;
    /**
     * 文字的透明度系数，这个值会随着动画改变的
     */
    private float mTextAlpha;
    /**
     * 文字上下移动的距离
     */
    private float mTextMoveDistance;
    /**
     * 动画播放的时长
     */
    private long mDuration = 250;
    public void setShiningAlpha(float shiningAlpha) {
        mShiningAlpha = shiningAlpha;
        invalidate();
    }

    public JikeLikeView(Context context) {
        this(context, null);
    }

    public JikeLikeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JikeLikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.JikeLikeView);
        mLikeNumber = ta.getInt(R.styleable.JikeLikeView_like_number, 1999);
//        mIsLiked = ta.getBoolean(R.styleable.JikeLikeView_is_liked, false);
        ta.recycle();
        init();
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.GRAY);
        mTextPaint.setTextSize(Utils.sp2px(getContext(), 14));
        mOldTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOldTextPaint.setColor(Color.GRAY);
        mOldTextPaint.setTextSize(Utils.sp2px(getContext(), 14));
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextBounds = new Rect();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // View 的高度 = 小手图像的高度 + 20dp
        int height = mUnlikeBitmap.getHeight() + 2 * Utils.dp2px(getContext(), 10);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        float textWidth = mTextPaint.measureText(String.valueOf(mLikeNumber));
        // View 的宽度 = 小手图像的宽度 + 文字的宽度 + 30dp
        int width = (int) (mUnlikeBitmap.getWidth() + 3 * Utils.dp2px(getContext(), 10) + textWidth);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int centerY = height / 2;
        Bitmap handBitmap = mIsLiked ? mLikeBitmap : mUnlikeBitmap;
        int handBitmapWidth = handBitmap.getWidth();
        int handBitmapHeight = handBitmap.getHeight();

        int handTop = (height - handBitmapHeight) / 2;
        // 绘制小手
        drawHand(canvas, centerY, handBitmap, handBitmapWidth, handTop);
        // 绘制四点图像
        drawShining(canvas, handBitmapWidth, handTop);
        // 绘制数字文本
        String textValue = String.valueOf(mLikeNumber);
        String textCancelValue;
        if (mIsLiked) {
            textCancelValue = String.valueOf(mLikeNumber - 1);
        } else {
            if (mIsFirst) {
                textCancelValue = String.valueOf(mLikeNumber + 1);
            } else {
                mIsFirst = !mIsFirst;
                textCancelValue = String.valueOf(mLikeNumber);
            }
        }
        int textLength = textValue.length();
        mTextPaint.getTextBounds(textValue, 0, textValue.length(), mTextBounds);
        // 文字起点的 x 坐标
        int textX = handBitmapWidth + Utils.dp2px(getContext(), 20);
        // 文字起点的 y 坐标
        int textY = centerY - (mTextBounds.top + mTextBounds.bottom) / 2;
        // 绘制文字
        if (textLength != textCancelValue.length() || mTextMaxMove == 0) {
            if (mIsLiked) {
                // 根据透明度变化设置透明度, 从 0 到 1 的变化，这里是渐隐效果
                mOldTextPaint.setAlpha((int) (255 * (1 - mTextAlpha)));
                // 绘制之前的数字
                canvas.drawText(textCancelValue, textX, textY - mTextMaxMove + mTextMoveDistance, mOldTextPaint);
                // 设置新数字的透明度
                mTextPaint.setAlpha((int) (255 * mTextAlpha));
                // 绘制新数字（点赞后或者取消点赞）
                canvas.drawText(textValue, textX, textY + mTextMoveDistance, mTextPaint);
            } else {
                mOldTextPaint.setAlpha((int) (255 * (1 - mTextAlpha)));
                canvas.drawText(textCancelValue, textX, textY + mTextMaxMove + mTextMoveDistance, mOldTextPaint);
                mTextPaint.setAlpha((int) (255 * mTextAlpha));
                canvas.drawText(textValue, textX, textY + mTextMoveDistance, mTextPaint);
            }
            return;
        }
    }

    private void drawShining(Canvas canvas, int handBitmapWidth, int handTop) {
        // 顶边：（整个图像高度 - 四点图像高度）/ 2 - 四点图像高度 + 17dp
        int shiningTop = handTop - mShiningBitmap.getHeight() + Utils.dp2px(getContext(), 17);
        mBitmapPaint.setAlpha((int) (255 * mShiningAlpha));
        canvas.save();
        canvas.scale(mShiningAlpha, mShiningAlpha, handBitmapWidth / 2, handTop);
        // 左边：15dp
        int shiningLeft = Utils.dp2px(getContext(), 15);
        canvas.restore();
        mBitmapPaint.setAlpha(255);
        if (mIsLiked) {
            canvas.drawBitmap(mShiningBitmap, shiningLeft, shiningTop, mBitmapPaint);
        } else {
            canvas.save();
            mBitmapPaint.setAlpha(0);
            canvas.drawBitmap(mShiningBitmap, shiningLeft, shiningTop, mBitmapPaint);
            canvas.restore();
            mBitmapPaint.setAlpha(255);
        }
    }

    private void drawHand(Canvas canvas, int centerY, Bitmap handBitmap, int handBitmapWidth, int handTop) {
        canvas.save();
        // 参一：x 向的缩放比例，参二：y 向的缩放比例，参三：缩放中心的 x 坐标，参四：缩放中心的 y 坐标。
        // 使用这样的缩放，缩放后小手的中心不会改变
        canvas.scale(mHandScale, mHandScale, handBitmapWidth / 2, centerY);
        canvas.drawBitmap(handBitmap, Utils.dp2px(getContext(), 10), handTop, mBitmapPaint);
        canvas.restore();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLikeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_message_like);
        mUnlikeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_message_unlike);
        mShiningBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_message_like_shining);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLikeBitmap.recycle();
        mUnlikeBitmap.recycle();
        mShiningBitmap.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                switchLikeState();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void switchLikeState() {
        mIsLiked = !mIsLiked;
        if (mIsLiked) {
            mLikeNumber++;
            setLikeNumber();
        } else {
            mLikeNumber--;
            setLikeNumber();
        }
        invalidate();
    }

    /**
     * 设置数值变化
     */
    private void setLikeNumber() {
        // 开始移动的 y 坐标
        float startY;
        // 最大移动的高度
        mTextMaxMove = Utils.dp2px(getContext(), 20);
        if (mIsLiked) {
            startY = mTextMaxMove;
        } else {
            startY = -mTextMaxMove;
        }
        ObjectAnimator textAlphaAnim = ObjectAnimator.ofFloat(this, "textAlpha", 0f, 1f);
        textAlphaAnim.setDuration(mDuration);
        ObjectAnimator textMoveAnim = ObjectAnimator.ofFloat(this, "textTranslate", startY, 0);
        textMoveAnim.setDuration(mDuration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(textAlphaAnim, textMoveAnim);
        animatorSet.start();
    }

    /**
     * 设置数值透明度
     * @param textAlpha
     */
    public void setTextAlpha(float textAlpha) {
        mTextAlpha = textAlpha;
        invalidate();
    }

    public void setTextTranslate(float textTranslate) {
        mTextMoveDistance = textTranslate;
        invalidate();
    }
}
