package com.example.week9classsession;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingHandlerExample extends AppCompatActivity {
    public final String TAG = "INFOSYS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}