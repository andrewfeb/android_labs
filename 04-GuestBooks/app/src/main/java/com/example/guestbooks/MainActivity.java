package com.example.guestbooks;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guestbooks.models.Guest;

public class MainActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtPhone;
    Guest guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        Button btnDetail1 = findViewById(R.id.btnDetail1);
        Button btnDetail2 = findViewById(R.id.btnDetail2);
        Button btnGithub = findViewById(R.id.btnGithub);
        Button btnResultActivity = findViewById(R.id.btnResultActivity);

        btnDetail1.setOnClickListener(this::onClick);
        btnDetail2.setOnClickListener(this::onClick);
        btnGithub.setOnClickListener(this::onClick);
        btnResultActivity.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnDetail1) {
            goToDetailIntent();
        } else if (view.getId() == R.id.btnDetail2) {
            goToDetailParcelable();
        } else if (view.getId() == R.id.btnResultActivity) {
            getResult();
        } else {
            gotToGithub();
        }
    }

    private void input() {
        String name,email,phone;
        name = edtName.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
        phone = edtPhone.getText().toString().trim();

        guest = new Guest(name,email,phone);
    }

    private void goToDetailIntent(){
        input();

        if (validation()) {
            Intent detail = new Intent(this, DetailActivity.class);
            detail.putExtra("NAME", guest.getName());
            detail.putExtra("EMAIL", guest.getEmail());
            detail.putExtra("PHONE", guest.getPhone());

            startActivity(detail);
        }
    }

    private void goToDetailParcelable(){
        input();

        if (validation()){
            Intent detail = new Intent(this, DetailActivity.class);
            detail.putExtra("GUEST", guest);

            startActivity(detail);
        }
    }

    private void gotToGithub(){
        Uri github = Uri.parse("https://github.com/andrewfeb");
        Intent intent = new Intent(Intent.ACTION_VIEW,github);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d("Implicit Intent", "Can't handle this intent!");
        }
    }

    private ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent= result.getData();
                        String msg = intent.getStringExtra("MESSAGE");
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private void getResult() {
        input();

        if (validation()){
            Intent detail = new Intent(this, DetailActivity.class);
            detail.putExtra("GUEST", guest);

            startForResult.launch(detail);
        }
    }

    private boolean validation(){
        boolean isValid = true;

        if (guest.getName().length() == 0){
            isValid = false;
            edtName.setError("Please entry the name");
        }

        if (guest.getEmail().length() == 0){
            isValid = false;
            edtEmail.setError("Please entry the email address");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(guest.getEmail()).matches())
        {
            isValid = false;
            edtEmail.setError("Email is not valid");
        }

        if (guest.getPhone().length() == 0){
            isValid = false;
            edtPhone.setError("Please entry the phone");
        }


        return isValid;
    }
}