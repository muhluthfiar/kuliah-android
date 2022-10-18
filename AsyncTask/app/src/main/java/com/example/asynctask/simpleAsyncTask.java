package com.example.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

public class simpleAsyncTask extends AsyncTask<Void, Void, String> {

    public TextView textView;

    public simpleAsyncTask(TextView tv){
        textView = tv;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();

        int n = r.nextInt(11);
        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;
        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    protected void onPostExecute(String result) {
        textView.setText(result);
    }
}
