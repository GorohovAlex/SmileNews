package com.example.smile.smilenews.api;

import com.example.smile.smilenews.model.pogo.Article;
import com.example.smile.smilenews.model.pogo.ArticleList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    static final String apiKey = "7cbc9770935c4f6f86dbbd332eed9d6c";

    @GET("top-headlines?apikey="+apiKey)
    Call<ArticleList> getTopHeadlines(@Query("country") String country);

    @GET("everything?apikey="+apiKey)
    Call<ArticleList> getEverything(@Query("language") String language,@Query("sources") String sources, @Query("page") int page);
}
