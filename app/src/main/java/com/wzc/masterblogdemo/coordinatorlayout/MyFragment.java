package com.wzc.masterblogdemo.coordinatorlayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wzc.masterblogdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2018/10/18
 */
public class MyFragment extends Fragment {

    private MyRecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutInflater = LayoutInflater.from(getActivity());
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
        mRecyclerView.setAdapter(new MyAdapter());
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mData.add("Item" + i);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.bindItem(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            private TextView mTextView;
            public MyHolder(View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(android.R.id.text1);
            }

            public void bindItem(String s) {
                mTextView.setText(s);
            }
        }
    }
}
