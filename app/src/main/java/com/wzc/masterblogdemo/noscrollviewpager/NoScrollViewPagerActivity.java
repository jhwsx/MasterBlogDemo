package com.wzc.masterblogdemo.noscrollviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wzc.masterblogdemo.R;

/**
 * 徐医生的文章：https://www.jianshu.com/p/80891d0185f7
 * @author wzc
 * @date 2018/9/9
 */
public class NoScrollViewPagerActivity extends AppCompatActivity {

    private MyViewPager mNoScrollViewPager;
    private TabLayout mTabLayout;
    private String[] mTitles = {"社会","推荐","历史","健康","军事","体育","佛学"};
    private NoScrollViewPager mCustomViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noscrollviewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mCustomViewPager = (NoScrollViewPager) findViewById(R.id.noscrollviewpager);
        mCustomViewPager.setAdapter(new MyPagerAdapter());
        mTabLayout.setupWithViewPager(mCustomViewPager);


    }

    private class MyPagerAdapter extends PagerAdapter {
        // 获取要滑动的控件的数量
        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            TextView textView = new TextView(NoScrollViewPagerActivity.this);
            textView.setText(mTitles[position]);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(NoScrollViewPagerActivity.this, mTitles[position], Toast.LENGTH_SHORT).show();
                }
            });
            textView.setGravity(Gravity.CENTER);
            FrameLayout fl = new FrameLayout(NoScrollViewPagerActivity.this);
            fl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(NoScrollViewPagerActivity.this, "布局：" + mTitles[position], Toast.LENGTH_SHORT).show();
                }
            });
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            fl.addView(textView, params);
            container.addView(fl);
            return fl;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        // 判断是否是同一个 View
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_noscrollviewpager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_scroll) {
            mCustomViewPager.setScroll(!mCustomViewPager.isScroll());
            Toast.makeText(this, mCustomViewPager.isScroll() ? "scroll" : "no scroll", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_set_current_item) {
            mCustomViewPager.setCurrentItem(4);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
