package com.aungmyokyaw.www.musicalstructureapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aungmyokyaw on 21/9/17.
 */

public class genreAdapter extends ArrayAdapter<song>{

    public genreAdapter(Activity context, ArrayList<song> genre){
        super(context,0,genre);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //set view
        View listView = convertView;

        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.song_list,parent,false);
        }

        //current
        song current = getItem(position);

        //set art
        ImageView img = (ImageView) listView.findViewById(R.id.art);
        img.setImageResource(current.getResArt());

        //set name
        TextView name = (TextView) listView.findViewById(R.id.name);
        name.setText(current.getGenre());

        //set empty
        TextView textView = (TextView) listView.findViewById(R.id.artist);
        textView.setText("");

        return listView;
    }
}
