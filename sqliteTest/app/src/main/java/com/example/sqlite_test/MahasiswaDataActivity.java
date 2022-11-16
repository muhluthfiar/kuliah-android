package com.example.sqlite_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MahasiswaDataActivity extends AppCompatActivity {

    private TextView namaDisplay, jenisKelaminDisplay, jurusanDisplay, alamatDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_data);

        namaDisplay = findViewById(R.id.nama_display);
        jenisKelaminDisplay = findViewById(R.id.jeniskelamin_display);
        jurusanDisplay = findViewById(R.id.jurusan_display);
        alamatDisplay = findViewById(R.id.alamat_display);

        Intent intent = getIntent();
        String namaMhs = intent.getStringExtra("nama_mhs");
        String jenisKelaminMhs = intent.getStringExtra("jeniskelamin_mhs");
        String jurusanMhs = intent.getStringExtra("jurusan_mhs");
        String alamatMhs = intent.getStringExtra("alamat_mhs");

        namaDisplay.setText(namaMhs);
        jenisKelaminDisplay.setText(jenisKelaminMhs);
        jurusanDisplay.setText(jurusanMhs);
        alamatDisplay.setText(alamatMhs);
    }
}