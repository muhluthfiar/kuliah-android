package com.example.pertemuan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "Hello World" );

        final Button dirbutton = (Button) findViewById(R.id.redirect_button);

        dirbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirects to another interface
                Intent messageIntent = new Intent(MainActivity.this , ActivityDetail.class);
                messageIntent.putExtra("temp2", "next page");
                startActivity(messageIntent);
            }
        });
    }
}
