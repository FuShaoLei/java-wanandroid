package com.example.java_wanandroid.Base;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.java_wanandroid.R;
import com.example.java_wanandroid.Util.MyCustomNav;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class BaseActivity  extends FragmentActivity {
    protected RelativeLayout mRelativeLayout;

    protected TextView mTitle;
    protected ImageView mLeftImageView;
    protected ImageView mRightImageView;

    protected FrameLayout mFrameLayout;
    protected MyCustomNav mMyCustomNav;
    protected WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        custom();
    }

    protected void custom() {

    }

    private void initView() {
        mRelativeLayout = findViewById(R.id.rl_layout);

        mTitle = findViewById(R.id.tv_title);
        mLeftImageView = findViewById(R.id.iv_left);
        mRightImageView = findViewById(R.id.iv_right);

        mFrameLayout = findViewById(R.id.frame_layout);
        mMyCustomNav = findViewById(R.id.custom_nav);

        mWebView = findViewById(R.id.web_view);
    }
}
