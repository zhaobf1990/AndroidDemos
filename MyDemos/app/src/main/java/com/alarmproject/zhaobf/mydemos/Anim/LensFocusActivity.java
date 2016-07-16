package com.alarmproject.zhaobf.mydemos.Anim;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.alarmproject.zhaobf.mydemos.R;

/**
 * 镜头由近至远
 */
public class LensFocusActivity extends Activity {

    Animation animScale, animOut, animIn;
    ImageView mIvImage;
    Button button;

    Drawable[] images = new Drawable[2];
    Integer index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens_focus);

        images[0] = getResources().getDrawable(R.drawable.image1);
        images[1] = getResources().getDrawable(R.drawable.bbb);

        mIvImage = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);
        animIn = AnimationUtils.loadAnimation(LensFocusActivity.this, R.anim.anim_in);
        animOut = AnimationUtils.loadAnimation(LensFocusActivity.this, R.anim.anim_out);
        animScale = AnimationUtils.loadAnimation(LensFocusActivity.this, R.anim.anim_scale);

        animIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(this.getClass().getSimpleName(), "animIn onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(this.getClass().getSimpleName(), "animIn onAnimationEnd");
                mIvImage.startAnimation(animScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(this.getClass().getSimpleName(), "animOut onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(this.getClass().getSimpleName(), "animOut onAnimationEnd");
                if (index < images.length - 1) {
                    index++;
                } else {
                    index = 0;
                }
//                mIvImage.setImageResource(images[index]);
                mIvImage.setImageDrawable(images[index]);
//                mIvImage.setScaleY(1);
//                mIvImage.setScaleX(1);
                mIvImage.startAnimation(animIn);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(this.getClass().getSimpleName(), "animScale onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(this.getClass().getSimpleName(), "animScale onAnimationEnd");
                mIvImage.startAnimation(animOut);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvImage.startAnimation(animScale);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIvImage.setImageDrawable(images[index]);
        mIvImage.startAnimation(animIn);

    }
}
