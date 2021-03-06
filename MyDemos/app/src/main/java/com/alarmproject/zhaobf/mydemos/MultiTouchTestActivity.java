package com.alarmproject.zhaobf.mydemos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouchTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = new MultiTouchView(this);

        setContentView(view);
//        setContentView(R.layout.activity_multi_touch_test);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)0
//                        .setAction("Action", null).show();
//            }
//        });
    }
    class MultiTouchView extends View {
        private float x1;
        private float y1;
        private float x2;
        private float y2;
        public MultiTouchView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // TODO Auto-generated method stub
            float size = event.getSize();
            int szi = (int) size;
            int dxi = szi >> 12;
            int dyit = ((1 << 12) - 1);
            int dyi = szi & dyit;
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            float dx = metrics.widthPixels * dxi / (float) dyit;
            float dy = metrics.heightPixels * dyi / (float) dyit;
            x1 = event.getX();
            y1 = event.getY();
            x2 = x1 + dx;
            y2 = y1 + dy;
            invalidate();
            return true;
        }
        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            float r = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
                            * (y1 - y2)) / 2;
            r = 50 >= r ? 50 : r;
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawCircle(x1, y1, r, paint);
        }
    }
}
