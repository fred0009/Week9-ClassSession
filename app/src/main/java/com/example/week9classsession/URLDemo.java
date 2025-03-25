package com.example.week9classsession;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class URLDemo extends AppCompatActivity {
    public final String TAG = "INFOSYS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // URL demo
        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority("www.example.com")
                .appendPath("search")
                .appendQueryParameter("query", "android")
                .build();

        Log.d(TAG, uri.toString());
    }
}