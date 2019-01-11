package com.wzc.masterblogdemo.coordinatorlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/10/18
 */
public class ScrollBehaviorActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ScrollBehaviorActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_behavior);

    }
}
