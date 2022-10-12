package com.example.pertemuan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ActivityDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        // get extra intent from previous window
        Intent transferredIntent = getIntent();

        String event = transferredIntent.getStringExtra("temp2");

        //make a toast in android (a ui component)
        Toast.makeText(ActivityDetail.this, event, Toast.LENGTH_LONG).show();

        Log.d("temp2", event);
    }
}
