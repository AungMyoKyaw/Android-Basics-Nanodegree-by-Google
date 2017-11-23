package com.aungmyokyaw.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
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
import android.widget.TextView;
import com.aungmyokyaw.inventoryapp.data.InventoryContract.InventoryEntry;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private InventoryCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup fab
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditItem.class);
                startActivity(intent);
            }
        });

        //set listview
        ListView product_list = (ListView) findViewById(R.id.item_list);
        mCursorAdapter = new InventoryCursorAdapter(this,null);
        product_list.setAdapter(mCursorAdapter);

        //set empty view
        TextView empty_view = findViewById(R.id.not_found);
        product_list.setEmptyView(empty_view);

        //set on click item
        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Log.d("clicked","clicked");
                Intent intent = new Intent(MainActivity.this,EditItem.class);
                Uri CurrentProductUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI,id);
                intent.setData(CurrentProductUri);
                startActivity(intent);
            }
        });

        //init loader
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:
                getContentResolver().delete(InventoryEntry.CONTENT_URI,null,null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COL_PRODUCT_NAME,
                InventoryEntry.COL_PRODUCT_PRICE,
                InventoryEntry.COL_PRODUCT_QUANTITY,
                InventoryEntry.COL_IMG_URL
        };
        return new CursorLoader(
                this,
                InventoryEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
