package com.example.intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GuestBookShow extends AppCompatActivity {

    private TextView tvName, tvEmail, tvHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_book_show);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvHP = findViewById(R.id.tvHP);

        Intent data = getIntent();
        tvName.setText("Nama: " + data.getStringExtra("nameGuest"));
        tvEmail.setText("Email: " + data.getStringExtra("email"));
        tvHP.setText("HP: " + data.getStringExtra("hp"));
        //tvName.setText(data.getData().toString());
    }
}