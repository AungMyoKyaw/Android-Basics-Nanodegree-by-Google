package com.aungmyokyaw.www.newsapp;

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
 * Created by aungmyokyaw on 14/10/17.
 */

public final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){

    }

    public static List<News> fetchNews(String requestUrl){
        URL url = createUrl(requestUrl);
        String json_response  = null;
        try{
            json_response = makeHttpRequest(url);
            Log.i(LOG_TAG,json_response);
        } catch (IOException e){
            e.printStackTrace();
        }

        List<News> news = extractFromJson(json_response);

        return news;
    }

    private static URL createUrl(String StringUrl){
        URL url = null;
        try{
            url = new URL(StringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String json_response = "";

        if(url==null){
            return json_response;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try{
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                json_response = readFromString(inputStream);
            } else {
                Log.e(LOG_TAG,"Erro Status Code"+httpURLConnection.getResponseCode());
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return json_response;
    }

    private static String readFromString(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
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

    private static List<News> extractFromJson(String news_json){
        if(TextUtils.isEmpty(news_json)){
            return null;
        }

        List<News> news = new ArrayList<News>();

        try {
            JSONObject baseJson = new JSONObject(news_json);
            JSONArray news_array = baseJson.getJSONObject("response").getJSONArray("results");

            for(int i=0;i<news_array.length();i++){
                JSONObject current_News = news_array.getJSONObject(i);
                String sectionName = current_News.getString("sectionName");
                String webTitle = current_News.getString("webTitle");
                String webPublicationDate = current_News.getString("webPublicationDate");
                String webUrl = current_News.getString("webUrl");

                News mynews = new News(sectionName,webTitle,webPublicationDate,webUrl);

                news.add(mynews);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return news;
    }

}
