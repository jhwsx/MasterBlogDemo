package com.wzc.masterblogdemo.coordinatorlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/10/18
 */
public class DependentBehaviorActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, DependentBehaviorActivity.class);
        context.startActivity(starter);
    }
    private float mLastX;
    private float mLastY;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependent_behavior);
        final View view = findViewById(R.id.tv_dependent);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float rawX = event.getRawX();
                float rawY = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mLastX = rawX;
                        mLastY = rawY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float deltaX = rawX - mLastX;
                        float deltaY = rawY - mLastY;

                        float translationX = view.getTranslationX();
                        view.setTranslationX(translationX + deltaX);

                        float translationY = view.getTranslationY();
                        view.setTranslationY(translationY + deltaY);

                        mLastX = rawX;
                        mLastY = rawY;
                        break;
                    case MotionEvent.ACTION_UP:
                        mLastX = rawX;
                        mLastY = rawY;
                        break;
                    default:
                        break;

                }
                return true;
            }
        });
    }
}
