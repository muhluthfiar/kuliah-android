package com.example.pertemuan4_alert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alert(View view) {
        AlertDialog.Builder someAlert = new AlertDialog.Builder(MainActivity.this);

        someAlert.setTitle("Alert");
        someAlert.setMessage("Nothing Significant Happened In China in 1989");

        someAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User clicked OK button.
                Toast.makeText(getApplicationContext(), "Pressed OK",
                        Toast.LENGTH_SHORT).show();
            }
        });

        someAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    // User cancelled the dialog.
                        Toast.makeText(getApplicationContext(), "Pressed Cancel",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        someAlert.show();
    }
}