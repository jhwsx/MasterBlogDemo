package com.wzc.masterblogdemo.textwachter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wzc.masterblogdemo.R;

/**
 * https://blog.csdn.net/zhuwentao2150/article/details/51546773
 * @author wzc
 * @date 2018/6/30
 */
public class TextWatcherActivity extends AppCompatActivity {
    private static final String TAG = TextWatcherActivity.class.getSimpleName();
    private TextView mTvCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textwatcher);

        EditText editText = (EditText) findViewById(R.id.editText);
        mTvCount = (TextView) findViewById(R.id.tv_count);

        editText.addTextChangedListener(new TextWatcher() {
            /**
             * 在文本改变之前调用
             * 在当前文本s中，从start位置开始之后的count个字符（即将）要被after个字符替换掉
             * @param s 文本改变之前的内容
             * @param start 文本开始改变时的起点位置, 从 0 开始计算
             * @param count 要被改变的文本字数, 即要被替代的选中文本字数
             * @param after 改变后添加的文本字数，即替代选中文本后的文本字数
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: s = " + s + ", start = " + start + ", count = " + count + ", after = " + after);
            }

            /**
             * 在文本改变时调用
             * 在当前文本s中，从start位置开始之后的before个字符（已经）被count个字符替换掉了
             * @param s 文本改变之后的内容
             * @param start 文本开始改变时的起点位置, 从 0 开始计算
             * @param before 要被改变的文本字数，即已经被替代的选中文本字数
             * @param count 改变后添加的文本字数，即替代选中文本后的文本字数
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: s = " + s + ", start = " + start + ", before = " + before + ", count = " + count);
                mTvCount.setText(s.length() + "/50");
                if (s.length() == 50) {
                    Toast.makeText(TextWatcherActivity.this, "您输入的字数已经超过50个了", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * 在文本改变结束后调用
             * @param s 改变后的最终文本
             */
            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: s = " + s);
            }
        });
    }
}
