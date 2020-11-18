package com.example.java_wanandroid;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.java_wanandroid.Base.BaseActivity;
import com.example.java_wanandroid.Util.MyURL;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

@Route(path = MyURL.MAIN_PATH)
public class MainActivity extends BaseActivity {
    private Fragment[] mFragments;
    private String[] mTabTitles;
    private int[] mTabIcon;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void custom() {
        super.custom();
        mMyCustomNav.setVisibility(View.VISIBLE);
        mFrameLayout.setVisibility(View.VISIBLE);

        initData();

        mMyCustomNav.setBasic(this, getSupportFragmentManager(), R.id.frame_layout)
                .setItemLayout(R.layout.item_tab, R.id.tab_img, R.id.tab_title)
                .setColor("#DB5860", "#E3E3E3")
                .setIcon(mTabIcon)
                .setTabTitle(mTabTitles)
                .setFragments(mFragments)
                .build();

    }

    private void initData() {
        mFragments = new Fragment[]{new Tab1Fragment(), new Tab2Fragment(), new Tab3Fragment(), new Tab4Fragment()};
        mTabTitles = new String[]{"首页", "项目", "公众号", "我的"};
        mTabIcon = new int[]{R.drawable.home_icon, R.drawable.project_icon, R.drawable.gongzonghao_icon, R.drawable.wode_icon};
    }
}