package com.example.android.courtcounter;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
//import 	androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MyService extends JobService {

    String CHANNEL_ID = "KARATEST";

    static JobScheduler jobScheduler ;

    AlarmManager manager;

    static Context ctx;

    private static final String TAG = MyService.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();
        jobScheduler = ctx.getSystemService(JobScheduler.class);
        Log.i(TAG,"2nd oncreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG,"2nd service onstart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        Log.e(TAG,"2nd service started on command");

        /**
        Intent broadIntent = new Intent(this, ServiceBroadcast.class);
        sendBroadcast(broadIntent);
         */

        scheduleJob();
        return Service.START_NOT_STICKY;
    }


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e(TAG," onStartJob");
        scheduleJob();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e(TAG," onstopjob");
        return true;
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
        Log.e(TAG,"2nd service task removed");
        Intent broadIntent = new Intent(getApplicationContext(), MainServiceBroadcast.class);
        sendBroadcast(broadIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"2nd service destroyed");
        Intent broadIntent = new Intent(getApplicationContext(), MainServiceBroadcast.class);
        sendBroadcast(broadIntent);
    }


    public static void scheduleJob() {
        String TAG = "utilService";
        Log.e(TAG,"Util received");
        ComponentName serviceComponent = new ComponentName(ctx, TestJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);

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

        Calendar currcal = Calendar.getInstance();
        currcal.setTimeInMillis(System.currentTimeMillis());

        builder.setMinimumLatency(calendar.getTimeInMillis()-currcal.getTimeInMillis()); // wait at least
        builder.setOverrideDeadline(calendar.getTimeInMillis()-currcal.getTimeInMillis()+2*1000); // maximum delay
        Log.e(TAG,"Util param set");
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        Log.e(TAG,"Util 2nd last");
        jobScheduler.schedule(builder.build());
        Log.e(TAG,"Util last");
    }

}
