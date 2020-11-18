package com.example.java_wanandroid.Base;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.java_wanandroid.Bean.Article;
import com.example.java_wanandroid.R;

import java.util.List;

import androidx.annotation.Nullable;

public class NewBaseAdapter extends BaseQuickAdapter<Article, NewBaseAdapter.ViewHold> {

    public NewBaseAdapter(int layoutResId, @Nullable List<Article> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHold helper, Article item) {
        helper.mTitle.setText(item.getTitle());
        helper.mDate.setText(item.getNiceDate());
        helper.mName.setText(item.getSuperChapterName());
    }

    class ViewHold extends BaseViewHolder{
        private TextView mTitle;
        private TextView mDate;
        private TextView mName;
        public ViewHold(View view) {
            super(view);
            mTitle = itemView.findViewById(R.id.item_tv_title);
            mDate = itemView.findViewById(R.id.item_tv_date);
            mName = itemView.findViewById(R.id.item_tv_name);
        }
    }
}
