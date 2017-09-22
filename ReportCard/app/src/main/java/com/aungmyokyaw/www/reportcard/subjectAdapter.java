package com.aungmyokyaw.www.reportcard;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by aungmyokyaw on 22/9/17.
 */

public class subjectAdapter extends ArrayAdapter<subject>{
    public subjectAdapter(Activity context, ArrayList<subject> subject){
        super(context,0,subject);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;

        if(listview == null){
            listview = LayoutInflater.from(getContext()).inflate(R.layout.list_subject,parent,false);
        }

        subject current = getItem(position);

        //set Subjectname
        TextView subName = (TextView) listview.findViewById(R.id.subject);
        subName.setText(current.getName()+"");

        //set mark
        TextView mark = (TextView) listview.findViewById(R.id.mark);
        mark.setText(current.getMark()+"");


        return listview;
    }
}
