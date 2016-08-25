package com.alarmproject.zhaobf.mydemos.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alarmproject.zhaobf.mydemos.R;
import com.alarmproject.zhaobf.mydemos.other.CustomerView.DrawHookView;

public class SuccessActivity extends AppCompatActivity {

    private Button reStart;
    private DrawHookView anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        reStart = (Button) findViewById(R.id.reStart);

        anim = (DrawHookView) findViewById(R.id.anim);
        anim.setOnFinish(new DrawHookView.OnFinishListener() {
            @Override
            public void onFinish() {
                Toast.makeText(SuccessActivity.this, "完成动画", Toast.LENGTH_SHORT).show();
            }
        });
        reStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.startAnim();
            }
        });
    }
}
