package com.wzc.masterblogdemo.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/10/18
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coorinator_layout);
    }

    public void dependentBehavior(View view) {
        DependentBehaviorActivity.start(this);
    }

    public void scrollBehavior(View view) {
        ScrollBehaviorActivity.start(this);
    }

    public void fab(View view) {
        FabActivity.start(this);
    }

    public void tablayout(View view) {
        TabLayoutActivity.start(this);
    }

    public void collapsingtoolbarlayout(View view) {
        CollapsingToolbarLayoutActivity.start(this);
    }
}
