package com.example.sqlite_test;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DataDetailActivity extends AppCompatActivity {

    private EditText namaEdit, jenisKelaminEdit, jurusanEdit, alamatEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail);
        initWidgets();
    }

    private void initWidgets() {
        namaEdit = findViewById(R.id.NamaField);
        jenisKelaminEdit = findViewById(R.id.JenisKelamin);
        jurusanEdit = findViewById(R.id.JurusanField);
        alamatEdit = findViewById(R.id.AlamatField);
    }

    public void saveData(View view) {

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);


        String nama = String.valueOf(namaEdit.getText());
        String jenisKelamin = String.valueOf(jenisKelaminEdit.getText());
        String jurusan = String.valueOf(jurusanEdit.getText());
        String alamat = String.valueOf(alamatEdit.getText());

        int id = Data.dataArrayList.size();
        Data newData = new Data(id, nama, jenisKelamin, jurusan, alamat);
        Data.dataArrayList.add(newData);

        sqLiteManager.addDataToDatabase(newData);
        finish();
    }
}