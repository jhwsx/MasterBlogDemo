package com.wzc.masterblogdemo.difference;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.wzc.masterblogdemo.R;

/**
 * 参考文章:
 * https://blog.csdn.net/dmk877/article/details/49734869
 *
 * @author wzc
 * @date 2018/6/9
 */
public class GetWidthDifferenceActivity extends AppCompatActivity {
    private static final String TAG = GetWidthDifferenceActivity.class.getSimpleName();
    private TextView mTvSample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_width);
        mTvSample = (TextView) findViewById(R.id.tv_sample);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "mTvSample.getWidth():" + mTvSample.getWidth());
        Log.d(TAG, "mTvSample.getMeasuredWidth():" + mTvSample.getMeasuredWidth());
    }
}
