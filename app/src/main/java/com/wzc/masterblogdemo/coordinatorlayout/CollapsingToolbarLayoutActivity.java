package com.wzc.masterblogdemo.coordinatorlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/11/1
 */
public class CollapsingToolbarLayoutActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, CollapsingToolbarLayoutActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsingtoolbarlayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
