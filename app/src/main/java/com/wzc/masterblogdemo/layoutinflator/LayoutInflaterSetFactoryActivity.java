package com.wzc.masterblogdemo.layoutinflator;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wzc.masterblogdemo.R;

/**
 * hongyang的文章：
 * https://blog.csdn.net/lmj623565791/article/details/51503977
 * 振兴的文章：
 * http://dandanlove.com/2017/11/15/layoutinflater-factory/
 *
 * @author wzc
 * @date 2018/5/17
 */
public class LayoutInflaterSetFactoryActivity extends AppCompatActivity {
    private static final String TAG = LayoutInflaterSetFactoryActivity.class.getSimpleName();
    public static Typeface sTypeface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (sTypeface == null) {
            sTypeface = Typeface.createFromAsset(getAssets(), "LAKESHOR-webfont.ttf");
        }
        final LayoutInflater inflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(inflater, new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                Log.d(TAG, "onCreateView: parent = " + parent);
                Log.d(TAG, "onCreateView: name = " + name);

                int n = attrs.getAttributeCount();

                for (int i = 0; i < n; i++) {
                    Log.d(TAG, "onCreateView: " + attrs.getAttributeName(i) + "==>" + attrs.getAttributeValue(i));
                }

                View view = null;

                if (name.equals("com.wzc.masterblogdemo.layoutinflator.MyTextView")) {
                    try {
                        view =  inflater.createView(name, null, attrs);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                AppCompatDelegate delegate = getDelegate();
                if (view == null) {
                     view = delegate.createView(parent, name, context, attrs);
                }

                if (view != null && view instanceof TextView) {
                    TextView textView = (TextView) view;
                    // 设置字体样式
                    textView.setTypeface(sTypeface);
                    // 设置字体大小
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30F);
                }
                return view;
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutinflater_setfactory);


    }
}
