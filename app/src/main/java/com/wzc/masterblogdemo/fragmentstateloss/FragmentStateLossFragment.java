package com.wzc.masterblogdemo.fragmentstateloss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzc.masterblogdemo.R;

/**
 * @author wzc
 * @date 2018/9/3
 */
public class FragmentStateLossFragment extends Fragment {
    public static final String TAG = "MyTag";

    public static FragmentStateLossFragment newInstance() {
        Bundle args = new Bundle();
        FragmentStateLossFragment fragment = new FragmentStateLossFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "FragmentStateLossFragment onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            int number = savedInstanceState.getInt("number");
            Log.d(TAG, "FragmentStateLossFragment onCreate: number = " + number);
        }
        Log.d(TAG, "FragmentStateLossFragment onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stateloss, container, false);
        Log.d(TAG, "FragmentStateLossFragment onCreateView: ");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mMyHandler.sendMessageDelayed(mMyHandler.obtainMessage(), 3000);
                mMyPauseHandler.sendMessageDelayed(mMyPauseHandler.obtainMessage(), 3000);
            }
        });
        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(getActivity(), AnotherActivity.class),1000 );
            }
        });
    }

    private MyPauseHandler mMyPauseHandler = new MyPauseHandler();
    private MyHandler mMyHandler = new MyHandler();
    // 会报出异常的 Handler
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentManager fragmentManager = getFragmentManager();
            MyDialogFragment myDialogFragment = new MyDialogFragment();
            myDialogFragment.show(fragmentManager,MyDialogFragment.TAG);
//            if (!isResumed()) {
//                Log.d(TAG, "handleMessage: isResumed() 为 false，不弹出 Dialog");
//                return;
//            }
//            if (getActivity() == null) {
//                Log.d(TAG, "handleMessage: getActivity() 为 null，不弹出 Dialog");
//                return;
//            }
//
//            if (getActivity().isFinishing()) {
//                Log.d(TAG, "handleMessage: getActivity().isFinishing() 为true 不弹出 Dialog");
//                return;
//            }
//
//            Log.d(TAG, "handleMessage: 弹出 Dialog");
//            AlertDialog dialog = new AlertDialog.Builder(getActivity())
//                    .setTitle("Dialog")
//                    .setMessage("Desc")
//                    .create();
//            dialog.show();
        }
    }

    class MyPauseHandler extends PauseHandler {
        @Override
        protected boolean storeMessage(Message message) {
            return true;
        }

        @Override
        protected void processMessage(Message message) {
            FragmentManager fragmentManager = getFragmentManager();
            MyDialogFragment myDialogFragment = new MyDialogFragment();
            myDialogFragment.show(fragmentManager,MyDialogFragment.TAG);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "FragmentStateLossFragment onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "FragmentStateLossFragment onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "FragmentStateLossFragment onResume: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("number", 5);
        super.onSaveInstanceState(outState);
        Log.d(TAG, "FragmentStateLossFragment onSaveInstanceState: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "FragmentStateLossFragment onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "FragmentStateLossFragment onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "FragmentStateLossFragment onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "FragmentStateLossFragment onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "FragmentStateLossFragment onDetach: ");
    }

}
