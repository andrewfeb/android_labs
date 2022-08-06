package com.example.moviecatalogdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;

public class LicenseActivity extends AppCompatActivity {

    private Button btnActivation;
    private final String FILE_ACTIVATION = "activated.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        btnActivation = findViewById(R.id.btnActivation);
        btnActivation.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        try {
            File file = new File(this.getFilesDir(), FILE_ACTIVATION);
            FileWriter writer = new FileWriter(file);
            writer.append("");
            writer.flush();
            writer.close();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch(Exception ex){
            Log.d("Error", ex.getMessage());
        }
    }
}