package com.angie.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    NotificationManagerCompat notificationManager;
    String CHANNEL_ID = "channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        createNotificationChannels();
        // TODO: 获取通知控制类
        //notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    public void doClick(View view) {

        switch (view.getId()) {

            case R.id.send_notification:
                setSendNotification();
                break;

            case R.id.cancel:
                break;
        }
    }

    private void createNotificationChannels() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // TODO: Create the channel object with the unique ID (CHANNEL_ID)
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_HIGH);

            // TODO: Configure the channel's initial settings
            channel.setDescription("This is one channel");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this

            // TODO: Submit the notification channel object to the Notification Manager
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(channel);
        }
    }


    private void setSendNotification() {

        // TODO: 需要创建PendingIntent, 并设置点击意图 => 启动 MA
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        // TODO: You must pass the channel id, or your notification won't show up on the old device.
        // => you'll get an error
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setTicker("Hello")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Notification")
                .setContentText("This is from Notification channel.")
                .setChannelId(CHANNEL_ID);       // Pass the channel ID;

        builder.setContentIntent(pendingIntent);

// notificationId is a unique int for each notification that you must define

        // TODO:
        // Notification notification = builder.build();

        // 2 params:
        //          1. notification ID: 自定义
        //          2. Notification object which is to be sent
        // TODO: Issue the notification as normal
        notificationManager.notify(1, builder.build());

    }

}
