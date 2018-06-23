package com.example.vinay_thakur.samplenotification;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by vinay_thakur on 6/23/2018.
 */

public class NotificationService extends IntentService {
    String ACTION_SNOOZE="snooz";
    public NotificationService() {
        super("vinay");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent i1=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent1=PendingIntent.getActivity(this,0,i1,0);
        Intent i=new Intent(this,NotificationReceiver.class);
        i.setAction(ACTION_SNOOZE);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,i,0);
        NotificationCompat.Builder notification =new NotificationCompat.Builder(this);
        notification.setContentTitle("sample notification");
        notification.setContentText("hello");
        notification.setSmallIcon(R.drawable.ic_stat_name);
        notification.setAutoCancel(true);
        notification.addAction(R.drawable.ic_stat_name,"snooz",pendingIntent);
        notification.setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(this);
        notificationManager.notify(123,notification.build());
        Log.d("vinay149","fff");
        stopSelf();
    }

}
