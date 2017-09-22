package com.aungmyokyaw.www.musicalstructureapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        fragmentAdapter adapter =new fragmentAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);

        //setting TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //setting Icon
        int icon = tabLayout.getTabCount();
        for (int i=0;i<icon;i++){
            switch(i){
                case 0://home
                    tabLayout.getTabAt(i).setIcon(R.drawable.home);
                    break;
                case 1://artists
                    tabLayout.getTabAt(i).setIcon(R.drawable.artists);
                    break;
                case 2://albums
                    tabLayout.getTabAt(i).setIcon(R.drawable.albums);
                    break;
                case 3:
                    tabLayout.getTabAt(i).setIcon(R.drawable.genre);
                    break;
                default:
                    break;
            }
        }

        //making tab beautiful
//        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
    }
}
