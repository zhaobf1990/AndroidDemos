package com.alarmproject.zhaobf.mydemos.async;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alarmproject.zhaobf.mydemos.R;

public class Handler2Activity extends AppCompatActivity {
   private   final  String TAG=Handler2Activity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler2);

        MyHandler myHandler = new MyHandler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.i(TAG,"Handler.Callback.handleMessage");
                return false;
            }
        });
        myHandler.sendMessage(myHandler.obtainMessage());
    }


    public class MyHandler extends Handler {

        public MyHandler(Callback callback) {
            super(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG,"MyHandler.handleMessage");
        }

    }
}
