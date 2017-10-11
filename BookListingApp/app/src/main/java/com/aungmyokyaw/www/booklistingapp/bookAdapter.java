package com.aungmyokyaw.www.booklistingapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by aungmyokyaw on 10/10/17.
 */

public class bookAdapter extends ArrayAdapter<book>{

    public bookAdapter(Activity context, List<book> books){
        super(context,0,books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View bookView = convertView;

        if(bookView ==null){
            bookView = LayoutInflater.from(getContext()).inflate(R.layout.book_list,parent,false);
        }

        book currentBook = getItem(position);

        //setImage
        ImageView img = (ImageView) bookView.findViewById(R.id.book_cover);
        Picasso.with(getContext()).load(currentBook.getImg()).into(img);

        //set Title
        TextView title = (TextView) bookView.findViewById(R.id.book_title);
        title.setText(currentBook.getTitle());

        //set author
        TextView author = (TextView) bookView.findViewById(R.id.book_author);
        author.setText(currentBook.getAuthor());

        //set description
        TextView description = (TextView) bookView.findViewById(R.id.book_desciption);
        description.setText(currentBook.getDescription());

        return bookView;
    }
}
