package com.example.java_wanandroid;

import android.view.View;

import com.example.java_wanandroid.Base.BaseFragment;

public class Tab4Fragment extends BaseFragment {
    @Override
    protected void custom() {
        super.custom();
        mRelativeLayout.setVisibility(View.VISIBLE);
        mTitle.setText("我的");
    }
}
