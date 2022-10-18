package com.example.asynctaskinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variables for the search input field, and results TextViews.
    private EditText BookInput;
    private TextView TitleText;
    private TextView AuthorText;


    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all the view variables.
        BookInput = (EditText)findViewById(R.id.enterBookName);
        TitleText = (TextView)findViewById(R.id.titleView);
        AuthorText = (TextView)findViewById(R.id.dataView);
    }

    /**
     * Gets called when the user pushes the "Search Books" button
     *
     * @param view The view (Button) that was clicked.
     */
    public void searchBook(View view) {
        // Get the search string from the input field.
        String queryString = BookInput.getText().toString();

        // Hide the keyboard when the button is pushed.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the network is active and the search field is not empty, start a FetchBook AsyncTask.
        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            new FetchBook(TitleText, AuthorText, BookInput).execute(queryString);
        }
        // Otherwise update the view to tell the user there is no connection or no search term.
        else {
            if (queryString.length() == 0) {
                AuthorText.setText("");
                TitleText.setText(R.string.no_search_term);
            } else {
                AuthorText.setText("");
                TitleText.setText(R.string.no_network);
            }
        }
    }
}