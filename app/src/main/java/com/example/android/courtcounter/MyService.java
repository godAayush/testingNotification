package com.example.android.courtcounter;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
//import 	androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Calendar;

public class MyService extends Service {

    String CHANNEL_ID = "KARATEST";

    AlarmManager manager;

    private static final String TAG = MyService.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"oncreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG,"service onstart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        Log.e(TAG,"service started on command");
        createNotificationChannel();
        Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);

        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hr1 = calendar.get(Calendar.HOUR_OF_DAY);
        int min1 = calendar.get(Calendar.MINUTE); //doesn't work for time 11:58:00-11:59:59 pm
        if (min1 >= 58)
        {
            min1 = 0;
            if(hr1==23)
                hr1 = 0;
            else
                hr1 += 1;
        }
        else
            min1 = (min1/2)*2 + 2;
        Log.e(TAG,"hr "+hr1+" min "+min1);
        calendar.set(Calendar.HOUR_OF_DAY, hr1);
        calendar.set(Calendar.MINUTE, min1);
        calendar.set(Calendar.SECOND, 0);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                2*60*1000, pendingIntent); //here really speaking, the time period is irrelevant as service is destroyed by that time
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "karatest";
            String description = "for testing";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.e(TAG,"service task removed");
        Intent broadIntent = new Intent(getApplicationContext(), ServiceBroadcast.class);
        sendBroadcast(broadIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"service destroyed");
        Intent broadIntent = new Intent(getApplicationContext(), ServiceBroadcast.class);
        sendBroadcast(broadIntent);
    }
}
