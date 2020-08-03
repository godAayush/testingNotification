package com.example.android.courtcounter;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public class Util {
    public static void scheduleJob(Context context) {
        Log.e("MyService","Util received");
        ComponentName serviceComponent = new ComponentName(context, MyService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(1 * 1000); // wait at least
        builder.setOverrideDeadline(3 * 1000); // maximum delay
        Log.e("MyService","Util param set");
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        Log.e("MyService","Util 2nd last");
        jobScheduler.schedule(builder.build());
        Log.e("MyService","Util last");
    }
}
