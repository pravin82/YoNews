package com.example.android.finews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;

import java.util.ArrayList;
import java.util.List;

public class Financial_news extends AppCompatActivity implements LoaderCallbacks<List<News>> {
    public static final String LOG_TAG = MainActivity.class.getName();
    private TextView pEmptyTextView;
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private NewsAdapter pAdapter;

    public static final String STRING_URL ="https://newsapi.org/v1/articles?source=the-wall-street-journal&sortBy=top&apiKey=c230d4deb92d48d9b3e34e429e252a0c";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_news);
        pEmptyTextView = (TextView) findViewById(R.id.empty_view);
        ListView newsListView = (ListView) findViewById(R.id.list);
        newsListView.setEmptyView(pEmptyTextView);
        pAdapter = new NewsAdapter(this,new ArrayList<News>());
        newsListView.setAdapter(pAdapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = pAdapter.getItem(position);


                Uri newsUri = Uri.parse(currentNews.getUrl());


                Intent websiteintent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteintent);


            }
        });
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null &&
                activeNetwork.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {  ProgressBar pProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);
            pProgressBar.setVisibility(ProgressBar.GONE);
            pEmptyTextView.setText(R.string.no_internet_connection);
        }


    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, STRING_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        View loadingIndicator = findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        pEmptyTextView.setText(R.string.no_news);

        // Clear the adapter of previous earthquake data
        pAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            pAdapter.addAll(news);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        pAdapter.clear();

    }
}


