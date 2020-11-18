package com.example.java_wanandroid.Util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.java_wanandroid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTabHost;

public class MyCustomNav extends FragmentTabHost implements TabHost.OnTabChangeListener {
    private String[] mTabTitle;
    private Fragment[] mFragments;
    private int[] mTabIcon;
    private int mLayout;
    private int mImage;
    private int mTitle;
    private String mSelectedColor = "#1177BB";//选中的颜色
    private String mUnSelectedColor = "#333333";//未选中的颜色

    public MyCustomNav(@NonNull Context context) {
        super(context);
    }

    public MyCustomNav(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomNav setBasic(@NonNull Context context, @NonNull FragmentManager manager, int containerId) {
        setup(context, manager, containerId);
        return this;
    }

    /**
     * 设置导航的文字
     */
    public MyCustomNav setTabTitle(String[] TabTitle) {//应该有一个判空
        mTabTitle = TabTitle;
        return this;
    }

    /**
     * 设置选中和未选中的颜色
     * 默认
     * 选中颜色为蓝色
     * 未选中颜色未黑色
     */
    public MyCustomNav setColor(String selectedColor, String unSelectedColor) {
        mSelectedColor = selectedColor;
        mUnSelectedColor = unSelectedColor;
        return this;
    }

    /**
     * 设置布局文件layout
     * 以及布局文件里的图片和文字的id
     */
    public MyCustomNav setItemLayout(int Layout, int Image, int Title) {
        mLayout = Layout;
        mImage = Image;
        mTitle = Title;
        return this;
    }

    /**
     * 设置图标
     */
    public MyCustomNav setIcon(int[] TabIcon) {
        mTabIcon = TabIcon;
        return this;
    }

    /**
     * 设置导航的fragment
     */
    public MyCustomNav setFragments(Fragment[] Fragments) {
        mFragments = Fragments;
        return this;
    }

    /**
     * build
     */
    public void build() {
        if (mTabTitle.length != mFragments.length) return;

        if (mLayout == 0 || mTabIcon.length != mTabTitle.length) {
            for (int i = 0; i < mTabTitle.length; i++) {
                TabSpec tabSpec = this.newTabSpec(mTabTitle[i]).setIndicator(mTabTitle[i]);
                this.addTab(tabSpec, mFragments[i].getClass(), null);
            }
        } else {
            for (int i = 0; i < mTabTitle.length; i++) {
                TabSpec tabSpec = this.newTabSpec(mTabTitle[i]).setIndicator(getTabView(i));
                this.addTab(tabSpec, mFragments[i].getClass(), null);
            }
            updateTabStatus();
        }
    }

    private View getTabView(int index) {
        View tabView = LayoutInflater.from(getContext()).inflate(mLayout, null);
        ImageView imageView = tabView.findViewById(R.id.tab_img);
        TextView textView = tabView.findViewById(R.id.tab_title);
        imageView.setImageResource(mTabIcon[index]);
        textView.setText(mTabTitle[index]);

        return tabView;
    }

    /**
     * 在此重设监听
     */
    @Override
    public void onTabChanged(@Nullable String tabId) {
        super.onTabChanged(tabId);
        if (mLayout != 0) {
            updateTabStatus();
        }
    }


    private void updateTabStatus() {
        TabWidget tabWidget = this.getTabWidget();
        for (int i = 0; i < tabWidget.getTabCount(); i++) {
            View childTabViewAt = tabWidget.getChildTabViewAt(i);
            ImageView tabIcon = childTabViewAt.findViewById(mImage);
            TextView tabText = childTabViewAt.findViewById(mTitle);

            if (i == this.getCurrentTab()) {
                // 选中的tab时
                tabIcon.setColorFilter(Color.parseColor(mSelectedColor));
                tabText.setTextColor(Color.parseColor(mSelectedColor));
            } else {
                // 未选中的tab
                tabIcon.setColorFilter(Color.parseColor(mUnSelectedColor));
                tabText.setTextColor(Color.parseColor(mUnSelectedColor));
            }
        }
    }

}
