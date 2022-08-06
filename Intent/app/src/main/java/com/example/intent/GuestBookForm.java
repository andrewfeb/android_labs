package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GuestBookForm extends AppCompatActivity {

    private EditText edtName, edtEmail, edtHP;
    private Button btnSend;
    private String name, email, hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_book_form);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtHP= findViewById(R.id.edtHP);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this::onClick);
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.btnSend) {
            name = edtName.getText().toString().trim();
            email = edtEmail.getText().toString().trim();
            hp = edtHP.getText().toString().trim();
            if (validation()) {
                //Log.d("Valid", "has been valid");
                Intent detailIntent = new Intent(this, GuestBookShow.class);
                detailIntent.putExtra("nameGuest", name);
                detailIntent.putExtra("email", email);
                detailIntent.putExtra("hp", hp);
                /*detailIntent.setData(Uri.parse(name));
                detailIntent.setData(Uri.parse(email));
                detailIntent.setData(Uri.parse(hp));*/
                startActivity(detailIntent);

            }
        }
    }

    private boolean validation()
    {
        boolean isValid = true;

        if (name.length() == 0) {
            isValid = false;
            edtName.setError("Nama tamu harus diisi");
        }

        if (email.length() == 0) {
            isValid = false;
            edtEmail.setError("Email harus diisi");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isValid = false;
            edtEmail.setError("Email tidak valid");
        }

        if (hp.length() == 0) {
            isValid = false;
            edtHP.setError("HP harus diisi");
        }

        return isValid;
    }
}