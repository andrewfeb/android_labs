package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName;
    private Button btnShow;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        btnShow = findViewById(R.id.btnShow);
        tvResult = findViewById(R.id.tvResult);

        btnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnShow){
            /*String inputName = edtName.getText().toString().trim();
            Intent moveIntent = new Intent(MainActivity.this, )
            tvResult.setText(inputName);*/
            /*Uri uri = Uri.parse("https://www.google.com");
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);*/
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("Title", "Pelatihan Android");
            startActivity(intent);
            
        }
    }
}