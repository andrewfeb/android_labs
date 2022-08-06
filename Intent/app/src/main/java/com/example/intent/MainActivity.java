package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnGuestBook;
    private Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuestBook = findViewById(R.id.btnGuestBooks);
        btnGuestBook.setOnClickListener(this::onClick);

        btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(this::onClick);
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.btnGuestBooks){
            Intent guestIntent = new Intent(this, GuestBookForm.class);
            startActivity(guestIntent);
        }else if (v.getId() == R.id.btnAbout){
            Uri webPage = Uri.parse("https://ebiz.co.id");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
            startActivity(webIntent);
        }
    }
}