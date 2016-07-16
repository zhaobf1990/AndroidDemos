package com.alarmproject.zhaobf.mydemos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alarmproject.zhaobf.mydemos.async.AsyncTaskTestActivity;
import com.alarmproject.zhaobf.mydemos.async.HandlerTestActivity;

public class AsyncActivity extends AppCompatActivity {

    String[] data = new String[]{"Handler", "AsyncTask"};
    private ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        listview = (ListView) findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter(AsyncActivity.this, android.R.layout.simple_list_item_1, data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(AsyncActivity.this, HandlerTestActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(AsyncActivity.this, AsyncTaskTestActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }
}
