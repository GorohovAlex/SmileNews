package com.example.smile.smilenews.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.smile.smilenews.controller.NewsController;
import com.example.smile.smilenews.model.adapter.NewsAdapter;
import com.example.smile.smilenews.R;
import com.example.smile.smilenews.model.pogo.Article;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NewsController.CallbackListener {

    private final String ARTICLE_LIST_STATE = "ARTICLE_LIST_STATE";

    @BindView(R.id.news_recycler) RecyclerView newsRecycle;

    private ArrayList<Article> articleList;
    private NewsAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private NewsController newsController;
    private static Bundle mBundleRecyclerViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
        newsController = new NewsController(MainActivity.this);
    }

    private void initViews(){
        articleList = new ArrayList<>();
        adapter = new NewsAdapter(articleList);
        mLayoutManager = new LinearLayoutManager(this);

        newsRecycle.setLayoutManager(mLayoutManager);
        newsRecycle.setAdapter(adapter);

        newsRecycle.addOnScrollListener(new RecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void LoadMore(int current_page) {
                newsController.LoadNews(current_page);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBundleRecyclerViewState = new Bundle();
        mBundleRecyclerViewState.putParcelableArrayList(ARTICLE_LIST_STATE, ((NewsAdapter)newsRecycle.getAdapter()).getArticleList());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("restore","onResume");

        if (mBundleRecyclerViewState != null) {
            this.articleList = mBundleRecyclerViewState.getParcelableArrayList(ARTICLE_LIST_STATE);
            newsRecycle.setAdapter(new NewsAdapter(articleList));
        } else{
            newsController.LoadNews(1);
        }
    }

    @Override
    public void onLoadNews(List<Article> articleList) {
        this.articleList.addAll(articleList);
        newsRecycle.getAdapter().notifyDataSetChanged();
    }
}