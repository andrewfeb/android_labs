package com.example.moviecatalog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>{

    private ArrayList<Movie> movies;
    public ListMovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
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

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView imgCover;
        TextView tvTitle, tvDescription, tvReleaseYear;
        private Context context;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.imgCover);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvReleaseYear = itemView.findViewById(R.id.tvReleaseYear);
            context = itemView.getContext();

            itemView.setOnCreateContextMenuListener(this);
            imgCover.setOnClickListener(this::onClick);
        }

        private void onClick(View view) {
            PopupMenu popup = new PopupMenu(context, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.popup_image, popup.getMenu());
            popup.setOnMenuItemClickListener(this::onMenuItemClick);
            popup.show();
        }

        private boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.popupShow:
                    showImage();
                    break;
                default:
                    break;
            }
            return true;
        }

        private void showImage()
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            ImageView img = new ImageView(context);
            img.setImageResource(DataMovie.getDataMovie(getLayoutPosition()).getCover());
            dialog.setTitle("Show Cover");
            dialog.setView(img);
            dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dialog.show();
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem detail = contextMenu.add(Menu.NONE, R.id.cmDetail, Menu.NONE, "Detail");
            detail.setOnMenuItemClickListener(detailMenu);
        }

        private MenuItem.OnMenuItemClickListener detailMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.cmDetail:
                        //Log.d("page", "Detail Page");
                        Intent detailPage = new Intent(context, DetailActivity.class);
                        detailPage.putExtra("position", getLayoutPosition());
                        context.startActivity(detailPage);
                        break;
                }
                return true;
            }
        };

    }
}
