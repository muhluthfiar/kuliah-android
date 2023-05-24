package com.example.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class PulseAnimationView extends View {

    private float radius;
    private final Paint paint = new Paint();
    private static final int COLOR_ADJUSTER = 5;
    private float x,y;
    private AnimatorSet pulseAnimationSet = new AnimatorSet();



    private static final int ANIMATION_DURATION = 4000;
    private static final long ANIMATION_DELAY = 1000;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this,"radius", 0, getWidth());
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this,"radius", getWidth(), 0);
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        ObjectAnimator repeatGrowAnimator = ObjectAnimator.ofFloat(this,"radius", 0, getWidth());
        repeatGrowAnimator.setDuration(ANIMATION_DURATION);
        repeatGrowAnimator.setInterpolator(new LinearInterpolator());
        repeatGrowAnimator.setRepeatCount(1);
        repeatGrowAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // wrap broth animations
        pulseAnimationSet.play(shrinkAnimator).before(repeatGrowAnimator).after(growAnimator);


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();

            if (pulseAnimationSet != null && pulseAnimationSet.isRunning()) {
                pulseAnimationSet.cancel();
            }


            pulseAnimationSet.start();

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x,y,radius,paint);
    }

    public void setRadius(float radius) {
        this.radius = radius;
        this.paint.setColor(Color.GREEN + (int) radius/COLOR_ADJUSTER);
        invalidate();
    }

    public PulseAnimationView(Context context) {
        super(context);
    }

    public PulseAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
