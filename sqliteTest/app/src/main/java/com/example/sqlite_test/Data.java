package com.example.sqlite_test;

import java.util.ArrayList;
import java.util.Date;

public class Data {

    public static String DB_KEY = "datamahasiswa";
    public static ArrayList<Data> dataArrayList = new ArrayList<>();
    private int id;
    private String nama;
    private String jenisKelamin;
    private String jurusan;
    private String alamat;
    private Date deleted;

    public Data(int id, String nama, String jenisKelamin, String jurusan, String alamat, Date deleted) {
        this.id = id;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.jurusan = jurusan;
        this.alamat = alamat;
        this.deleted = deleted;
    }

    public Data(int id, String nama, String jenisKelamin, String jurusan, String alamat) {
        this.id = id;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.jurusan = jurusan;
        this.alamat = alamat;
        deleted = null;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getAlamat() {
        return alamat;
    }

    public Date getDeleted() {
        return deleted;
    }
}
