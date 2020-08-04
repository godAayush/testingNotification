package com.example.android.courtcounter;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class TestJobService extends JobService {
    private static final String TAG = "SyncService";

    AlarmManager manager;

    String CHANNEL_ID = "KARATEST";

    @Override
    public boolean onStartJob(JobParameters params) {
        //Intent service = new Intent(getApplicationContext(), MyService.class);
        Log.e(TAG,"onstartjob");
        //getApplicationContext().startService(service);
         // reschedule the job

        //createNotificationChannel();

        Log.e(TAG,"aagaya beta");
        Toast.makeText(getApplicationContext(), "Alarm running", Toast.LENGTH_SHORT).show();

        showNotification(getApplicationContext());




        /**

        Intent broadIntent = new Intent(getApplicationContext(), ServiceBroadcast.class);
        sendBroadcast(broadIntent);
         */


        //MyService.scheduleJob();



        Log.e(TAG,"end of onStartJob");
        return false;  //AAYUSH CHANGED NOTICE
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG," onstopjob");
        Intent broadIntent = new Intent(getApplicationContext(), MainServiceBroadcast.class);
        sendBroadcast(broadIntent);
        return true;
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.e(TAG,"service task removed");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"service destroy starts");
        Intent broadIntent = new Intent(getApplicationContext(), MainServiceBroadcast.class);
        sendBroadcast(broadIntent);
        Log.e(TAG,"service destroyed end");
        //Intent broadIntent = new Intent(getApplicationContext(), ServiceBroadcast.class);
        //sendBroadcast(broadIntent);
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

    private void showNotification(Context ctx) {

        //String eventtext = "hello frans chai pilo";

        /**

         NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

         // Set the icon, scrolling text and timestamp
         //Notification mNotification = new Notification(R.mipmap.ic_launcher, "Reminder", System.currentTimeMillis());
         //The three parameters are: 1. an icon, 2. a title, 3. time when the notification appears

         // The PendingIntent to launch our activity if the user selects this
         // notification
         Intent MyIntent = new Intent(Intent.ACTION_VIEW);
         PendingIntent StartIntent = PendingIntent.getActivity(ctx,0,MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
         //A PendingIntent will be fired when the notification is clicked. The FLAG_CANCEL_CURRENT flag cancels the pendingintent

         Notification.Builder builder = new Notification.Builder(ctx)
         .setContentIntent(StartIntent)
         .setSmallIcon(R.mipmap.ic_launcher)
         .setContentTitle("Medicine")
         .setContentText("Don't forget to take your medicine!");
         Notification mNotification = builder.build();

         */

        /**

         String MyNotificationTitle = "Medicine!";
         String MyNotificationText  = "Don't forget to take your medicine!";
         // Set the info for the views that show in the notification panel.
         mNotification.setLatestEventInfo(ctx, MyNotificationTitle, MyNotificationText, StartIntent);
         */

        /**
         int NOTIFICATION_ID = 1;
         mNotificationManager.notify(NOTIFICATION_ID , mNotification);
         */
        //We are passing the notification to the NotificationManager with a unique id.

        String MyNotificationTitle = "AAYUSH DA DHABA";
        String MyNotificationText  = "Hello frands .. chai pilo";



        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(MyNotificationTitle)
                .setContentText(MyNotificationText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);

        int NOTIFICATION_ID = 1;

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }



}