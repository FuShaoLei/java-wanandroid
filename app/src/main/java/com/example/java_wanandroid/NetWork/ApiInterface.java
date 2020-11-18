package com.example.java_wanandroid.NetWork;

import com.example.java_wanandroid.Bean.ArticleDataRes;
import com.example.java_wanandroid.Bean.PrimaryArticleDirectoryRes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("article/list/{pageNum}/json")
    Observable<ArticleDataRes> getArticleData(@Path("pageNum") int pageNum);

    /*
   获取项目分类
 */
    @GET("project/tree/json")
    Observable<PrimaryArticleDirectoryRes> getProjectTreeData();

    /*
      获取某个项目分类的项目
      参数介绍：
      pageNum: 页码从1开始
      cid:项目的分类id

     */
    @GET("project/list/{pageNum}/json")
    Observable<ArticleDataRes> getProectTreeDetailArticleData(@Path("pageNum") int pageNum, @Query("cid") int cid);
}
