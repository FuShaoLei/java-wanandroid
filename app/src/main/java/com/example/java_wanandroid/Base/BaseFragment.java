package com.example.java_wanandroid.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.java_wanandroid.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

/**
 * 自定义可复用的fragment
 */
public abstract class BaseFragment extends Fragment {
    private View mView;

    protected RelativeLayout mRelativeLayout;

    protected TextView mTitle;
    protected ImageView mLeftImageView;
    protected ImageView mRightImageView;

    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;

    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main,container,false);

        initView();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        custom();
    }

    /**
     * 可自定义方法
     */
    protected void custom() {

    }


    private void initView() {
        mRelativeLayout = mView.findViewById(R.id.rl_layout);
        mTitle = mView.findViewById(R.id.tv_title);
        mLeftImageView = mView.findViewById(R.id.iv_left);
        mRightImageView = mView.findViewById(R.id.iv_right);
        mTabLayout = mView.findViewById(R.id.tab_layout);
        mViewPager = mView.findViewById(R.id.view_pager);
        mRecyclerView = mView.findViewById(R.id.recycler_view);
    }


}
