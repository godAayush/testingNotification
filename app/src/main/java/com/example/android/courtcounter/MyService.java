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
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        Log.e(TAG,"service started");
        createNotificationChannel();
        Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);

        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 5);
        calendar.set(Calendar.MINUTE, 56);
        calendar.set(Calendar.SECOND, 0);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                2*60*1000, pendingIntent);
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
