package com.example.java_wanandroid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.java_wanandroid.Base.BaseAdapter;
import com.example.java_wanandroid.Base.BaseFragment;
import com.example.java_wanandroid.Base.NewBaseAdapter;
import com.example.java_wanandroid.Bean.Article;
import com.example.java_wanandroid.Bean.ArticleData;
import com.example.java_wanandroid.Bean.ArticleDataRes;
import com.example.java_wanandroid.NetWork.ApiInterface;
import com.example.java_wanandroid.Util.MyRetrofitManager;
import com.example.java_wanandroid.Util.MyRxJavaUtil;
import com.example.java_wanandroid.Util.MyURL;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Route(path = MyURL.HOME_FRAGMENT)
public class Tab1Fragment extends BaseFragment {
    private Context mContext;
    private List<Article> mList;
    private ArticleDataRes mArticleDataRes;
    private ArticleData mArticleData;

//    private BaseAdapter mAdapter;
    private NewBaseAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void custom() {
        super.custom();
        mRelativeLayout.setVisibility(View.VISIBLE);
        mTitle.setText("首页");

        initData();


        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new NewBaseAdapter(R.layout.item_main,mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Article article = mList.get(position);
                Log.e("=====","点击了第"+position+"个item 它的信息是 =="+article.getTitle()+"== "+article.getLink());
                ARouter.getInstance().build(MyURL.WEB_VIEW_PATH)
                        .withString("title",article.getTitle())
                        .withString("link",article.getLink())
                        .navigation();
            }
        });

    }

    private void initData() {
        mList = new ArrayList<>();

        Observable<ArticleDataRes> observable = MyRetrofitManager.getInstance()
                                                .createRs(ApiInterface.class)
                                                .getArticleData(0);

        observable = MyRxJavaUtil.toSubscribe(observable);

        observable.subscribe(new Observer<ArticleDataRes>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArticleDataRes articleDataRes) {
                Log.e("===","success");
                mArticleData = articleDataRes.getData();
                mList = mArticleData.getDatas();
//                mAdapter.resetData(mList);
                mAdapter.setNewData(mList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
