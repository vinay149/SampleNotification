package com.example.vinay_thakur.samplenotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.doc);
        bitmap=getCroppedBitmap(bitmap);
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O) {
            String Channelid = "my_channel_id";
            String desc = "this is my channel";
            int imp = NotificationManager.IMPORTANCE_LOW;
            String name = "NOTICE";
            NotificationChannel notificationChannel = new NotificationChannel(Channelid, name, imp);
            notificationChannel.setDescription(desc);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }
        Intent i1=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent1=PendingIntent.getActivity(this,0,i1,0);
        Intent i=new Intent(this,NotificationReceiver.class);
        i.setAction(ACTION_SNOOZE);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,i,0);
        NotificationCompat.Builder notification =new NotificationCompat.Builder(this);
        notification.setContentTitle("Sample notification");
        notification.setContentText("Hello");
        notification.setSmallIcon(R.drawable.ic_stat_name);
        notification.setAutoCancel(true);
        notification.setLargeIcon(bitmap);
        notification.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap)).build();
        //notification.setStyle(new NotificationCompat.BigPictureStyle().bigLargeIcon(null)).build();
        //notification.setStyle(new NotificationCompat.BigTextStyle().bigText("hey my name is vinay  kumar yadav  as i professional i am a software engineer hey my name is vinay  kumar yadav  as i professional i am a software engineer hey my name is vinay  kumar yadav  as i professional i am a software engineer")).build();
        notification.addAction(R.drawable.ic_stat_name,"snooz",pendingIntent);
        notification.setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(this);
        notificationManager.notify(123,notification.build());
    }
    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }
}
