package com.example.moviecatalogdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movie_catalog";
    private static final String TABLE_NAME = "tbl_movie";

    // Nama kolom
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_RELEASE_YEAR = "release_year";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_COVER = "cover";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE " + TABLE_NAME
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT NOT NULL,"
                + COLUMN_RELEASE_YEAR + " TEXT NOT NULL,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_COVER + " INT)";

        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Get ALL Data
    public ArrayList<Movie> getAllMovie() {
        ArrayList<Movie> movieList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getInt(0));
                movie.setTitle(cursor.getString(1));
                movie.setReleaseYear(cursor.getString(2));
                movie.setDescription(cursor.getString(3));
                movie.setCover(cursor.getInt(4));

                movieList.add(movie);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return movieList;
    }

    // Get Data by id
    public Movie getMovie(int idMovie) {
        Movie movie = new Movie();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id="+ idMovie;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        movie.setId(cursor.getInt(0));
        movie.setTitle(cursor.getString(1));
        movie.setReleaseYear(cursor.getString(2));
        movie.setDescription(cursor.getString(3));
        movie.setCover(cursor.getInt(4));

        cursor.close();
        db.close();

        return movie;
    }

    // Insert Data
    public long insertMovie(Movie movie) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_RELEASE_YEAR, movie.getReleaseYear());
        values.put(COLUMN_DESCRIPTION, movie.getDescription());
        values.put(COLUMN_COVER, movie.getCover());

        result = db.insert(TABLE_NAME,null, values);
        db.close();

        return result;
    }

    // Update Data
    public int updateMovie(Movie movie) {
        int result;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_RELEASE_YEAR, movie.getReleaseYear());
        values.put(COLUMN_DESCRIPTION, movie.getDescription());
        values.put(COLUMN_COVER, movie.getCover());

        result = db.update(TABLE_NAME,values, "id=?", new String[] {String.valueOf(movie.getId())});
        db.close();

        return result;
    }

    // Delete Data
    public int deleteMovie(Movie movie){
        int result;
        SQLiteDatabase db = this.getWritableDatabase();
        result = db.delete(TABLE_NAME,"id=?", new String[] {String.valueOf(movie.getId())});

        db.close();

        return result;
    }
}
