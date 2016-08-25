package com.alarmproject.zhaobf.mydemos.other.FragmentTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alarmproject.zhaobf.mydemos.R;

public class Main2Activity extends AppCompatActivity {
    final static String TAG = Main2Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "-----Activity onCreate-----");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "-----Activity onStart-----");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "-----Activity onRestart-----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "-----Activity onResume-----");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "-----Activity onPause-----");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "-----Activity onStop-----");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "-----Activity onDestroy-----");
    }
}
