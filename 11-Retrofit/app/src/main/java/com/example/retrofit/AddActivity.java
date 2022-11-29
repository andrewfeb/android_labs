package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
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

public class AddActivity extends AppCompatActivity {
    private EditText edtFirstName, edtLastName, edtEmail;
    private Button btnCancel, btnInsert;
    private String firstName, lastName, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initComponents();
    }

    private void initComponents() {
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtEmail = findViewById(R.id.edtEmail);
        btnCancel = findViewById(R.id.btnCancel);
        btnInsert = findViewById(R.id.btnInsert);

        btnCancel.setOnClickListener(this::onClick);
        btnInsert.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        if (view.getId() == R.id.btnInsert) {
            save();
        }else if (view.getId() == R.id.btnCancel) {
            finish();
        }
    }

    private void save() {
        firstName = edtFirstName.getText().toString().trim();
        lastName = edtLastName.getText().toString().trim();
        email = edtEmail.getText().toString().trim();

        if(validation()) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);

            UserService userService = ApiClient.getInstance().create(UserService.class);
            Call<User> insertUser = userService.insertUser(user);

            insertUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(getApplicationContext(), "Insert successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validation() {
        boolean isValid = true;
        if (firstName.length() == 0){
            isValid = false;
            edtFirstName.setError("Please entry the first name");
        }

        if (lastName.length() == 0){
            isValid = false;
            edtLastName.setError("Please entry the last name");
        }

        if (email.length() == 0){
            isValid = false;
            edtEmail.setError("Please entry the email");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isValid = false;
            edtEmail.setError("This email is not valid");
        }

        return isValid;
    }
}