package com.aungmyokyaw.www.musicalstructureapp;

import android.app.Activity;
import android.media.Image;
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

public class albumAdapter extends ArrayAdapter<song>{
    public albumAdapter(Activity context, ArrayList<song> albums){
        super(context,0,albums);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //set list view
        View listview = convertView;

        if(listview == null){
            listview = LayoutInflater.from(getContext()).inflate(R.layout.song_list,parent,false);
        }

        //current album
        song currentAlbum = getItem(position);

        //set Album Image
        ImageView img = (ImageView) listview.findViewById(R.id.art);
        img.setImageResource(currentAlbum.getResArt());

        //set albumname
        TextView name = (TextView) listview.findViewById(R.id.name);
        name.setText(currentAlbum.getAlbums());

        //set empty
        TextView textView = (TextView) listview.findViewById(R.id.artist);
        textView.setText("");

        return listview;
    }
}
