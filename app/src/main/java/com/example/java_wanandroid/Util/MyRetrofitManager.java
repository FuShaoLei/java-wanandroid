package com.example.java_wanandroid.Util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit单例工具类
 */
public class MyRetrofitManager {
    private Retrofit mRetrofit;

    private MyRetrofitManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(MyURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static MyRetrofitManager getInstance(){
        return Inner.manager;
    }

    private static class Inner{
        private static final MyRetrofitManager manager = new MyRetrofitManager();
    }



    public <T> T createRs(Class<T> ser){
        return mRetrofit.create(ser);
    }
}
