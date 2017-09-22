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
 * {@link Genre.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Genre#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Genre extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View genre = inflater.inflate(R.layout.fragment_genre, container, false);

        //genre
        ArrayList<song> genreList = getGenre();

        //setadapter
        ListView listView = (ListView) genre.findViewById(R.id.genre);
        genreAdapter adapter = new genreAdapter(getActivity(),genreList);
        listView.setAdapter(adapter);

        return genre;
    }

    private ArrayList<song> getGenre(){
        ArrayList<song> genre = new ArrayList<song>();

        genre.add(new song("","","","Rock",0,R.drawable.genre_art));

        return genre;
    }
}
