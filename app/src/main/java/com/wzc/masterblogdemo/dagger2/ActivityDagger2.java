package com.wzc.masterblogdemo.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/6/2
 */
public class ActivityDagger2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
    }
}
