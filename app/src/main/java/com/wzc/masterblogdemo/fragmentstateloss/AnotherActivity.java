package com.wzc.masterblogdemo.fragmentstateloss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/9/3
 */
public class AnotherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }
}
