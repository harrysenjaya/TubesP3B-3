package com.example.tubesp3b_3;

public class Manga {
    protected int id;
    protected String Nama;
    protected String gambar;

    public Manga(int id, String nama, String gambar) {
        this.id = id;
        Nama = nama;
        this.gambar = gambar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
