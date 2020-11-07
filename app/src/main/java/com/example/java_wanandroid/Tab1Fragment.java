package com.example.java_wanandroid;

import android.content.Context;
import android.view.View;

import com.example.java_wanandroid.Base.BaseAdapter;
import com.example.java_wanandroid.Base.BaseFragment;
import com.example.java_wanandroid.Bean.Article;
import com.example.java_wanandroid.Bean.ArticleData;
import com.example.java_wanandroid.Bean.ArticleDataRes;
import com.example.java_wanandroid.NetWork.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tab1Fragment extends BaseFragment {
    private Context mContext;
    private List<Article> mList;
    private ArticleDataRes articleDataRes;
    private ArticleData articleData;

    BaseAdapter mAdapter;
    @Override
    protected void custom() {
        super.custom();
        mRelativeLayout.setVisibility(View.VISIBLE);
        mTitle.setText("首页");

        initData();


        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new BaseAdapter(getContext(),R.layout.item_main,mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {
        mList = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://www.wanandroid.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<ArticleDataRes> call = apiInterface.getArticleData(0);
        call.enqueue(new Callback<ArticleDataRes>() {
            @Override
            public void onResponse(Call<ArticleDataRes> call, Response<ArticleDataRes> response) {
                 articleDataRes = response.body();
                 articleData = articleDataRes.getData();
                 mList = articleData.getDatas();
                 mAdapter.resetData(mList);
            }

            @Override
            public void onFailure(Call<ArticleDataRes> call, Throwable t) {

            }
        });


    }
}
