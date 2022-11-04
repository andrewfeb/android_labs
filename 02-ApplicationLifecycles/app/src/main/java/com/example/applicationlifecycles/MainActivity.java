package com.example.applicationlifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btnGotoActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate " + TAG, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate " + TAG);

        btnGotoActivity2 = findViewById(R.id.btnGotoActivity2);
        btnGotoActivity2.setOnClickListener(this::gotoActivity2);
    }

    public void gotoActivity2(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
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