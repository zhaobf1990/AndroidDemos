package com.alarmproject.zhaobf.mydemos.async;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alarmproject.zhaobf.mydemos.R;

public class AsyncTaskTestActivity extends AppCompatActivity {
    private Context context;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        text = (TextView) findViewById(R.id.text);
        this.context = this;
        for (int i = 0; i <= 100000; i++) {
            MyAsyncTask task = new MyAsyncTask();
            task.execute(i, 0);
        }
    }

    //    三个参数
    //    Params: 这个泛型指定的是我们传递给异步任务执行时的参数的类型
    //    Progress: 这个泛型指定的是我们的异步任务在执行的时候将执行的进度返回给UI线程的参数的类型
    //    Result: 这个泛型指定的异步任务执行完后返回给UI线程的结果的类型
    private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        public MyAsyncTask() {
            super();
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer[] params) {

            Log.d(AsyncTaskTestActivity.class.getSimpleName(), "doInBackground" + params[0]);
            return params[0] + params[1] + "";
        }


        @Override
        protected void onPostExecute(String s) {
//            Toast.makeText(AsyncTaskTestActivity.this, s, Toast.LENGTH_SHORT).show();
            text.setText(s);
        }
    }

}
