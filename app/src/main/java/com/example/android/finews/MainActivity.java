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

public class MainActivity extends AppCompatActivity  {
    public static final String LOG_TAG = MainActivity.class.getName();


    private NewsAdapter pAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView fNewsTextView=(TextView) findViewById(R.id.financeNews);

        fNewsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fNewsintent= new Intent(MainActivity.this,Financial_news.class);
                startActivity(fNewsintent);
            }
        });
        TextView tNewsTextView=(TextView) findViewById(R.id.techNews);
        tNewsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tNewsintent= new Intent(MainActivity.this,Tech_news.class);
                startActivity(tNewsintent);
            }
        });









    }






}
