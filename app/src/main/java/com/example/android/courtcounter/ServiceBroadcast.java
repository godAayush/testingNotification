package com.example.android.courtcounter;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class ServiceBroadcast extends BroadcastReceiver {
    String TAG = ServiceBroadcast.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"broadcast received");

        /**
        Intent i= new Intent(context, MyService.class);
        // potentially add data to the intent
        i.putExtra("KEY1", "Value to be used by the service");
        context.startService(i);
         */

        Util.scheduleJob(context);
        Log.e(TAG,"end of broadcast reciever");
    }
}
