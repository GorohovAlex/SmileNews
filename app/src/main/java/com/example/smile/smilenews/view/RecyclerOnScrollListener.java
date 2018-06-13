package com.example.smile.smilenews.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int firstVisibleItem;
    private int previosTotal = 0;
    private int visibleThreshold = 5;
    private boolean isLoading = true;
    private int current_page = 1;

    public RecyclerOnScrollListener(LinearLayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        visibleItemCount = mLayoutManager.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if(isLoading){
            if(totalItemCount > previosTotal){
                isLoading = false;
                previosTotal = totalItemCount;
            }
        }

        if (!isLoading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)){
            current_page++;
            Log.i("current_page",Integer.toString(current_page));
            LoadMore(current_page);
            isLoading = true;
        }
    }

    public abstract void LoadMore(int current_page);
}
