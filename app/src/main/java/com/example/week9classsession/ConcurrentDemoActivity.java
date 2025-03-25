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
        Log.d(TAG, "Main Thread prints this");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) { // can you guess the output log if i<4?
            String k = String.valueOf(i); // used for printing below
            executor.execute(new Runnable() { // this task is executed by the background thread
                @Override
                public void run() {
                    Log.d(TAG, "This is executed by background thread no " + k);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Log.d(TAG, "This is also executed by background thread no " + k);
                }
            });
        }
        // this is executed in main thread
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Log.d(TAG, "Main Thread prints this again");



//         Sharing data between main thread and background thread
        int s = 0;
        final Container<Integer> cs = new Container<>(s);
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        executor2.execute(new Runnable() {
            @Override
            public void run() {
                // a new thread
                int s1 = cs.get() + 1;
                cs.set(s1);
            }
        });

    }
}

class Container<T>{
    T value;
    Container(T v) { this.value = v; }
    void set(T v) { this.value = v; }
    T get() { return this.value; }
}
