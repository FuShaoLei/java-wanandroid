package com.example.java_wanandroid.Custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTabHost;

public class CustomNav extends FragmentTabHost {
    private String[] mTabTitle;
    private Fragment[] mFragments;

    public CustomNav(@NonNull Context context) {
        super(context);
    }

    public CustomNav(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNav setBasic(@NonNull Context context, @NonNull FragmentManager manager, int containerId){
        setup(context,manager,containerId);
        return this;
    }
    /**
     * 设置导航的文字
     */
    public CustomNav setTabTitle(String[] TabTitle){//应该有一个判空
        mTabTitle = TabTitle;
        return this;
    }
    /**
     * 设置导航的fragment
     */
    public CustomNav setFragments(Fragment[] Fragments){
        mFragments = Fragments;
        return this;
    }

    /**
     * build
     */
    public void build(){
        if (mTabTitle.length!=mFragments.length) return;
        for (int i = 0;i<mTabTitle.length;i++) {
            this.addTab(this.newTabSpec(mTabTitle[i]).setIndicator(mTabTitle[i]),mFragments[i].getClass(),null);
        }
    }



}
