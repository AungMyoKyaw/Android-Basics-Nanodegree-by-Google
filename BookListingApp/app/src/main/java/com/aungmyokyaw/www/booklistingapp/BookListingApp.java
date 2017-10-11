package com.aungmyokyaw.www.booklistingapp;

import android.content.Context;
import android.content.Intent;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookListingApp extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<book>>,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private final static String google_book_api = "https://www.googleapis.com/books/v1/volumes";
    private bookAdapter bookAdapter;
    private String keyword;
    private TextView.OnEditorActionListener onSearch = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                search();
                return true;
            }
            return false;
        }
    };

    private View.OnTouchListener onTouchSearchBox = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            EditText searchBox = (EditText) findViewById(R.id.search);
            searchBox.setFocusableInTouchMode(true);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing_app);

        EditText searchBox = (EditText) findViewById(R.id.search);
        searchBox.setOnEditorActionListener(onSearch);
        searchBox.setOnTouchListener(onTouchSearchBox);

        //hide loading
        ProgressBar loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.GONE);

        //hide no result
        TextView no_result = (TextView) findViewById(R.id.no_result);
        no_result.setVisibility(View.GONE);

        //share preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.settings:
                Intent settingIntent = new Intent(this,BookListingAppSettingsActivity.class);
                startActivity(settingIntent);
                return true;
            default:
                return true;
        }
    }

    private void search(){
        EditText searchBox = (EditText) findViewById(R.id.search);
        keyword = searchBox.getText().toString();
        searchBox.clearFocus();
        searchBox.setFocusable(false);
        InputMethodManager in = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchBox.getWindowToken(),0);

        ListView listView = (ListView) findViewById(R.id.list);
        bookAdapter = new bookAdapter(this,new ArrayList<book>());
        listView.setAdapter(bookAdapter);

        //show loading
        ProgressBar loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

        //loader setup
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(1,null,this);
    }

    @Override
    public Loader<List<book>> onCreateLoader(int id, Bundle args) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String max_results = prefs.getString("max_results","10");

        Uri baseUri = Uri.parse(google_book_api);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("key",getString(R.string.api_key));
        uriBuilder.appendQueryParameter("maxResults",max_results);
        uriBuilder.appendQueryParameter("q",keyword);
        return new bookLoader(this,uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<book>> loader, List<book> books) {
        bookAdapter.clear();
        //hide loading
        ProgressBar loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.GONE);

        if(books !=null && !books.isEmpty()){
            bookAdapter.addAll(books);
        } else {
            //show emptyResult
            TextView no_result = (TextView) findViewById(R.id.no_result);
            no_result.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<book>> loader) {
        bookAdapter.clear();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("max_results")){
            bookAdapter.clear();
        }
    }
}
