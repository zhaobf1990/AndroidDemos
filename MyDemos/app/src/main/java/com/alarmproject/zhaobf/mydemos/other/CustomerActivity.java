package com.alarmproject.zhaobf.mydemos.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.alarmproject.zhaobf.mydemos.R;

/**
 * 自定义控件
 */
public class CustomerActivity extends AppCompatActivity {

    private LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

//        content  = (LinearLayout) findViewById(R.id.content);
//
//        DrawView child=new DrawView();
//
//        content.addView(child);
    }
}
