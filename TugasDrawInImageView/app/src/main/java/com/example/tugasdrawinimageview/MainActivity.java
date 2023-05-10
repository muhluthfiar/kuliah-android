package com.example.tugasdrawinimageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Canvas canvas;
    private Paint paint = new Paint(); // buat bikin objek
    private Paint paintText = new Paint(Paint.UNDERLINE_TEXT_FLAG); // buat bikin teks
    private Bitmap bitmap;
    private ImageView imageView;


    private int colorHead;
    private int colorFace;
    private int colorAccent;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorHead = ResourcesCompat.getColor(getResources(), R.color.yellow_base, null);
        colorFace = ResourcesCompat.getColor(getResources(), R.color.mouth, null);
        colorAccent = Color.BLACK;

        imageView = findViewById(R.id.myimageview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw(count, view);
                count += 1;

            }
        });

    }

    private void draw(int counter, View view) {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight /2;
        int radius = Math.min(halfHeight, halfWidth);



        Log.d("aa", String.valueOf(count));


        if (counter == 0) {
            bitmap = Bitmap.createBitmap(vWidth,vHeight,Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);

            // draw head
            paint.setColor(colorHead);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawCircle(halfWidth, halfHeight, radius - 100, paint);
            imageView.setImageBitmap(bitmap);

        }
        else if (counter == 1) {
            // draw left eye
            paint.setColor(colorFace);
            paint.setStyle(Paint.Style.FILL);

            RectF lefteye = new RectF(
                    halfWidth - radius / 3 - 75,
                    halfHeight - radius / 3 - 90,
                    halfWidth - radius / 3 + 25,
                    halfHeight - radius / 3 + 90
            );

            canvas.drawOval(lefteye, paint);
            imageView.setImageBitmap(bitmap);

        }
        else if (counter == 2){
            //draw right eye
            paint.setColor(colorFace);
            paint.setStyle(Paint.Style.FILL);

            RectF righteye = new RectF(
                    halfWidth + radius / 3 - 75,
                    halfHeight - radius / 3 - 90,
                    halfWidth + radius / 3 + 25,
                    halfHeight - radius / 3 + 90
            );

            canvas.drawOval(righteye, paint);
            imageView.setImageBitmap(bitmap);

        }
        else if (counter == 3) {
            // draw mouth
            paint.setColor(colorFace);
            paint.setStyle(Paint.Style.FILL);

            RectF mouth = new RectF(
                    halfWidth - 120,
                    halfHeight + 50,
                    halfWidth + 80,
                    halfHeight + 300
            );
            canvas.drawOval(mouth, paint);
            imageView.setImageBitmap(bitmap);

        }
        else if (counter == 4){
            // add text
            String text = "Penasaran";
            paintText.setColor(colorAccent);
            paintText.setTextSize(120);

            canvas.drawText(text, halfWidth - 300, halfHeight + 600, paintText);

        }
        view.invalidate();
    }
}