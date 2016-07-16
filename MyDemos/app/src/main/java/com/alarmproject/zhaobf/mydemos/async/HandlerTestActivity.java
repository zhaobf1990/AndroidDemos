package com.alarmproject.zhaobf.mydemos.async;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alarmproject.zhaobf.mydemos.R;

public class HandlerTestActivity extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName();
    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        Log.d(TAG + "onCreate", Thread.currentThread().getId() + "");
        HandlerThread handlerThread = new HandlerThread("新线程");
        handlerThread.start();
        MyHandler myHandler = new MyHandler(handlerThread.getLooper());
        myHandler.sendMessage(myHandler.obtainMessage());
    }

    public class MyHandler extends Handler {
        public MyHandler() {

        }

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG + "handleMessage", Thread.currentThread().getId() + "");
        }
    }
}
