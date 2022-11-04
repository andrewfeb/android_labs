package com.example.guestbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guestbooks.models.Guest;

public class DetailActivity extends AppCompatActivity {
    private TextView tvName,tvEmail,tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail Guest");

        initComponents();
        setData();
    }

    private void initComponents(){
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
    }

    private void setData(){
        Intent intent = getIntent();
        if (intent.hasExtra("GUEST")){
            Log.i("Parcelable", "send data using parcelable");
            Guest guest = intent.getParcelableExtra("GUEST");
            tvName.setText(guest.getName());
            tvEmail.setText(guest.getEmail());
            tvPhone.setText(guest.getPhone());
        }else {
            Log.i("Parcelable", "send data using intent extra");
            tvName.setText(intent.getStringExtra("NAME"));
            tvEmail.setText(intent.getStringExtra("EMAIL"));
            tvPhone.setText(intent.getStringExtra("PHONE"));
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("MESSAGE", "Detail activity is closed");
        setResult(RESULT_OK, data);
        super.finish();
    }
}