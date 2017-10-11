package com.aungmyokyaw.www.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aungmyokyaw on 10/10/17.
 */

public class bookLoader extends AsyncTaskLoader<List<book>>{

    private String url;

    public bookLoader(Context context,String new_url){
        super(context);
        url = new_url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<book> loadInBackground() {
        if(url==null){
            return null;
        }

        //get data
        List<book> books = QueryUtils.fetchBooks(url);
        return books;
    }

}
