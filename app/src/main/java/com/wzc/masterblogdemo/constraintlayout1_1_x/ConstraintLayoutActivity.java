package com.wzc.masterblogdemo.constraintlayout1_1_x;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wzc.masterblogdemo.R;


/**
 * https://constraintlayout.com/
 *
 * @author wzc
 * @date 2018/6/14
 */
public class ConstraintLayoutActivity extends AppCompatActivity {

    private Group mGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayout);
        
        mGroup = (Group) findViewById(R.id.group);

    }

    public void hideshowProfile(View view) {
        // 设置 View.INVISIBLE 在代码里却不生效
        int visibility = mGroup.getVisibility();
        switch (visibility) {
            case View.VISIBLE:
                mGroup.setVisibility(View.INVISIBLE);
                break;
            case View.INVISIBLE:
                mGroup.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }
}
