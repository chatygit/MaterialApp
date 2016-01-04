package com.example.ziggy.materialapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.ziggy.materialapp.R;
import com.example.ziggy.materialapp.SubActivity;
import com.example.ziggy.materialapp.network.VolleySingleton;
import com.example.ziggy.materialapp.pojo.News;

import java.util.ArrayList;

/**
 * Created by Ziggy on 2015-12-28.
 */
public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolderHome> {

    private ArrayList<News> listNews = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context context;
    private ClickListener clickListener;
    public AdapterHome(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getsInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public void setNewsList(ArrayList<News> listNews){
        this.listNews = listNews;
        notifyItemRangeChanged(0, listNews.size());
    }
    @Override
    public ViewHolderHome onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_news,parent,false);
        ViewHolderHome viewHolder = new ViewHolderHome(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderHome holder, int position) {
        News currentNews = listNews.get(position);
        holder.newsHeadline.setText(currentNews.getHeadline());
        holder.newsDate.setText(currentNews.getDate());
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    class ViewHolderHome extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView newsHeadline;
        private TextView newsDate;
        public ViewHolderHome(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            newsHeadline = (TextView) itemView.findViewById(R.id.newsTitle);
            newsDate = (TextView) itemView.findViewById(R.id.newsDate);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, SubActivity.class));
            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }
        }
    }

    public interface ClickListener{
        public void itemClicked(View view,int position);

    }
}












