package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerview.adapters.HouseAdapter;
import com.example.recyclerview.data.HousesData;
import com.example.recyclerview.models.TraditionalHouse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHouses;
    private ArrayList<TraditionalHouse> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvHouses = findViewById(R.id.rvHouse);
        rvHouses.setHasFixedSize(true);

        list.addAll(HousesData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvHouses.setLayoutManager(new LinearLayoutManager(this));
        HouseAdapter houseAdapter = new HouseAdapter(list);
        rvHouses.setAdapter(houseAdapter);
    }
}