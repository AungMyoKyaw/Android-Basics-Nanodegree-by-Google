package com.aungmyokyaw.www.musicalstructureapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Artists.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Artists#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Artists extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View artists = inflater.inflate(R.layout.fragment_artists, container, false);

        //get ArtistsList
        ArrayList<song> artList = getArtists();

        //set adapter
        artistAdapter adapter = new artistAdapter(getActivity(),artList);
        ListView listView = (ListView) artists.findViewById(R.id.artist);
        listView.setAdapter(adapter);

        return artists;
    }

    private ArrayList<song> getArtists(){
        ArrayList<song> artists = new ArrayList<song>();

        artists.add(new song("","စိုင်းထီးဆိုင်","","Rock",0,R.drawable.artist_art));
        artists.add(new song("","ထူးအိမ်သင်","","Rock",0,R.drawable.artist_art));
        artists.add(new song("","ခင်မောင်တိုး","","Rock",0,R.drawable.artist_art));
        artists.add(new song("","IDIOTS","","Rock",0,R.drawable.artist_art));
        artists.add(new song("","WANTED","","Rock",0,R.drawable.artist_art));
        artists.add(new song("","BIG BAG","","Rock",0,R.drawable.artist_art));
        artists.add(new song("","REASON","","Rock",0,R.drawable.artist_art));

        return artists;
    }
}
