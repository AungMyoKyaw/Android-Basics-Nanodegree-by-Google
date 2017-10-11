package com.aungmyokyaw.www.booklistingapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aungmyokyaw on 10/10/17.
 */

public final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){

    }

    public static List<book> fetchBooks(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e){
            Log.e(LOG_TAG,"Problem making Http Request",e);
        }

        List<book> books = extractFromJson(jsonResponse);

        return books;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        } catch (MalformedURLException e){
            Log.e(LOG_TAG,"Problem on building url",e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromString(inputStream);
            } else {
                Log.e(LOG_TAG,"Error Status Code "+urlConnection.getResponseCode());
            }
        } catch (IOException e){
            Log.e(LOG_TAG,"Error on retrieving JSON results",e);
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromString(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<book> extractFromJson(String bookJSON){
        if(TextUtils.isEmpty(bookJSON)){
            return null;
        }

        List<book> books = new ArrayList<book>();

        try {
            JSONObject baseJSON = new JSONObject(bookJSON);

            JSONArray bookArray = baseJSON.getJSONArray("items");

            for(int i=0;i<bookArray.length();i++){
                StringBuilder author = new StringBuilder();
                JSONObject currentBook = bookArray.getJSONObject(i);
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                JSONArray authors = new JSONArray();
                String imageLink = null;

                try{
                    authors = volumeInfo.getJSONArray("authors");
                } catch (JSONException e){
                    authors = authors;
                }

                try{
                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                    imageLink = imageLinks.getString("smallThumbnail");
                } catch (JSONException e){
                    imageLink = "https://storage.googleapis.com/gweb-uniblog-publish-prod/images/NationalBookDay-hero_2.2e16d0ba.fill-1440x810.jpg";
                }

                for(int j=0;j<authors.length();j++){
                    if(authors.length() == j+1){
                        author.append(authors.get(j));
                    } else {
                        author.append(authors.get(j)+", ");
                    }
                }

                String title = volumeInfo.getString("title");

                book myBook = new book(imageLink,title,author.toString(),"");
                books.add(myBook);
            }
        } catch (JSONException e){
            Log.e(LOG_TAG,"Error Parsing JSON",e);
        }

        return books;
    }
}
