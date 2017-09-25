package com.aungmyokyaw.www.tourguideapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Attractions.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Attractions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Attractions extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View attractions = inflater.inflate(R.layout.fragment_attractions, container, false);

        final ArrayList<place> places = getPlaces();

        ListView listView = (ListView) attractions.findViewById(R.id.listView);
        placeAdapter adapter = new placeAdapter(getActivity(),places);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String geoLoc = places.get(position).getLocation();
                showLoc(geoLoc);
            }
        });

        return attractions;
    }

    private ArrayList<place> getPlaces(){
        ArrayList<place> places = new ArrayList<place>();

        places.add(new place("ShewDagon",getString(R.string.shwedagon),R.drawable.shwedagon,"geo:16.798354,96.149705?z=16"));
        places.add(new place("Kyaiktiyo",getString(R.string.kyaiktiyo),R.drawable.kyaiktiyo,"geo:17.481682,97.098118?z=16"));
        places.add(new place("Shwenandaw Monastery",getString(R.string.shwenandaw_monastery),R.drawable.shwenandaw_monastery,"geo:22.000675,96.113722?z=16"));
        places.add(new place("Bagan",getString(R.string.bagan),R.drawable.bagan,"geo:21.166667,94.866667?z=16"));
        places.add(new place("Mrauk U",getString(R.string.maruk_u),R.drawable.mrauk_u,"geo:20.596083,93.19405?z=16"));
        places.add(new place("Inle Lake",getString(R.string.inle_lake),R.drawable.inle_lake,"geo:20.55,96.916667?z=16"));

        return places;
    }

    private void showLoc(String geoLoc){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse(geoLoc));

        if(mapIntent.resolveActivity(getActivity().getPackageManager())!=null){
            startActivity(mapIntent);
        }
    }

}
