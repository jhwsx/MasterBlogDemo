package com.wzc.masterblogdemo.dialogfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/6/25
 */
public class MyFragment extends Fragment {


    private View mInflate;

    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.fragment_my, container, false);
        mInflate.setFocusable(true);
        mInflate.setFocusableInTouchMode(true);
        mInflate.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("MyFragment", "onKey: " + keyCode + ", event="+event.toString());
                return false;
            }
        });
        return mInflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}

