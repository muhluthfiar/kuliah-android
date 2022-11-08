package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notifyManager;
    private static final int NOTIFICATION_ID = 0;
    Button notifyButton;
    Button updateButton;
    Button cancelButton;
    String CHANNEL_ID = "my_channel_01";
    int notifyID = 1;
    public final String RANDOM_URL = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
    public final String TOUCH_GRASS = "data:image/jpeg;base64,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";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifyButton = (Button) findViewById(R.id.notify);
        updateButton = (Button) findViewById(R.id.update);
        cancelButton = (Button) findViewById(R.id.cancel);

        notifyButton.setEnabled(true);
        updateButton.setEnabled(false);
        cancelButton.setEnabled(false);

        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });

        notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void sendNotification() {
//        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("You've been notified!")
//                .setContentText("This is your notification text.")
//                .setSmallIcon(R.drawable.ic_notify)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        Notification myNotification = notifyBuilder.build();
//        notifyManager.notify(NOTIFICATION_ID, myNotification);

        notifyButton.setEnabled(false);
        updateButton.setEnabled(true);
        cancelButton.setEnabled(true);

        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(RANDOM_URL));

        PendingIntent learnMorePendingIntent = PendingIntent.getActivity(
                this, notifyID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT
        );

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;

        Context context = getApplicationContext();
        Resources res = context.getResources();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String CHANNEL_ID = "alex_channel";

            // https://developer.android.com/training/notify-user/channels
            // https://medium.com/exploring-android/exploring-android-o-notification-channels-94cd274f604c
            // https://startandroid.ru/ru/uroki/vse-uroki-spiskom/515-urok-190-notifications-kanaly.html
            // https://code.tutsplus.com/ru/tutorials/android-o-how-to-use-notification-channels--cms-28616

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "AlexChannel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Alex channel description");
            manager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        }
        else
        {
            builder = new NotificationCompat.Builder(context);
        }

        // https://developer.android.com/reference/android/app/PendingIntent
        PendingIntent action = PendingIntent.getActivity(context,
                0, new Intent(context, MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT); // Flag indicating that if the described PendingIntent already exists, the current one should be canceled before generating a new one.

        builder.setContentIntent(action)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_notify))
                .setSmallIcon(R.drawable.ic_notify)
                .setTicker("Small text!")
                .setAutoCancel(true) // make this notification automatically dismissed when the user touches it
                .setContentTitle("Title")
                .addAction(R.drawable.ic_click,"Learn More", learnMorePendingIntent)
                .setContentText("Notification text");

        Notification notification = builder.build();

        int notificationCode = (int) (Math.random() * 1000);
        manager.notify(notifyID, notification);
    }

    public void updateNotification() {

        notifyButton.setEnabled(false);
        updateButton.setEnabled(false);
        cancelButton.setEnabled(true);

        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(RANDOM_URL));

        PendingIntent learnMorePendingIntent = PendingIntent.getActivity(
                this, notifyID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT
        );

        Intent goTouchGrassIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(TOUCH_GRASS));

        PendingIntent TouchGrassIntent = PendingIntent.getActivity(this, notifyID, goTouchGrassIntent, PendingIntent.FLAG_ONE_SHOT);

        Bitmap androidImage = BitmapFactory
                .decodeResource(getResources(),R.drawable.soeharto_slebew);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;

        Context context = getApplicationContext();
        Resources res = context.getResources();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String CHANNEL_ID = "alex_channel";

            // https://developer.android.com/training/notify-user/channels
            // https://medium.com/exploring-android/exploring-android-o-notification-channels-94cd274f604c
            // https://startandroid.ru/ru/uroki/vse-uroki-spiskom/515-urok-190-notifications-kanaly.html
            // https://code.tutsplus.com/ru/tutorials/android-o-how-to-use-notification-channels--cms-28616

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "AlexChannel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Alex channel description");
            manager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        }
        else
        {
            builder = new NotificationCompat.Builder(context);
        }

        // https://developer.android.com/reference/android/app/PendingIntent
        PendingIntent action = PendingIntent.getActivity(context,
                0, new Intent(context, MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT); // Flag indicating that if the described PendingIntent already exists, the current one should be canceled before generating a new one.

        builder.setContentIntent(action)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_notify))
                .setSmallIcon(R.drawable.ic_notify)
                .setTicker("Small text!")
                .setAutoCancel(true) // make this notification automatically dismissed when the user touches it
                .setContentTitle("Title")
                .setContentText("Notification text")
                .setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(androidImage)
                    .setBigContentTitle("Notification Updated!"))
                .addAction(R.drawable.ic_click,"Learn More", learnMorePendingIntent)
                .addAction(R.drawable.ic_touch, "Touch Grass", TouchGrassIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = builder.build();

        int notificationCode = (int) (Math.random() * 1000);
        manager.notify(notifyID, notification);
    }

    public void cancelNotification() {
        notifyButton.setEnabled(true);
        updateButton.setEnabled(false);
        cancelButton.setEnabled(false);

        notifyManager.cancel(notifyID);
    }



}