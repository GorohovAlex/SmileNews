package com.example.smile.smilenews;

import android.app.Application;

public class MainApplication extends Application {
    private static final String url = "https://newsapi.org/v2/";
    private static final String key = "7cbc9770935c4f6f86dbbd332eed9d6c";

    public static String getKey() {
        return key;
    }

    public static String getUrl() {
        return url;
    }
}
