package com.example.recyclerview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerview.R;
import com.example.recyclerview.models.TraditionalHouse;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ListViewHolder> {
    private ArrayList<TraditionalHouse> listHouse;

    public HouseAdapter(ArrayList<TraditionalHouse> list) {
        this.listHouse = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        TraditionalHouse house = listHouse.get(position);
        //holder.imgHouse.setImageResource(house.getImage());
        Glide.with(holder.itemView.getContext())
                .load(house.getImage())
                .into(holder.imgHouse);
        holder.tvName.setText(house.getName());
        holder.tvProvince.setText(house.getProvince());
    }

    @Override
    public int getItemCount() {
        return listHouse.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgHouse;
        private TextView tvName, tvProvince;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHouse = itemView.findViewById(R.id.imgHouse);
            tvName = itemView.findViewById(R.id.tvName);
            tvProvince = itemView.findViewById(R.id.tvProvince);
        }
    }
}

