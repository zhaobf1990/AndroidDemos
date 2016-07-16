package com.alarmproject.zhaobf.mydemos.async;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alarmproject.zhaobf.mydemos.R;

public class AsyncTaskTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        for (int i = 0; i <= 1000000; i++) {
            MyAsyncTask task = new MyAsyncTask();
            task.execute(i, 0);
        }
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        public MyAsyncTask() {
            super();
        }

        @Override
        protected String doInBackground(Integer[] params) {
            Log.d(AsyncTaskTestActivity.class.getSimpleName(),"doInBackground"+ params[0]);
            return params[0] + params[1] + "";
        }


        @Override
        protected void onPostExecute(String s) {
           // Toast.makeText(AsyncTaskTestActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

}
