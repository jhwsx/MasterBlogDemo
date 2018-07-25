package com.wzc.masterblogdemo.canvas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * https://www.jianshu.com/p/d5ed6b2eacba
 * @author wzc
 * @date 2018/7/23
 */
public class WhereAreCanvasFromActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        setContentView(new View(this){
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawLine(0,0,getWidth(), getHeight(), paint);
            }
        });
    }
}
