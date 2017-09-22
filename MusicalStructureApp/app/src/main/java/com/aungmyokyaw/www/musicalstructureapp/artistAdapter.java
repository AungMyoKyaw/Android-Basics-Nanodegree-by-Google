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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by aungmyokyaw on 21/9/17.
 */

public class artistAdapter extends ArrayAdapter<song> {

    public artistAdapter(Activity context, ArrayList<song> artists){
        super(context,0,artists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //set listview
        View artistView = convertView;

        if(artistView == null){
            artistView = LayoutInflater.from(getContext()).inflate(R.layout.song_list,parent,false);
        }

        song currentSong = getItem(position);

        //set art
        ImageView imageView = (ImageView) artistView.findViewById(R.id.art);
        imageView.setImageResource(currentSong.getResArt());

        //set ArtistName
        TextView artName = (TextView) artistView.findViewById(R.id.name);
        artName.setText(currentSong.getArtist());

        //set Empty
        TextView textView = (TextView) artistView.findViewById(R.id.artist);
        textView.setText("");

        return artistView;
    }
}
