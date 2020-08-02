package com.example.android.courtcounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i= new Intent(context, MyService.class);
        // potentially add data to the intent
        Log.e("StartBroadcast","start broadcast received");
        i.putExtra("KEY1", "Value to be used by the service");
        context.startService(i);
    }
}
