package com.example.android.courtcounter;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import java.util.Calendar;

public class Util {

    private static final String TAG = "Util";

    public static void scheduleJob(Context context) {
        Log.e(TAG,"Util received");
        ComponentName serviceComponent = new ComponentName(context, TestJobService.class);
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
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        Log.e(TAG,"Util 2nd last");
        jobScheduler.schedule(builder.build());
        Log.e(TAG,"Util last");
    }
}
