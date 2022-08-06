package com.example.moviecatalogdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private EditText edtTitle, edtReleaseYear, edtDescription;
    private Button btnSave, btnCancel, btnUpload;
    private String title, releaseYear, description;
    private DBHelper db;
    private int idMovie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        db = new DBHelper(this);

        edtTitle = findViewById(R.id.edtTitle);
        edtReleaseYear = findViewById(R.id.edtReleaseYear);
        edtDescription = findViewById(R.id.edtDescription);
        btnUpload = findViewById(R.id.btnUpload);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this::click);
        btnCancel.setOnClickListener(this::click);
        btnUpload.setOnClickListener(this::click);

        getData();
    }

    private void click(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                save();
                break;
            case R.id.btnUpload:
                break;
            default:
                break;
        }
    }

    private void getData() {

        try {
            Intent intent = getIntent();
            if (intent.hasExtra("idMovie")) {
                int id = intent.getIntExtra("idMovie", 1);

                DBHelper db = new DBHelper(this);
                Movie movie = db.getMovie(id);

                edtTitle.setText(movie.getTitle());
                edtReleaseYear.setText(movie.getReleaseYear());
                edtDescription.setText(movie.getDescription());
                idMovie = movie.getId();
            }
        }catch(Exception ex) {
            Log.d("error", ex.getMessage());
        }
    }

    private void save() {
        title = edtTitle.getText().toString();
        releaseYear = edtReleaseYear.getText().toString();
        description = edtDescription.getText().toString();

        if (validation()){
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setReleaseYear(releaseYear);
            movie.setDescription(description);

            if (idMovie == 0) {
                if (db.insertMovie(movie) > 0) {
                    resetForm();
                    finish();
                    Toast.makeText(this, "Data Movie berhasil ditambah", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Data Movie gagal ditambah", Toast.LENGTH_SHORT).show();
                }
            }else{
                movie.setId(idMovie);
                if(db.updateMovie(movie) > 0) {
                    resetForm();
                    Toast.makeText(this, "Data Movie berhasil diupdate", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Data Movie gagal diupdate", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void resetForm() {
        edtTitle.setText("");
        edtReleaseYear.setText("");
        edtDescription.setText("");
    }

    private boolean validation()
    {
        boolean isValid = true;
        if (title.length() == 0){
            isValid = false;
            edtTitle.setError("Title harus diisi");
        }

        if (releaseYear.length() == 0){
            isValid = false;
            edtReleaseYear.setError("Title harus diisi");
        }

        return isValid;
    }
}