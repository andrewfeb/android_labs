package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.retrofit.adapters.UserAdapter;
import com.example.retrofit.models.ListUser;
import com.example.retrofit.models.User;
import com.example.retrofit.services.ApiClient;
import com.example.retrofit.services.UserService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvListUser;
    private ArrayList<User> listUser = new ArrayList<>();

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

        if (isInternetConnection()) {
            getUsersFromApi();
            setTitle(getNetworkType());
            Toast.makeText(this, getNetworkType(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Your device does not internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInternetConnection() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    private String getNetworkType() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected()) return "-";

        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) return "Wi-Fi";

        String operator = "";
        if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

            switch (networkInfo.getSubtype()) {
                case (TelephonyManager.NETWORK_TYPE_GPRS | TelephonyManager.NETWORK_TYPE_GSM | TelephonyManager.NETWORK_TYPE_EDGE):
                    operator = "2G";
                    break;
                case (TelephonyManager.NETWORK_TYPE_HSDPA):
                    operator = "3G";
                    break;
                case (TelephonyManager.NETWORK_TYPE_LTE):
                    operator = "4G";
                    break;
                case (TelephonyManager.NETWORK_TYPE_NR):
                    operator = "5G";
                    break;
            }

        }
        return operator;
    }


    private void usersToRecyclerView() {
        UserAdapter adapter = new UserAdapter(MainActivity.this, listUser);
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
                    Toast.makeText(MainActivity.this, String.format("%s", response.body().getTotal()), Toast.LENGTH_SHORT).show();
                    listUser.addAll(response.body().getData());

                    usersToRecyclerView();
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