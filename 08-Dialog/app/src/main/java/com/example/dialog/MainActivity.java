package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private EditText edtTime, edtDate;
    public String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAlert = findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment alertFragment = new AlertFragment();
                alertFragment.show(getSupportFragmentManager(), "Alert");
            }
        });

        edtTime = findViewById(R.id.edtTime);
        edtDate = findViewById(R.id.edtDate);

        Button btnTime = findViewById(R.id.btnTime);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("MainActivity", "open timepicker");
                DialogFragment timeFragment = new TimePickerFragment(edtTime);
                timeFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        Button btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateFragment = new DatePickerFragment(edtDate);
                dateFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }
}