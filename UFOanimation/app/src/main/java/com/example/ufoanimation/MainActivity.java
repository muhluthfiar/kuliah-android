package com.example.ufoanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ObjectAnimator animator;
    ObjectAnimator down;
    private final int MAX_HEIGHT = 1250;
    private int currentHeight = 0;
    private int jumpHeight = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgView = findViewById(R.id.image_ufo);
        Button btn = findViewById(R.id.button);


        currentHeight = MAX_HEIGHT;

        // multithreading
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Call your function here
                LowerUFO(imgView);

                // Call the same runnable again after X milliseconds
                handler.postDelayed(this, 1000);
            }
        };

        // Start the initial call with a delay of X milliseconds
        handler.postDelayed(runnable, 1000);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int tmp = currentHeight;
                currentHeight -= jumpHeight;

                if (currentHeight < 0) {
                    currentHeight = 0;
                }

                // pos : x,y to change rotation use rotation on propertyname
                animator = ObjectAnimator.ofFloat(imgView, "y", tmp, currentHeight);

                animator.setDuration(400);
                animator.start();
            }
        });
    }

    private void LowerUFO(ImageView imgView) {

        int tmp = currentHeight;
        currentHeight += 50;

        if (currentHeight > MAX_HEIGHT) {
            currentHeight = MAX_HEIGHT;
        }

        down = ObjectAnimator.ofFloat(imgView, "y", tmp , currentHeight);

        down.setDuration(400);
        down.start();
    }


}

