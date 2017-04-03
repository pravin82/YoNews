package com.example.android.finews;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PRAVIN on 2/2/2017.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> newses){
        super(context,0,newses);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        News currentNews = getItem(position);


        // Find the TextView with view ID magnitude
        TextView headlineView = (TextView) listItemView.findViewById(R.id.headline);

        headlineView.setText(currentNews.getHeadline());
        TextView sourceView = (TextView) listItemView.findViewById(R.id.description);

        sourceView.setText(currentNews.getSource());

















        return listItemView;
    }


}
