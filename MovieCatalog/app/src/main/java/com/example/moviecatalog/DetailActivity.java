package com.example.moviecatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgCover;
    private TextView tvTitle, tvReleaseYear, tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Detail Movie");
        imgCover = findViewById(R.id.imgDetailCover);
        tvTitle = findViewById(R.id.tvDetailTitle);
        tvReleaseYear = findViewById(R.id.tvDetailReleaseYear);
        tvDescription = findViewById(R.id.tvDetailDescription);

        Intent intent = getIntent();
        Movie movie;
        int position = intent.getIntExtra("position", 0);

        movie = DataMovie.getDataMovie(position);
        //Toast.makeText(this, movie.getTitle(), Toast.LENGTH_SHORT).show();
        imgCover.setImageResource(movie.getCover());
        tvTitle.setText(movie.getTitle());
        tvReleaseYear.setText(movie.getReleaseYear());
        tvDescription.setText(movie.getDescription());

    }
}