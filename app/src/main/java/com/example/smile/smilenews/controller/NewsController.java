package com.example.smile.smilenews.controller;

import com.example.smile.smilenews.MainApplication;
import com.example.smile.smilenews.api.NewsApi;
import com.example.smile.smilenews.model.pogo.Article;
import com.example.smile.smilenews.model.pogo.ArticleList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsController {

    private CallbackListener mListener;
    private String language = "ru";
    private String sources = "ukraine-news-api";

    public NewsController(CallbackListener mListener) {
        this.mListener = mListener;
    }

    public void LoadNews(int current_page){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainApplication.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);
        newsApi.getEverything(language, sources,current_page).enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                if(response.body().getArticles() != null) {
                    mListener.onLoadNews(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {

            }
        });
    }

    public interface CallbackListener{
        void onLoadNews(List<Article> articleList);
    }
}