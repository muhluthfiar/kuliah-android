package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notifyManager;
    private static final int NOTIFICATION_ID = 0;
    Button notifyButton;
    String CHANNEL_ID = "my_channel_01";
    int notifyID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifyButton = (Button) findViewById(R.id.notify);


        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void sendNotification() {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_notify)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification myNotification = notifyBuilder.build();
        notifyManager.notify(NOTIFICATION_ID, myNotification);

    }
}