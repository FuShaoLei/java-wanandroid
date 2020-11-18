package com.example.java_wanandroid;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.java_wanandroid.Base.BaseFragment;
import com.example.java_wanandroid.Base.BasePagerAdapter;
import com.example.java_wanandroid.Bean.PrimaryArticleDirectory;
import com.example.java_wanandroid.Bean.PrimaryArticleDirectoryRes;
import com.example.java_wanandroid.NetWork.ApiInterface;
import com.example.java_wanandroid.Util.MyRetrofitManager;
import com.example.java_wanandroid.Util.MyRxJavaUtil;
import com.example.java_wanandroid.Util.MyURL;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
@Route(path = MyURL.PROJECT_FRAGMENT)
public class Tab2Fragment extends BaseFragment {
    private List<String> mTitles = new ArrayList<>();
    private List<Integer> mNums = new ArrayList<>();
    private BasePagerAdapter mAdapter;
    private List<PrimaryArticleDirectory> primaryArticleDirectoryList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void custom() {
        super.custom();
        mRelativeLayout.setVisibility(View.VISIBLE);
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);

        mTitle.setText("项目");

        initData();
    }

    private void initData() {
        Observable<PrimaryArticleDirectoryRes> observable
                = MyRetrofitManager.getInstance()
                .createRs(ApiInterface.class)
                .getProjectTreeData();
        observable = MyRxJavaUtil.toSubscribe(observable);
        observable.subscribe(new Observer<PrimaryArticleDirectoryRes>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PrimaryArticleDirectoryRes primaryArticleDirectoryRes) {
                initTabLayout(primaryArticleDirectoryRes.getData());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    private void initTabLayout(List<PrimaryArticleDirectory> directories){
        for (PrimaryArticleDirectory p : directories){
            mTitles.add(p.getName());
            mNums.add(p.getId());
            mTabLayout.addTab(mTabLayout.newTab().setText(p.getName()));
        }
        mAdapter = new BasePagerAdapter(getChildFragmentManager(),mTitles,mNums);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mAdapter.notifyDataSetChanged();
    }
}
