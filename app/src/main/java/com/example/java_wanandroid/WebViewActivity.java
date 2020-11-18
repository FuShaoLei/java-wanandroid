package com.example.java_wanandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.java_wanandroid.Base.BaseActivity;
import com.example.java_wanandroid.Bean.Article;
import com.example.java_wanandroid.Util.MyURL;

import androidx.annotation.Nullable;

@Route(path = MyURL.WEB_VIEW_PATH)
public class WebViewActivity extends BaseActivity {

    @Autowired(name = "title")
    String articleTitle = "啥也没有啊";
    @Autowired(name = "link")
    String articleLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);

        if (articleLink !=null && articleTitle !=null){
            Log.e("=====",articleTitle+"  "+articleLink);
            mWebView.setVisibility(View.VISIBLE);
            mWebView.getSettings().setJavaScriptEnabled(true);//启用js
            mWebView.getSettings().setBlockNetworkImage(false);//解决图片不显示
            resetView();
        }else {
            Log.e("===========","都特么的是null");
        }
    }

    @Override
    protected void custom() {
        super.custom();

        mRelativeLayout.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.VISIBLE);
        mLeftImageView.setVisibility(View.VISIBLE);



    }

    private void resetView() {
        mTitle.setText(articleTitle);
        mWebView.loadUrl(articleLink);

        mLeftImageView.setImageResource(R.drawable.left_arrow);
        mLeftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
