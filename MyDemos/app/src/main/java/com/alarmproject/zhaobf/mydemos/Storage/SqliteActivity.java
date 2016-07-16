package com.alarmproject.zhaobf.mydemos.Storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alarmproject.zhaobf.mydemos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class SqliteActivity extends AppCompatActivity {
    private static String path = Environment.getExternalStorageDirectory().getPath() + "/MyDemos";
    private static String filename = "test.db";  //这个扩展名没什么关系,可以随便定义
    SQLiteDatabase sqLiteDatabase;
    private Button open;
    private Button add;
    private Button createTable;
    private Button query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        //如果路径不存在,则创建
        File file = new File(path);
        if (!file.exists()) {  //如果路径不存在 , 直接调用openOrCreateDatabase去创建数据库,会报can not open database
            file.mkdirs();
        }
        open = (Button) findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开数据库
                sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path + "/" + filename, null);
            }
        });
        createTable = (Button) findViewById(R.id.createTable);
        createTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    sqLiteDatabase.execSQL("create table user_inf(user_id integer primary key AUTOINCREMENT ," +
                    sqLiteDatabase.execSQL("create table user_inf(user_id integer primary key  ," +     //不加AUTOINCREMENT(自增)   在插入数据时,也会自增
                            " user_name varchar(255)," +
                            " user_pass varchar(255))");
                } catch (Exception ex) {
                    Toast.makeText(SqliteActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        add = (Button) findViewById(R.id.add);
        //        添加一行数据
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
//                values.put("user_id", 20);
                values.put("user_name", "赵佰磊");
                values.put("user_pass", "fww222");
                long rows = sqLiteDatabase.insert("user_inf", null, values);  //返回的rows是主键的值    -1表示插入数据失败
                Toast.makeText(SqliteActivity.this, rows + "", Toast.LENGTH_SHORT).show();
//                sqLiteDatabase.insert
            }
        });

        query = (Button) findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = sqLiteDatabase.rawQuery("select * from user_inf", null);
                JSONArray arr = new JSONArray();
                while (cursor.moveToNext() == true) {
                    JSONObject jsonObject = new JSONObject();
                    int userIdIndex = cursor.getColumnIndex("user_id");
                    int userId = cursor.getInt(userIdIndex);
                    int userNameIndex = cursor.getColumnIndex("user_name");
                    int user_name = cursor.getInt(userNameIndex);
                    int userPassIndex = cursor.getColumnIndex("user_pass");
                    int user_pass = cursor.getInt(userPassIndex);

                    try {
                        jsonObject.put("user_id", userId);
                        jsonObject.put("user_name", user_name);
                        jsonObject.put("user_pass", user_pass);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arr.put(jsonObject);
                }
                Toast.makeText(SqliteActivity.this, arr.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
