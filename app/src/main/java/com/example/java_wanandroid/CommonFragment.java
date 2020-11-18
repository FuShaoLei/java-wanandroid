package com.example.java_wanandroid;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.java_wanandroid.Base.BaseAdapter;
import com.example.java_wanandroid.Base.BaseFragment;
import com.example.java_wanandroid.Base.NewBaseAdapter;
import com.example.java_wanandroid.Bean.Article;
import com.example.java_wanandroid.Bean.ArticleData;
import com.example.java_wanandroid.Bean.ArticleDataRes;
import com.example.java_wanandroid.Bean.PrimaryArticleDirectoryRes;
import com.example.java_wanandroid.NetWork.ApiInterface;
import com.example.java_wanandroid.Util.MyRetrofitManager;
import com.example.java_wanandroid.Util.MyRxJavaUtil;
import com.example.java_wanandroid.Util.MyURL;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * viewpager通用fragment
 */
public class CommonFragment extends BaseFragment {
    private List<Article> mLists = new ArrayList<>();
    private NewBaseAdapter mAdapter;
    private static final String CID = "director_cid";
    private int cid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void custom() {
        super.custom();

        mRecyclerView.setVisibility(View.VISIBLE);

        Bundle bundle = getArguments();
        cid = bundle.getInt(CID,0);
        if (cid == 0) {
            return;
        }
        mAdapter = new NewBaseAdapter(R.layout.item_main,mLists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Article article = mLists.get(position);
                ARouter.getInstance().build(MyURL.WEB_VIEW_PATH)
                                        .withString("title",article.getTitle())
                                        .withString("link",article.getLink())
                                        .navigation();
            }
        });

        initData();
    }

    private void initData() {
        Observable<ArticleDataRes> observable
                = MyRetrofitManager.getInstance().createRs(ApiInterface.class)
                .getProectTreeDetailArticleData(0,cid);
        observable = MyRxJavaUtil.toSubscribe(observable);
        observable.subscribe(new Observer<ArticleDataRes>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArticleDataRes articleDataRes) {
                ArticleData  articleData = articleDataRes.getData();
                mLists = articleData.getDatas();
//                mAdapter.resetData(mLists);
                mAdapter.setNewData(mLists);
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

    public static CommonFragment getInstance(int cid){
        CommonFragment fragment = new CommonFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CID,cid);
        fragment.setArguments(bundle);
        return fragment;
    }
}
