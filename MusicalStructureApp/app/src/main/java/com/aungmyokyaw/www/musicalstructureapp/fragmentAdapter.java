package com.aungmyokyaw.www.musicalstructureapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by aungmyokyaw on 21/9/17.
 */

public class fragmentAdapter extends FragmentPagerAdapter{
    private Context context;

    public fragmentAdapter(FragmentManager fm,Context ncontext){
        super(fm);
        context = ncontext;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Home();
            case 1:
                return new Artists();
            case 2:
                return new Albums();
            case 3:
                return new Genre();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.home);
            case 1:
                return context.getString(R.string.artists);
            case 2:
                return context.getString(R.string.albums);
            case 3:
                return context.getString(R.string.genre);
            default:
                return null;
        }
    }
}
