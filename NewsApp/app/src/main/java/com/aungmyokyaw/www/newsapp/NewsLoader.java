package com.aungmyokyaw.www.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by aungmyokyaw on 14/10/17.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>>{
    private static final String LOG_TAG = NewsLoader.class.getSimpleName();
    private String url;

    public NewsLoader(Context context,String new_url){
        super(context);
        url = new_url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if(url==null){
            return null;
        }
        Log.i(LOG_TAG,url);
        //get data
        List<News> news = QueryUtils.fetchNews(url);
        return news;
    }
}
