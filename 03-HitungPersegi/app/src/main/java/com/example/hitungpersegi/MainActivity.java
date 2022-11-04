package com.example.hitungpersegi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private EditText edtSisi;
    private Button btnCalculate;
    private static final String STATE_RESULT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        edtSisi = findViewById(R.id.edtSisi);
        btnCalculate = findViewById(R.id.btnHitung);
        //btnCalculate.setOnClickListener(this::onClick);
        btnCalculate.setOnClickListener(this::onClick);

        if (savedInstanceState != null) {
            tvResult.setText(savedInstanceState.getString(STATE_RESULT));
        }
    }

    public void onClick(View view) {
        int sisi = Integer.parseInt(edtSisi.getText().toString().trim());
        tvResult.setText(calculate(sisi));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    private String calculate(int sisi) {
        int luas, keliling;
        luas = sisi * sisi;
        keliling = 4 * sisi;

        return "Luas: "+ luas + "\n" + "Keliling" + keliling;
    }
}