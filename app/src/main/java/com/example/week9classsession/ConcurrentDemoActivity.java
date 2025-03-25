package com.example.week9classsession;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentDemoActivity extends AppCompatActivity {
    final static String TAG = "INFOSYS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ConcurrentDemo
//        Log.d(TAG, "Main Thread prints this");
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 4; i++) { // can you guess the output log if i<4?
//            String k = String.valueOf(i); // used for printing below
//            executor.execute(new Runnable() { // this task is executed by the background thread
//                @Override
//                public void run() {
//                    Log.d(TAG, "This is executed by background thread no " + k);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    Log.d(TAG, "This is also executed by background thread no " + k);
//                }
//            });
//        }
//        // this is in main thread
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Log.d(TAG, "Main Thread prints this again");

        // Sharing data between main thread and background thread
//        int s = 0;
//        final Container<Integer> cs = new Container<>(s);
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                // a new thread
//                int s1 = cs.get() + 1;
//                cs.set(s1);
//            }
//        });

        // Using handler from the background thread to execute task in main thread
        // main thread (i.e. UI thread)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Looper uiLooper = Looper.getMainLooper(); // get the main looper
        final Handler handler = new Handler(uiLooper); // get the handler for the main thread

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // instructions performed in the child thread
                Log.d(TAG, "run: Child thread executing something");

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread will receive and run this
                        Log.d(TAG, "run: Main thread execute this after " +
                                "background thread finish the task");
                    }
                });
            }
        });
        
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

class Container<T>{
    T value;
    Container(T v) { this.value = v; }
    void set(T v) { this.value = v; }
    T get() { return this.value; }
}
