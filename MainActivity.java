package com.example.vinay_thakur.samplenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    String ACTION_SNOOZE="action_snooz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
