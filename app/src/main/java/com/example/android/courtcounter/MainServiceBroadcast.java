package com.example.android.courtcounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MainServiceBroadcast extends BroadcastReceiver {
    String TAG = MainServiceBroadcast.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"broadcast received");

        /**
        Intent i = new Intent(context,MyService.class);
        context.startService(i);
         */

        Util.scheduleJob(context);

        Log.e(TAG,"broadcast ended");
    }
}
