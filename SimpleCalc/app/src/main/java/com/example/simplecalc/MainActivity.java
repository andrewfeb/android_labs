package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvHasil;
    private EditText edtAngka1, edtAngka2;
    private Button btnTambah, btnKurang, btnKali, btnBagi;
    private String angka1, angka2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        tvHasil = findViewById(R.id.tvHasil);
        edtAngka1 = findViewById(R.id.edtAngka1);
        edtAngka2 = findViewById(R.id.edtAngka2);
        btnTambah = findViewById(R.id.btnTambah);
        btnKurang = findViewById(R.id.btnKurang);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);

        btnTambah.setOnClickListener(this::onClick);
        btnKurang.setOnClickListener(this::onClick);
        btnKali.setOnClickListener(this::onClick);
        btnBagi.setOnClickListener(this::onClick);
    }

    public void onClick(View v)
    {
        angka1 = edtAngka1.getText().toString().trim();
        angka2 = edtAngka2.getText().toString().trim();
        String hasil;
        if (validation()) {
            switch (v.getId()) {
                case R.id.btnTambah:
                    hasil = angka1 + " + " + angka2 + " = " + (Integer.parseInt(angka1) + Integer.parseInt(angka2));
                    break;
                case R.id.btnKurang:
                    hasil = angka1 + " - " + angka2 + " = " + (Integer.parseInt(angka1) - Integer.parseInt(angka2));
                    break;
                case R.id.btnKali:
                    hasil = angka1 + " * " + angka2 + " = " + (Integer.parseInt(angka1) * Integer.parseInt(angka2));
                    break;
                case R.id.btnBagi:
                    hasil = angka1 + " / " + angka2 + " = " + (Float.parseFloat(angka1) / Float.parseFloat(angka2));
                    break;
                default:
                    hasil="";
                    break;
            }
            tvHasil.setText(hasil);
        }
    }

    private boolean validation()
    {
        boolean isValid = true;

        if (angka1.length() == 0){
            isValid = false;
            edtAngka1.setError("Angka 1 tidak boleh kosong");
        }

        if (angka2.length() == 0){
            isValid = false;
            edtAngka2.setError("Angka 2 tidak boleh kosong");
        }

        return isValid;
    }
}