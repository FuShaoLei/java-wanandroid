package com.example.java_wanandroid.Base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.java_wanandroid.Bean.Article;
import com.example.java_wanandroid.R;
import com.example.java_wanandroid.Util.MyURL;
import com.example.java_wanandroid.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    private Context mContext;
    private int mLayout;
    private List<Article> mList = new ArrayList<>();

    public BaseAdapter(Context mContext, int mLayout, List<Article> mList) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mList = mList;
    }

    public void resetData(List<Article> list){
        if (list == null || list.size() == 0){
            return;
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(mList.get(position).getTitle());
        holder.mDate.setText(mList.get(position).getNiceDate());
        holder.mName.setText(mList.get(position).getSuperChapterName());
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mDate;
        private TextView mName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.item_tv_title);
            mDate = itemView.findViewById(R.id.item_tv_date);
            mName = itemView.findViewById(R.id.item_tv_name);
        }
    }
}
