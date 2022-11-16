package com.example.sqlite_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView dataListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setDataAdapter();
        SetOnClickListener();
    }

    private void SetOnClickListener() {
        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Data selectedItem = (Data) dataListView.getItemAtPosition(position);
                Intent dataDetail = new Intent(getApplicationContext(), MahasiswaDataActivity.class);

                dataDetail.putExtra("nama_mhs", selectedItem.getNama());
                dataDetail.putExtra("jeniskelamin_mhs", selectedItem.getJenisKelamin());
                dataDetail.putExtra("jurusan_mhs", selectedItem.getJurusan());
                dataDetail.putExtra("alamat_mhs", selectedItem.getAlamat());

                startActivity(dataDetail);
            }
        });
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateData();

    }

    private void initWidgets() {
        dataListView = findViewById(R.id.DataListView);
    }

    private void setDataAdapter() {
        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), Data.dataArrayList);
        dataListView.setAdapter(dataAdapter);
    }


    public void newData(View view) {
        Intent newDataIntent = new Intent(this, DataDetailActivity.class);
        startActivity(newDataIntent);
    }

}