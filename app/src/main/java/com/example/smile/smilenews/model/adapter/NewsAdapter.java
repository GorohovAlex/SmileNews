package com.example.smile.smilenews.model.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smile.smilenews.view.NewsActivity;
import com.example.smile.smilenews.R;
import com.example.smile.smilenews.model.pogo.Article;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<Article> articleList;


    public NewsAdapter(ArrayList<Article> articleList) {
        this.articleList = articleList;
    }

    public ArrayList<Article> getArticleList() {
        return articleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Article article = articleList.get(position);
        holder.newsTitle.setText(article.getTitle());
        holder.newsSource.setText(article.getSource().getName());

        Picasso.get().load(article.getUrlToImage()).fit().centerCrop().into(holder.imageViewBG);
        Picasso.get().load(article.getUrlToImage()).fit().centerCrop().placeholder(R.drawable.giphy).into(holder.imageView);

        holder.imageViewBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewsActivity.class);
                intent.putExtra("url",article.getUrl());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (articleList == null)
            return 0;
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.news_source) TextView newsSource;
        @BindView(R.id.news_title) TextView newsTitle;
        @BindView(R.id.news_image_bg) ImageView imageViewBG;
        @BindView(R.id.news_image) ImageView imageView;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
