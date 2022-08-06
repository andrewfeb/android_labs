package com.example.moviecatalogdb;

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
import androidx.recyclerview.widget.RecyclerView;

import java.text.Normalizer;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvReleaseYear.setText(movie.getReleaseYear());
        holder.tvDescription.setText(movie.getDescription());
        holder.imgCover.setImageResource(movie.getCover());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCover;
        TextView tvTitle, tvDescription, tvReleaseYear;
        Button btnEdit, btnDelete;
        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCover = itemView.findViewById(R.id.imgCover);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvReleaseYear = itemView.findViewById(R.id.tvReleaseYear);
            context = itemView.getContext();

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            btnEdit.setOnClickListener(this::onClick);
            btnDelete.setOnClickListener(this::onClick);
        }

        private void onClick(View view) {
            switch (view.getId()){
                case R.id.btnEdit:
                    editData();
                    break;
                case R.id.btnDelete:
                    deleteData();
                    break;
                default:
                    break;
            }
        }

        private void editData() {
            Movie movie = movies.get(getLayoutPosition());
            Intent intent = new Intent(context, FormActivity.class);
            intent.putExtra("idMovie", movie.getId());
            context.startActivity(intent);
        }

        private void deleteData(){
            Movie movie = movies.get(getLayoutPosition());
            DBHelper db = new DBHelper(context);
            if (db.deleteMovie(movie)>0) {
                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
