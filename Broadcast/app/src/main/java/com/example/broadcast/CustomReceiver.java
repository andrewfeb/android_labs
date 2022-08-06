package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String intentAction = intent.getAction();
        if (intentAction != null) {
            String message = "intent action tidak diketahui";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    message = "Power connected";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    message = "Power disconnected";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    message = "Custom Broadcast Received";
                    break;
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}