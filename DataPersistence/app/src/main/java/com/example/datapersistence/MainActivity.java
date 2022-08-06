package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText edtInput;
    private Button btnSave, btnShow, btnDelete;
    private String filename;

    private Button btnSaveShared, btnShowShared, btnClearShared;
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile = "com.example.datapersistence";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filename = "data.txt";

        edtInput = findViewById(R.id.edtInput);

        // File
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        btnDelete = findViewById(R.id.btnDelete);

        btnSave.setOnClickListener(this::onClick);
        btnShow.setOnClickListener(this::onClick);
        btnDelete.setOnClickListener(this::onClick);

        // Shared Preferences
        btnSaveShared = findViewById(R.id.btnSaveShared);
        btnShowShared = findViewById(R.id.btnShowShared);
        btnClearShared = findViewById(R.id.btnClearShared);

        btnSaveShared.setOnClickListener(this::onClick);
        btnShowShared.setOnClickListener(this::onClick);
        btnClearShared.setOnClickListener(this::onClick);

        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }

    private void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnSave:
                saveToFile(edtInput.getText().toString(), filename);
                break;
            case R.id.btnShow:
                Toast.makeText(this, getDataFromFile(filename), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDelete:
                removeFile(filename);
                break;
            case R.id.btnSaveShared:
                saveToShared(edtInput.getText().toString());
                break;
            case R.id.btnShowShared:
                showDataFromShared();
                break;
            case R.id.btnClearShared:
                clearDataShared();
                break;
            default:
                break;
        }
    }

    private void saveToShared(String input) {
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putString("input", input);
        preferencesEditor.apply();
        Toast.makeText(this, "Data berhasil disimpan di Shared Preference", Toast.LENGTH_SHORT).show();
    }

    private void showDataFromShared() {
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String data = sharedPreferences.getString("input", "");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    private void clearDataShared() {
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.clear();
        //preferencesEditor.remove("input"); //digunakan untuk menghapus data tertentu
        preferencesEditor.apply();
        Toast.makeText(this, "Data berhasil dihapus dari Shared Preference", Toast.LENGTH_SHORT).show();
    }

    private void removeFile(String filename) {
        File file = new File(this.getFilesDir(), filename);
        if (file.exists()){
            file.delete();
            Toast.makeText(this, "File berhasil dihapus", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "File tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveToFile(String input, String filename){
        try {
            File file = new File(this.getFilesDir(), filename);
            FileWriter writer = new FileWriter(file, true);
            writer.append(input);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
        }
    }

    private String getDataFromFile(String filename) {
        String data = "Data tidak ada";
        try{
            File file = new File(this.getFilesDir(), filename);
            if (file.exists()) {
                FileReader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);
                String line;
                data="";
                while ((line = br.readLine()) != null) {
                    data += line;
                }
                reader.close();
            }
        }catch (IOException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return data;
    }
}