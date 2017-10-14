package com.aungmyokyaw.www.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewsAppActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener,
        LoaderManager.LoaderCallbacks<List<News>>,
        SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String LOG_TAG = NewsAppActivity.class.getSimpleName();
    private static final String news_api_url = "http://content.guardianapis.com/search";
    private static final String api_key = "c02a9922-ec3b-41bf-85d2-4124e5832b45";

    private String searchQuery;
    private NewsAdapter newsAdapter;
    private ProgressBar loading;
    private TextView noResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_app);

        //adapter setup
        ListView news_list = (ListView) findViewById(R.id.news);
        newsAdapter = new NewsAdapter(this,new ArrayList<News>());
        news_list.setAdapter(newsAdapter);
        news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News current = newsAdapter.getItem(position);
                Intent web = new Intent(Intent.ACTION_VIEW,Uri.parse(current.getWebUrl()));
                startActivity(web);
            }
        });

        //share preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        //loader Setup
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        //search View
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.settings:
                Intent settingIntent = new Intent(this,Settings.class);
                startActivity(settingIntent);
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        newsAdapter.clear();
        searchQuery = query;
        getLoaderManager().restartLoader(1,null,this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        //show Loading
        loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

        // hide no results
        noResult = (TextView) findViewById(R.id.no_result);
        noResult.setVisibility(View.GONE);

        //get network INfo
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null || !networkInfo.isConnected()){
            Toast.makeText(getApplicationContext(),getString(R.string.no_internet),Toast.LENGTH_LONG).show();
        }

            //get preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String max_results = sharedPreferences.getString("max_results","10");

        //createUrl
        Uri baseUri = Uri.parse(news_api_url);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("api-key",api_key);
        uriBuilder.appendQueryParameter("page-size",max_results);
        if(searchQuery != null){
            uriBuilder.appendQueryParameter("q",searchQuery);
        }

        return new NewsLoader(this,uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        //clear adapter
        newsAdapter.clear();

        //hide loading
        loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.GONE);

        if(news != null && !news.isEmpty()){
            newsAdapter.addAll(news);
        } else {
            //show no results
            noResult = (TextView) findViewById(R.id.no_result);
            noResult.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clear();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("max_results")){
            newsAdapter.clear();
        }
    }
}
