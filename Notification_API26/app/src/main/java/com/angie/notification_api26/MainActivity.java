package com.angie.notification_api26;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.angie.notification_api26.App.CHANNEL_1_ID;
import static com.angie.notification_api26.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    // This can create notification channels
    private NotificationManagerCompat notificationManagerCompat;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: 获取通知控制类
        notificationManagerCompat = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
    }

    public void sendOnChannel1(View v) {

        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_ondemand_video_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)          // to support low API level < OREL
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setChannelId(CHANNEL_1_ID)
                .build();

        notificationManagerCompat.notify(1, notification);

    }

    public void sendOnChannel2(View v) {

        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_ondemand_video_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)          // to support low API level < OREL
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setChannelId(CHANNEL_2_ID)
                .build();

        notificationManagerCompat.notify(2, notification);

    }

}
