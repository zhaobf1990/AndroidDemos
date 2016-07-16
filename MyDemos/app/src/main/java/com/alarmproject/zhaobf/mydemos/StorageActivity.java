package com.alarmproject.zhaobf.mydemos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alarmproject.zhaobf.mydemos.Storage.SqliteActivity;

public class StorageActivity extends AppCompatActivity {
    String[] data = new String[]{"sqlite"};
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        listview = (ListView) findViewById(R.id.listview);

        ArrayAdapter arrayAdapter = new ArrayAdapter(StorageActivity.this, android.R.layout.simple_list_item_1, data);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(StorageActivity.this, SqliteActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
