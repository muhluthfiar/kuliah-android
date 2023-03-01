package com.example.tugas1fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button button;
    private boolean hasButtonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button_fragment);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasButtonClicked) {
                    displayChange();
                } else {
                    revertChange();
                }

            }
        });
    }

    public void displayChange() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        if (fragmentManager.findFragmentByTag("contentFragment") == null) {
//            Content content = Content.newInstance();
//            fragmentTransaction.add(R.id.fragment, content, "contentFragment").addToBackStack(null).commit();
//
//        }



        TextView description = findViewById(R.id.description);
        description.setText("Honda PCX160 kini pakai mesin 156,9 cc 4 katup dengan teknologi anyar eSP+. Outputnya paling besar di antara para kompetitor yakni punya tenaga maksimal 15,8 daya kuda pada 8.500 rpm dan torsi puncak 14,7 Nm di 6.500 rpm.\n" +
                    "\n" +
                    "Berdasarkan pengetesan internal pabrikan lewat metode ECE R40 Euro 3 dengan pengaturan Idling Stop System aktif tercatat 45 kilometer per liter.\n" +
                    "\n" +
                    "Soal angkut barang bawaan Honda PCX160 punya kelebihan di sektor ini, bagasi utama dengan volume 30 liter mampu menampung 1 buah helm fullface.");


        hasButtonClicked = true;
        button.setText("Mantap!");

    }

    public void revertChange() {
        TextView description = findViewById(R.id.description);
        description.setText("Honda PCX160 tersedia dalam pilihan mesin Petrol di Indonesia Scooter baru dari Honda hadir dalam 4 varian. Bicara soal spesifikasi mesin Honda PCX160, ini ditenagai dua pilihan mesin Petrol berkapasitas 156.9 cc. PCX160 tersedia dengan transmisi CVT tergantung variannya. PCX160 adalah Scooter 2 seater dengan panjang 1936 mm, lebar 742 mm, wheelbase 1313 mm. serta ground clearance 135 mm.");

        hasButtonClicked = false;
        button.setText("Lihat Kelebihan");
    }
}