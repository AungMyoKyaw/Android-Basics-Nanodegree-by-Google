package com.aungmyokyaw.www.musicalstructureapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Albums.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Albums#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Albums extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View albums = inflater.inflate(R.layout.fragment_albums, container, false);

        //getalbums
        ArrayList<song> albumList =getAlbums();

        //setadapter
        albumAdapter adapter = new albumAdapter(getActivity(),albumList);
        ListView listView = (ListView) albums.findViewById(R.id.album);
        listView.setAdapter(adapter);

        return albums;
    }

    private ArrayList<song> getAlbums(){
        ArrayList<song> albums = new ArrayList<song>();
        albums.add(new song("","","လူအဂီတ","Rock",0,R.drawable.al_list_art));
        albums.add(new song("","","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.al_list_art));

        return albums;
    }
}
