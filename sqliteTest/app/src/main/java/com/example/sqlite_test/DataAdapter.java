package com.example.sqlite_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DataAdapter extends ArrayAdapter<Data> {

    public DataAdapter(Context context, List<Data> datas) {
        super(context,0, datas);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Data data = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.data_cell, parent, false);
        }

        // https://www.youtube.com/watch?v=4k1ZMpO9Zn0 from 6:04
        TextView titleDisplay = convertView.findViewById(R.id.NamaTitle);

        titleDisplay.setText(data.getNama());

        return convertView;
    }
}
