package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private  CustomReceiver receiver = new CustomReceiver();
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    private Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSendBroadcast);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        this.registerReceiver(receiver, filter);

        btnSend.setOnClickListener(this::onClick);
        this.registerReceiver(receiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    private void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(ACTION_CUSTOM_BROADCAST);
        //sendBroadcast(intent);
        sendOrderedBroadcast(intent,null,new CustomReceiver(),null,0,"Start", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver);

    }
}