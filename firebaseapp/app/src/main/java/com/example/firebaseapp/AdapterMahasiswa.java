package com.example.firebaseapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.ViewHolder> {

    private List<ModelMahasiswa> listMahasiswa;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterMahasiswa(List<ModelMahasiswa> listMahasiswa, Activity activity) {
        this.listMahasiswa = listMahasiswa;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.mini_item, parent, false);
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelMahasiswa data = listMahasiswa.get(position);
        holder.tv_nama.setText("Nama : " + data.getNama());
        holder.tv_matkul.setText("Mata Kuliah : " + data.getMatkul());
    }

    @Override
    public int getItemCount() {
        return listMahasiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama, tv_matkul;
        CardView card_hasil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_matkul = itemView.findViewById(R.id.tv_matkul);
            card_hasil = itemView.findViewById(R.id.card_hasil);

        }
    }
}
