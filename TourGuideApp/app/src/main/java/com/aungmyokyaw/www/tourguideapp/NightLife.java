package com.aungmyokyaw.www.tourguideapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NightLife.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NightLife#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NightLife extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nightLife = inflater.inflate(R.layout.fragment_night_life, container, false);

        final ArrayList<place> places = getPlaces();
        ListView listView = (ListView) nightLife.findViewById(R.id.listView);
        placeAdapter adapter = new placeAdapter(getActivity(),places);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String geo = places.get(position).getLocation();
                showLocation(geo);
            }
        });

        return nightLife;
    }

    private ArrayList<place> getPlaces(){
        ArrayList<place> places = new ArrayList<place>();

        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Night Life","Night Life",R.drawable.night_life,"geo:16.798354,96.149705?z=5"));

        return places;
    }

    private void showLocation(String geo){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse(geo));

        if(mapIntent.resolveActivity(getActivity().getPackageManager())!=null){
            startActivity(mapIntent);
        }
    }

}
