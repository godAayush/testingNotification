package com.example.android.courtcounter;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate()");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e(TAG, "onLowMemory()");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e(TAG,"onterminate");
    }
}