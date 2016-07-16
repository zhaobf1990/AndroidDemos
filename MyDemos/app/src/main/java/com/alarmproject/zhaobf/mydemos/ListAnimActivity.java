package com.alarmproject.zhaobf.mydemos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alarmproject.zhaobf.mydemos.Anim.Anim1Activity;
import com.alarmproject.zhaobf.mydemos.Anim.LensFocusActivity;
import com.alarmproject.zhaobf.mydemos.Anim.ToGoneActivity;

public class ListAnimActivity extends Activity {
    String[] data = new String[]{"surfureView1", "动画3", "rotate", "scale1", "translate", "镜头风格_由近至远", "渐变并隐藏"};

    private ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_anim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listview = (ListView) findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(ListAnimActivity.this, SurfaceViewDemoActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(ListAnimActivity.this, Anim1Activity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.a2, R.anim.a1);
                        break;
                    case 2:
                        Animation animation = AnimationUtils.loadAnimation(ListAnimActivity.this, R.anim.alpha_rotate);
                        listview.startAnimation(animation);
                        break;
                    case 3:
                        Animation animation2 = AnimationUtils.loadAnimation(ListAnimActivity.this, R.anim.zoom_enter);
                        listview.startAnimation(animation2);
                        break;
                    case 4:
                        Animation animation3 = AnimationUtils.loadAnimation(ListAnimActivity.this, R.anim.translate);
                        listview.startAnimation(animation3);
                        break;
                    case 5:
                        Intent intent = new Intent(ListAnimActivity.this, LensFocusActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        Intent intent2 = new Intent(ListAnimActivity.this, ToGoneActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }

}
