package com.example.tampilangambar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class radiobutton extends AppCompatActivity {

    private static final String TAG_ACTIVITY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);

        Intent intent = getIntent();
        String ordertype = intent.getStringExtra("ordertype");

        TextView order = (TextView) findViewById(R.id.ordername);
        order.setText("Your Dessert : " + ordertype);
    }


    public void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view) {
// Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
// Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_sameday:
                if (checked)
// Same day service
                    displayToast("Chosen : Same Day Delivery");
                break;
            case R.id.radio_nextday:
                if (checked)
// Next day delivery
                    displayToast("Chosen : Next Day Delivery");
                break;
            case R.id.radio_pickup:
                if (checked)
// Pick up
                    displayToast("Chosen : Pick Up");
                break;
            default:

                break;
        }
    }
}