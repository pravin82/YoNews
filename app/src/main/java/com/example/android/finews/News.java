package com.example.android.finews;

/**
 * Created by PRAVIN on 2/2/2017.
 */
public class News {
    private String pHeadline;
    private String pSource;
    private String pUrl;

    public News(String headline, String source, String url) {

        pHeadline = headline;
        pSource = source;
        pUrl = url;
    }

    public String getHeadline() {
        return pHeadline;
    }

    public String getSource() {
        return pSource;
    }

    public String getUrl() {
        return pUrl;
    }
}
