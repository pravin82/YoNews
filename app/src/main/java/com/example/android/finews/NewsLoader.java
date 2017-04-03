package com.example.android.finews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRAVIN on 2/3/2017.
 */
public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String pUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        pUrl=url;

        // TODO: Finish implementing this constructor
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if(pUrl==null)
        {return null;
        }
        // Perform the HTTP request for earthquake data and process the response.
        ArrayList<News> news = QueryUtils.extractNews(pUrl);
        return news;
        // TODO: Implement this method
    }
}
