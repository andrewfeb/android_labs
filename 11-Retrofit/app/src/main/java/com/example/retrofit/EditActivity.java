package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit.models.User;
import com.example.retrofit.services.ApiClient;
import com.example.retrofit.services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    private EditText edtFirstName, edtLastName, edtEmail;
    private Button btnCancel, btnUpdate;
    private int  userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtEmail = findViewById(R.id.edtEmail);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");
        edtFirstName.setText(user.getFirstName());
        edtLastName.setText(user.getLastName());
        edtEmail.setText(user.getEmail());
        userId = user.getId();

        btnCancel.setOnClickListener(this::onClick);
        btnUpdate.setOnClickListener(this::onClick);
    }

    public void onClick(View view){
        if (view.getId() == R.id.btnCancel) {
            finish();
        }else if(view.getId() == R.id.btnUpdate) {
            UserService userService = ApiClient.getInstance().create(UserService.class);
            Call<User> updateUser = userService.updateUser(
                    userId,
                    edtFirstName.getText().toString(),
                    edtLastName.getText().toString(),
                    edtEmail.getText().toString()
            );
            updateUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(getApplicationContext(), "Update successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}