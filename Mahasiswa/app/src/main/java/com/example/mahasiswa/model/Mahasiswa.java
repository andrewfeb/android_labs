package com.example.mahasiswa.model;

public class Mahasiswa {
    private int id;
    private String nama, jurusan, gender;

    public Mahasiswa(int id, String nama, String jurusan, String gender)
    {
        this.id = id;
        this.nama = nama;
        this.jurusan = jurusan;
        this.gender = gender;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
