package com.example.java_wanandroid.Base;

import com.example.java_wanandroid.CommonFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;
    private List<Integer> mNumbers;

    public BasePagerAdapter(@NonNull FragmentManager fm,List<String> titlelists,List<Integer> numLists) {
        super(fm);
        this.mTitles = titlelists;
        this.mNumbers = numLists;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return CommonFragment.getInstance(mNumbers.get(position));
    }

    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
