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
 * {@link FoodandDrink.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodandDrink#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodandDrink extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View foodAndDrink = inflater.inflate(R.layout.fragment_foodand_drink, container, false);

        final ArrayList<place> places = getPlaces();

        ListView listView = (ListView) foodAndDrink.findViewById(R.id.listView);
        placeAdapter adapter = new placeAdapter(getActivity(),places);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String geo = places.get(position).getLocation();
                showLocation(geo);
            }
        });

        return foodAndDrink;
    }

    private ArrayList<place> getPlaces(){
        ArrayList<place> places = new ArrayList<place>();

        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));
        places.add(new place("Food & Drink","Food & Drink",R.drawable.food,"geo:16.798354,96.149705?z=5"));

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
