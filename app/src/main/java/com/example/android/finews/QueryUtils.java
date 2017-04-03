package com.example.android.finews;
import android.util.Log;

import com.example.android.finews.MainActivity;

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

/**
 * Created by PRAVIN on 2/2/2017.
 */
public final class QueryUtils {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private QueryUtils() throws IOException {
    }

 public static ArrayList<News> extractNews(String STRING_URL )
 {
     URL url=createUrl(STRING_URL);
     ArrayList<News> newses=new ArrayList<>();
     try {
         String jsonResponse= null;
         try {
             jsonResponse = makeHttpRequest(url);
         } catch (IOException e) {
             e.printStackTrace();
         }
         // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
         JSONObject baseJsonResponse = new JSONObject(jsonResponse);

         // Extract the JSONArray associated with the key called "features",
         // which represents a list of features (or earthquakes).
         JSONArray newsArray = baseJsonResponse.getJSONArray("articles");

         // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
         for (int i = 0; i < newsArray.length(); i++) {

             // Get a single earthquake at position i within the list of earthquakes
             JSONObject currentNews = newsArray.getJSONObject(i);

             // For a given earthquake, extract the JSONObject associated with the
             // key called "properties", which represents a list of all properties
             // for that earthquake.

             // Extract the value for the key called "mag"
             String description  = currentNews.getString("description");

             // Extract the value for the key called "place"
             String headline = currentNews.getString("title");



             // Extract the value for the key called "url"
             String url1 = currentNews.getString("url");

             // Create a new {@link Earthquake} object with the magnitude, location, time,
             // and url from the JSON response.
             News news = new News(headline, description, url1);

             // Add the new {@link Earthquake} to the list of earthquakes.
             newses.add(news);
         }

     }
     catch (JSONException e) {
         // If an error is thrown when executing any of the above statements in the "try" block,
         // catch the exception here, so the app doesn't crash. Print a log message
         // with the message from the exception.
         Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
     }




 return newses;
 }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if(url==null){return jsonResponse;}
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {Log.e(LOG_TAG,"Error Http response code is" + urlConnection.getResponseCode());}} catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the Http request ", e);
            //
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
