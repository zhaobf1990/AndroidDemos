package com.alarmproject.zhaobf.mydemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.alarmproject.zhaobf.mydemos.R;
import com.alarmproject.zhaobf.mydemos.other.CustomerActivity;
import com.alarmproject.zhaobf.mydemos.other.DialogActivity;
import com.alarmproject.zhaobf.mydemos.other.FragmentTest.Main2Activity;
import com.alarmproject.zhaobf.mydemos.other.ProgressBarActivity;
import com.alarmproject.zhaobf.mydemos.other.RectActivity;
import com.alarmproject.zhaobf.mydemos.other.ScreenActivity;
import com.alarmproject.zhaobf.mydemos.other.SuccessActivity;
import com.alarmproject.zhaobf.mydemos.other.TestTextViewActivity;

public class OtherActivity extends AppCompatActivity {
    String[] data = new String[]{"横竖屏", "自定义控件", "TextView文字颜色", "进度条", "绘制正方形", "绘制一个勾", "Activity和Fragment的生命周期", "对话框"};
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        listview = (ListView) findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter(OtherActivity.this, android.R.layout.simple_list_item_1, data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(OtherActivity.this, ScreenActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(OtherActivity.this, CustomerActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(OtherActivity.this, TestTextViewActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(OtherActivity.this, ProgressBarActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(OtherActivity.this, RectActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(OtherActivity.this, SuccessActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(OtherActivity.this, Main2Activity.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(OtherActivity.this, DialogActivity.class);
                        startActivity(intent7);
                        break;
                }
            }
        });
    }

}
