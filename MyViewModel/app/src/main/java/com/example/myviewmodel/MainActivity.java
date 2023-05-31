package com.example.myviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private EditText editLength, editWidth, editHeight;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editHeight = findViewById(R.id.edit_height);
        editLength = findViewById(R.id.edit_length);
        editWidth = findViewById(R.id.edit_width);
        tvResult = findViewById(R.id.txt_result);
        btnCalculate = findViewById(R.id.btn_calculate);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        btnCalculate.setOnClickListener(view -> {
            String length = editLength.getText().toString();
            String width = editWidth.getText().toString();
            String height = editHeight.getText().toString();

            if (width.isEmpty()) {
                editWidth.setError("Please Enter Width");
                return;
            }
            if (height.isEmpty()) {
                editWidth.setError("Please Enter Height");
                return;
            }
            if (length.isEmpty()) {
                editWidth.setError("Please Enter Length");
                return;
            }

//            String result = String.valueOf(Integer.parseInt(width) * Integer.parseInt(height) * Integer.parseInt(length));
//
//            tvResult.setText(result);

            mainViewModel.calculate(width,height,length);


        });
        displayResult();
    }

    private void displayResult() {
        //tvResult.setText(String.valueOf(mainViewModel.result));
        mainViewModel.result.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer result) {
                tvResult.setText(String.valueOf(result));
            }
        });
    }
}