package com.aungmyokyaw.www.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aungmyokyaw on 14/10/17.
 */

public class NewsAdapter extends ArrayAdapter<News>{
    public NewsAdapter(Activity context,List<News> News){
        super(context,0,News);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newsView = convertView;

        if(newsView == null){
            newsView = LayoutInflater.from(getContext()).inflate(R.layout.news_list,parent,false);
        }

        //current news
        News current_news = getItem(position);

        //set Title
        TextView title = (TextView) newsView.findViewById(R.id.web_title);
        title.setText(current_news.getWebTitle());

        //set pub_date
        TextView pub_date = (TextView) newsView.findViewById(R.id.web_pub_date);
        pub_date.setText(current_news.getWebPublicationDate());

        //set section Name
        TextView section_name = (TextView) newsView.findViewById(R.id.section_name);
        section_name.setText(current_news.getSectionName());

        return newsView;
    }
}
