package com.example.java_wanandroid.NetWork;

import com.example.java_wanandroid.Bean.ArticleDataRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("article/list/{pageNum}/json")
    Call<ArticleDataRes> getArticleData(@Path("pageNum") int pageNum);
}
