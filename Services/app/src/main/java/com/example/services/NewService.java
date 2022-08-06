package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NewService extends Service {

    private MediaPlayer player;

    public NewService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try{
            player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
            player.setLooping(true);
            player.start();

        }catch (Exception ex){
            Toast.makeText(this, "Default Ringtone not found", Toast.LENGTH_SHORT).show();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        try {
            super.onDestroy();
            if (player.isPlaying()) {
                player.stop();
            }
        }catch (Exception ex){
            Toast.makeText(this, "Service is stop", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}