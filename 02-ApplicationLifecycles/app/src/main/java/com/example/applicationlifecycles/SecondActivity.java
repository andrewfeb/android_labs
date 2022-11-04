package com.example.applicationlifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast.makeText(this, "onCreate " + TAG, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate MainActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart " + TAG, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart " + TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume " + TAG, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume " + TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause " + TAG, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause " + TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop " + TAG, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop " + TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart " + TAG, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart " + TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy " + TAG, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy " + TAG);
    }
}