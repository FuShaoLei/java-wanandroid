package com.example.java_wanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.view.MenuItem;



public class MainActivity extends FragmentActivity {

    private Fragment[] mFragments;
    private String[] mTabTitles;

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initBottomNav();
    }

    private void initView() {
        mTabHost = findViewById(R.id.fragment_tab_host);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.frame_layout);
    }

    private void initBottomNav() {
        for (int i = 0;i<4;i++){
            mTabHost.addTab(mTabHost.newTabSpec(mTabTitles[i]).setIndicator(mTabTitles[i]),mFragments[i].getClass(),null);
        }
    }

    private void initData() {
        mFragments = new Fragment[4];
        mFragments[0] = new Tab1Fragment();
        mFragments[1] = new Tab2Fragment();
        mFragments[2] = new Tab3Fragment();
        mFragments[3] = new Tab4Fragment();

        mTabTitles = new String[4];
        mTabTitles[0] = "首页";
        mTabTitles[1] = "项目";
        mTabTitles[2] = "广场";
        mTabTitles[3] = "公众号";
    }

}