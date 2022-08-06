package com.example.mahasiswa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mahasiswa.model.Mahasiswa;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mahasiswaDB";
    private static final String TABLE_NAME = "mahasiswa";

    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_JURUSAN = "jurusan";
    private static final String COLUMN_GENDER = "gender";

    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB;
        createDB = "CREATE TABLE " + TABLE_NAME
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nama TEXT NOT NULL,"
                + "jurusan TEXT NOT NULL,"
                + "gender TEXT NOT NULL)";
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Mahasiswa> getAllMahasiswa() {
        ArrayList<Mahasiswa> mhsList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);


        if(cursor.moveToFirst()){
           while(cursor.moveToNext()) {
               int id = cursor.getInt(0);
               String nama = cursor.getString(1);
               String jurusan = cursor.getString(2);
               String gender = cursor.getString(3);
               Mahasiswa mhs = new Mahasiswa(id, nama, jurusan, gender);
               mhsList.add(mhs);
           }
        }
        cursor.close();

        return mhsList;
    }

    public Mahasiswa getMahasiswa(int idMahasiswa){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(idMahasiswa)});

        int id = cursor.getInt(0);
        String nama = cursor.getString(1);
        String jurusan = cursor.getString(2);
        String gender = cursor.getString(3);
        Mahasiswa mhs = new Mahasiswa(id, nama, jurusan, gender);

        cursor.close();

        return mhs;
    }

    public long addMahasiswa(Mahasiswa mahasiswa){
        long result;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_JURUSAN, mahasiswa.getJurusan());
        values.put(COLUMN_GENDER, mahasiswa.getGender());

        result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result;
    }

    public long updateMahasiswa(Mahasiswa mahasiswa) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_JURUSAN, mahasiswa.getJurusan());
        values.put(COLUMN_GENDER, mahasiswa.getGender());

        result = db.update(TABLE_NAME, values, "id=?", new String[] {String.valueOf(mahasiswa.getId())});
        db.close();

        return result;
    }

    public long delete(Mahasiswa mahasiswa) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        result = db.delete(TABLE_NAME, "id=?", new String[] {String.valueOf(mahasiswa.getId())});
        db.close();

        return result;
    }
}
