package com.aungmyokyaw.www.tourguideapp;

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
 * Created by aungmyokyaw on 23/9/17.
 */

public class placeAdapter extends ArrayAdapter<place>{
    public placeAdapter(Activity context, ArrayList<place> places){
        super(context,0,places);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;

        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.place_list,parent,false);
        }

        place current = getItem(position);

        //setImaget
        ImageView img = (ImageView) listView.findViewById(R.id.image);
        img.setImageResource(current.getImgRes());

        //set name
        TextView text = (TextView) listView.findViewById(R.id.name);
        text.setText(current.getName());

        //set des
        TextView des = (TextView) listView.findViewById(R.id.des);
        des.setText(current.getDescription());

        return listView;
    }
}
