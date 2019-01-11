package com.wzc.masterblogdemo.jikelikeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
    private Paint mBitmapPaint;
    private boolean mIsLike;
    private float mHandScale = 1.0f;

    public void setShiningAlpha(float shiningAlpha) {
        mShiningAlpha = shiningAlpha;
        invalidate();
    }

    private float mShiningAlpha = 1f;
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
        ta.recycle();
        init();
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // View 的高度 = 小手图像的高度 + 20dp
        int height = (int) (mLikeBitmap.getHeight() + 2 * Utils.dp2px(getContext(), 10));
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        float textWidth = mTextPaint.measureText(String.valueOf(mLikeNumber));
        // View 的宽度 = 小手图像的宽度 + 文字的宽度 + 30dp
        int width = (int) (mLikeBitmap.getWidth() + 3 * Utils.dp2px(getContext(), 10) + textWidth);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int centerY = height / 2;
        Bitmap handBitmap = mIsLike ? mLikeBitmap : mUnlikeBitmap;
        int handBitmapWidth = handBitmap.getWidth();
        int handBitmapHeight = handBitmap.getHeight();

        int handTop = centerY - handBitmapHeight / 2;
        // 绘制小手
        drawHand(canvas, centerY, handBitmap, handBitmapWidth, handTop);
        // 绘制四点图像
        drawShining(canvas, handBitmapWidth, handTop);
        // 绘制数字文本
        String textValue = String.valueOf(mLikeNumber);

    }

    private void drawShining(Canvas canvas, int handBitmapWidth, int handTop) {
        // 顶边：（整个图像高度 - 四点图像高度）/ 2 - 四点图像高度 + 17dp
        int shiningTop = (int) (handTop - mShiningBitmap.getHeight() + Utils.dp2px(getContext(), 17));
        mBitmapPaint.setAlpha((int) (255 * mShiningAlpha));
        canvas.save();
        canvas.scale(mShiningAlpha, mShiningAlpha, handBitmapWidth / 2, handTop);
        // 左边：15dp
        int shiningLeft = (int) Utils.dp2px(getContext(), 15);
        canvas.drawBitmap(mShiningBitmap, shiningLeft, shiningTop, mBitmapPaint);
        canvas.restore();
        mBitmapPaint.setAlpha(255);
    }

    private void drawHand(Canvas canvas, int centerY, Bitmap handBitmap, int handBitmapWidth, int handTop) {
        canvas.save();
        canvas.scale(mHandScale, mHandScale, handBitmapWidth / 2, centerY);
        canvas.drawBitmap(handBitmap, Utils.dp2px(getContext(), 10), handTop, mBitmapPaint);
        canvas.restore();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLikeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        mUnlikeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_unselected);
        mShiningBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected_shining);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLikeBitmap.recycle();
        mUnlikeBitmap.recycle();
        mShiningBitmap.recycle();
    }


}
