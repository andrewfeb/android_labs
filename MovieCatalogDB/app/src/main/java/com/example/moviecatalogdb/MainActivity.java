package com.example.moviecatalogdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMovie;
    private TextView tvMessage;
    private DBHelper db;
    private Button btnAdd;
    private final String FILE_ACTIVATION = "activated.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = findViewById(R.id.tvMessage);
        btnAdd = findViewById(R.id.btnAdd);

        rvMovie = findViewById(R.id.rvMovie);
        rvMovie.setHasFixedSize(true);
        db = new DBHelper(this);

        btnAdd.setOnClickListener(this::onClick);
        checkTrial();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showRecyclerList();
        checkData();
    }

    private void onClick(View view) {
        if (view.getId() == R.id.btnAdd) {
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
        }
    }

    private void checkTrial(){
        File file = new File(this.getFilesDir(), FILE_ACTIVATION);
        try {
        if (file.exists()) {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line;
            String oldDate="";
            String newDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            while((line = br.readLine()) != null) {
                oldDate = line;
            }
            reader.close();
            int dateDiff = (int) dateDiff(new SimpleDateFormat("yyyy-MM-dd"),oldDate, newDate);
            if (dateDiff > 30) {
                Intent intent = new Intent(this, LicenseActivity.class);
                startActivity(intent);
                finish();
            }

        }else{

                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                FileWriter writer = new FileWriter(file);
                writer.append(date);
                writer.flush();
                writer.close();
        }
        }catch(Exception ex) {
            Log.d("error", ex.getMessage());
        }
    }

    private long dateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        }catch (Exception e) {
            return 0;
        }
    }

    private void showRecyclerList() {
        ArrayList<Movie> list = db.getAllMovie();
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter movieAdapter = new MovieAdapter(list);
        rvMovie.setAdapter(movieAdapter);
    }

    private void checkData() {
        if (rvMovie.getAdapter().getItemCount() == 0) {
            tvMessage.setVisibility(View.VISIBLE);
            rvMovie.setVisibility(View.GONE);
        }else{
            tvMessage.setVisibility(View.GONE);
            rvMovie.setVisibility(View.VISIBLE);
        }
    }
}