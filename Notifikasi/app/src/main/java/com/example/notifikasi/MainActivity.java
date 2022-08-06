package com.example.notifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSend;
    private final String CHANNEL_ID = "primary_notification_channel";
    private NotificationManager notifManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this::onClick);
        notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSend:
                sendNotification();
                break;
        }
    }

    private void sendNotification() {
        Intent notifIntent = new Intent(this, MainActivity.class);

        PendingIntent notifPendingIntent = PendingIntent.getActivity(this, 0, notifIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("Notifikasi")
                .setContentText("belajar membuat notifikasi")
                .setContentIntent(notifPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        Notification notif = notifBuilder.build();
        notifManager.notify(0, notif);
    }

}