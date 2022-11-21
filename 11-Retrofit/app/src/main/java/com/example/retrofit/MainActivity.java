package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retrofit.adapters.UserAdapter;
import com.example.retrofit.models.ListUser;
import com.example.retrofit.models.User;
import com.example.retrofit.services.ApiClient;
import com.example.retrofit.services.UserService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListUser = findViewById(R.id.rvListUser);
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        getUsersFromApi();
    }

    private void usersToRecyclerView(List<User> users) {
        UserAdapter adapter = new UserAdapter(MainActivity.this, users);
        rvListUser.setHasFixedSize(true);
        rvListUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvListUser.setAdapter(adapter);
    }

    private void getUsersFromApi() {
        UserService userService = ApiClient.getInstance().create(UserService.class);
        Call<ListUser> usersCall = userService.getList(1);
        usersCall.enqueue(new Callback<ListUser>() {
            @Override
            public void onResponse(Call<ListUser> call, Response<ListUser> response) {
                if (response.isSuccessful()) {
                    List<User> listUser = response.body().getData();
                    usersToRecyclerView(listUser);
                }
            }

            @Override
            public void onFailure(Call<ListUser> call, Throwable t) {
                Log.d("Retrofit Get", t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}