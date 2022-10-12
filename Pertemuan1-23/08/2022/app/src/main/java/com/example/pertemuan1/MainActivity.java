package com.example.pertemuan1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Double;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // code when the app starts
        super.onCreate(savedInstanceState);
        // load the current interface at activity_main.xml
//        setContentView(R.layout.activity_main);

        //R.id is used to find the id of object in the xml interface
//        final TextView title = (TextView) findViewById(R.id.titleView);
//        title.setText("Selamat siang!!");

//        setContentView(R.layout.percobaan);
//        final EditText nameInput = (EditText) findViewById(R.id.inputText);
//        final Button displayBtn = (Button) findViewById(R.id.displayBtn);
//        final TextView displayName = (TextView) findViewById(R.id.displayText);
//
//        displayBtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                 displayName.setText("Hello! "+ nameInput.getText());
//
//            }
//        });

        setContentView(R.layout.simple_calc);

        final EditText inputFirst = (EditText) findViewById(R.id.firstNumber);
        final EditText inputSecond = (EditText) findViewById(R.id.secondNumber);
        final Button calcBtn = (Button) findViewById(R.id.calculateBtn);
        final TextView result = (TextView) findViewById(R.id.displayText);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 double firstNumber = Double.parseDouble(inputFirst.getText().toString());
                 double secondNumber = Double.parseDouble(inputSecond.getText().toString());

                 result.setText("Hasil : " + (firstNumber + secondNumber));

            }
        });


    }
}
