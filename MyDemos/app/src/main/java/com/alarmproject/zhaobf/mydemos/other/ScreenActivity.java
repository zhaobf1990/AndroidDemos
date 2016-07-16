package com.alarmproject.zhaobf.mydemos.other;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.TextView;

import com.alarmproject.zhaobf.mydemos.R;

public class ScreenActivity extends AppCompatActivity {

    private TextView orgin;
    private MyOrientationDetector orientationDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
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

        orientationDetector = new MyOrientationDetector(this);


        orgin = (TextView) findViewById(R.id.orgin);

        Log.d(this.getClass().getSimpleName() + ":onCreate", getRequestedOrientation() + "");


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(this.getClass().getSimpleName() + ":onConfigurationChanged", newConfig.orientation + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        orientationDetector.enable();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        orientationDetector.disable();
    }

    public class MyOrientationDetector extends OrientationEventListener {

        /**
         * Creates a new OrientationEventListener.
         *
         * @param context for the OrientationEventListener.
         */
        public MyOrientationDetector(Context context) {
            super(context);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if (orientation == ORIENTATION_UNKNOWN) {
                return;
            }
            orgin.setText("角度："+orientation + "");
            Log.d(ScreenActivity.class.getSimpleName(), "orientation=" + orientation);

        }
    }
}
