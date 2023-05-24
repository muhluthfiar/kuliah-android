package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgView = findViewById(R.id.img);
        Button btn = findViewById(R.id.btn_start);

        // pos : x,y to change rotation use rotation on propertyname
        animator = ObjectAnimator.ofFloat(imgView, "x", 750);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animator.setDuration(1000);
                animator.start();
            }
        });
    }
}