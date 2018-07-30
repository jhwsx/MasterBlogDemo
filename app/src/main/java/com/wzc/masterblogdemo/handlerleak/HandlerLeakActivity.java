package com.wzc.masterblogdemo.handlerleak;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzc.masterblogdemo.R;

/**
 * https://droidyue.com/blog/2014/12/28/in-android-handler-classes-should-be-static-or-leaks-might-occur/
 * http://ohmerhe.com/2016/01/18/how-to-work-weakhandler/
 * https://github.com/badoo/android-weak-handlerhttps://github.com/badoo/android-weak-handler
 * @author wzc
 * @date 2018/7/25
 */
public class HandlerLeakActivity extends AppCompatActivity {
    private Handler mLeakyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerleak);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl, new HandlerLeakFragment())
                    .commit();
        }

        mLeakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 60 * 1000 * 10);
    }


    public static class HandlerLeakFragment extends Fragment {



//        private final Handler mHandler = new MyHandler(this);
//        private static class MyHandler extends Handler {
//            private WeakReference<HandlerLeakFragment> mHandlerLeakFragmentWeakReference;
//            MyHandler(HandlerLeakFragment fragment) {
//                mHandlerLeakFragmentWeakReference = new WeakReference<>(fragment);
//            }
//
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                HandlerLeakFragment fragment = mHandlerLeakFragmentWeakReference.get();
//
//
//            }
//        }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
}
