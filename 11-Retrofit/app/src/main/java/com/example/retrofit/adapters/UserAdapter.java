package com.example.retrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.AlertFragment;
import com.example.retrofit.EditActivity;
import com.example.retrofit.MainActivity;
import com.example.retrofit.R;
import com.example.retrofit.models.ListUser;
import com.example.retrofit.models.User;
import com.example.retrofit.services.ApiClient;
import com.example.retrofit.services.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements AlertFragment.AlertListener {
    private final ArrayList<User> dataUser;
    private final AppCompatActivity context;
    private User user;

    public UserAdapter(AppCompatActivity context, ArrayList<User> dataUser) {
        this.context = context;
        this.dataUser = dataUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user = dataUser.get(position);
        holder.tvName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        holder.tvEmail.setText(user.getEmail());
        Glide.with(context)
                .load(user.getAvatar())
                .into(holder.imgAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(view.getContext(), EditActivity.class);
                editIntent.putExtra("user", user);
                view.getContext().startActivity(editIntent);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertFragment alertFragment = new AlertFragment();
                alertFragment.setListener(UserAdapter.this);

                alertFragment.show(context.getSupportFragmentManager(), "alert");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataUser.size();
    }

    @Override
    public void clickAlertOk() {
        UserService userService = ApiClient.getInstance().create(UserService.class);
        Call<User> deleteUser = userService.deleteUser(user.getId());

        deleteUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Delete is failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgAvatar;
        private final TextView tvName;
        private final TextView tvEmail;
        private final Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
