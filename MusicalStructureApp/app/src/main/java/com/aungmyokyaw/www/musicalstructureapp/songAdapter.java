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

public class songAdapter extends ArrayAdapter<song> {

    public songAdapter(Activity context,ArrayList<song> songs){
        super(context,0,songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View songView = convertView;

        if(songView == null){
            songView = LayoutInflater.from(getContext()).inflate(R.layout.song_list,parent,false);
        }

        song currentSong = getItem(position);

        //setArt
        ImageView art = (ImageView) songView.findViewById(R.id.art);
        art.setImageResource(currentSong.getResArt());

        //setName
        TextView name = (TextView) songView.findViewById(R.id.name);
        name.setText(currentSong.getName());

        //set Artist
        TextView artist = (TextView) songView.findViewById(R.id.artist);
        artist.setText(currentSong.getArtist());

        return songView;
    }
}
