package com.example.pertemuan4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String mSpinnerLabel = "";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        //create the spinner
        Spinner spinner = (Spinner) findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array,
                android.R.layout.simple_spinner_item );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }
     //  for activity_main.xml
    public void showText(View view) {
        EditText editText = (EditText) findViewById(R.id.editText_main);

        if (editText != null) {
            String showstring = editText.getText().toString();
            Toast.makeText(this, showstring, Toast.LENGTH_LONG).show();
        }
    }

    // for activity_spinner.xml
    public void displayPhone(View view) {
        EditText editText = (EditText) findViewById(R.id.input_thing);
        TextView displayText = (TextView) findViewById(R.id.phone_display);

        if (editText != null) {
            String showstring =  (editText.getText().toString() + " - " + mSpinnerLabel);
            displayText.setText(showstring);
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinnerLabel = adapterView.getItemAtPosition(i).toString();
        showText(view);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d(TAG, "Nothing Happened In China in 1989");
    }

}