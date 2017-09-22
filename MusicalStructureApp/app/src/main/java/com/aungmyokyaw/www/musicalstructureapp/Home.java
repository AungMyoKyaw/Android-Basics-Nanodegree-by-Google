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
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View home = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<song> songs = getSong();

        songAdapter songAdapter = new songAdapter(getActivity(),songs);

        //set adapter
        ListView listView = (ListView) home.findViewById(R.id.song);
        listView.setAdapter(songAdapter);

        return home;
    }

    private ArrayList<song> getSong(){
        final ArrayList<song> songs = new ArrayList<song>();

        songs.add(new song("ငါ့ဘဝပြန်ယူ","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("ချန်ခဲ့","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("မင်းနဲ့နီးဖို့","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("A Tu","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("နေရစ်ခဲ့တော့","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("မထိုက်တဲ့အလင်း","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("အသက်ငွေ့ငွေ့","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("ဆုလာဒ်","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("အဓိပ္ပါယ်","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("မင်းအတွက်မင်း","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("ထက်မမေ့နဲ့တော့","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("တွေ့ရတာဝမ်းသာပါတယ်","idiots","လူအဂီတ","Rock",0,R.drawable.album_art));
        songs.add(new song("လူအ","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("Distortion","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("ဒီလိုပဲ","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("မလိုတော့ဘူး","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("သူရဲကောင်း","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("ဘာလိုနေသေးလဲ","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("တခန်းရပ်","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("အက်","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("ယုံကြည်မှု","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("မင်းနဲ့ပတ်သက်ရင်","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("အရှင်ဂီတ","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("ရူးသူဘဝ","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));
        songs.add(new song("အိပ်ရာဝင်တေး","idiots","ခေတ်သစ်ကျောက်","Rock",0,R.drawable.album_art));

        return songs;
    }
}
