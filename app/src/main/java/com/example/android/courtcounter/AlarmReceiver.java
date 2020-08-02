package com.example.android.courtcounter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
//import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class AlarmReceiver extends BroadcastReceiver {

    String CHANNEL_ID = "KARATEST";

    @Override
    public void onReceive(Context context, Intent intent) {
        // show toast
        Log.e("MyService","aagaya beta");
        Toast.makeText(context, "Alarm running", Toast.LENGTH_SHORT).show();

        showNotification(context);

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