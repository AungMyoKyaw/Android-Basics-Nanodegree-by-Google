package com.aungmyokyaw.www.tourguideapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by aungmyokyaw on 23/9/17.
 */

public class fragmentAdapter extends FragmentPagerAdapter{

    public fragmentAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Attractions();
            case 1:
                return new FoodandDrink();
            case 2:
                return new NightLife();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Attraction";
            case 1:
                return "Food & Drink";
            case 2:
                return "Nightlife";
            default:
                return null;
        }
    }
}

