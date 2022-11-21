package com.example.retrofit.adapters;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.AlertFragment;
import com.example.retrofit.EditActivity;
import com.example.retrofit.MainActivity;
import com.example.retrofit.R;
import com.example.retrofit.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements AlertFragment.OnAlertListener {
    private final List<User> dataUser;
    private final AppCompatActivity context;

    public UserAdapter(AppCompatActivity context, List<User> dataUser) {
        this.context = context;
        this.dataUser = dataUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = dataUser.get(position);
        holder.tvName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        holder.tvEmail.setText(user.getEmail());
        Glide.with(holder.itemView.getContext())
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
                DialogFragment alertFragment = new AlertFragment();
                alertFragment.show(context.getSupportFragmentManager(), "alert");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataUser.size();
    }

    @Override
    public void onPositiveButtonClicked() {
        Toast.makeText(context, "Delete Alert", Toast.LENGTH_SHORT).show();
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
