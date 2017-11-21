package com.aungmyokyaw.habittrackerapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aungmyokyaw.habittrackerapp.data.HabitContract.HabitEntry;

public  class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private HabitCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditHabit.class);
                startActivity(intent);
            }
        });

        //set list view
        ListView habit_list = (ListView) findViewById(R.id.habit_list);
        //set empty view
        View emptyView = findViewById(R.id.not_found);
        habit_list.setEmptyView(emptyView);

        mCursorAdapter = new HabitCursorAdapter(this,null);
        habit_list.setAdapter(mCursorAdapter);

        habit_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(MainActivity.this,EditHabit.class);
                Uri currentHabitUri = ContentUris.withAppendedId(HabitEntry.CONTENT_URI,id);
                intent.setData(currentHabitUri);
                startActivity(intent);
            }
        });

        //start loader manager
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_TIMES
        };
        return new CursorLoader(
                this,
                HabitEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//        Log.d("cursor", DatabaseUtils.dumpCursorToString(cursor));
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:
                getContentResolver().delete(HabitEntry.CONTENT_URI,null,null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
