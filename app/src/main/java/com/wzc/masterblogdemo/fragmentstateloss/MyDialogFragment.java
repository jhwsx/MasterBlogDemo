package com.wzc.masterblogdemo.fragmentstateloss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/9/3
 */
public class MyDialogFragment extends DialogFragment {
    public static final String TAG = MyDialogFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_dialog, container, false);
        return view;
    }
}
