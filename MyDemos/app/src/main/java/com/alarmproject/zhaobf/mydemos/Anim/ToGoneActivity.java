package com.alarmproject.zhaobf.mydemos.Anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.alarmproject.zhaobf.mydemos.R;

/**
 * 渐变到消失
 */
public class ToGoneActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_gone);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(ToGoneActivity.this, R.anim.alpha_to_0);
                anim.setDuration(1000);
                button.startAnimation(anim);
                button.setVisibility(View.GONE);
            }
        });
    }
}
