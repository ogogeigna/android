package com.angie.notification_api26;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    // 输入 psfs => public static final String
    // TODO: Declare the channel ID
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";


    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }


    // TODO: Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
    private void createNotificationChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, "channel 1", NotificationManager.IMPORTANCE_HIGH);
            // 这是users可以看到的
            channel1.setDescription("This is channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );

            // 这是users可以看到的
            channel2.setDescription("This is channel 2");

            // TODO: Submit the channels to the notification manager, to let it know the channels
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}


// TODO: Register the App class into the Manifest
