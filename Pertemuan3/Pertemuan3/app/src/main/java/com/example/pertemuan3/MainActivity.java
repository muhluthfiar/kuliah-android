package com.example.pertemuan3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int count = 0;
    TextView showCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout);
        showCount = (TextView) findViewById(R.id.display_count);
    }


    public void countUp(View view) {
        count++;

        if (showCount != null) {
            showCount.setText(Integer.toString(count));
        }

    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this,"Hello Toast", Toast.LENGTH_LONG);
        toast.show();
    }
}
