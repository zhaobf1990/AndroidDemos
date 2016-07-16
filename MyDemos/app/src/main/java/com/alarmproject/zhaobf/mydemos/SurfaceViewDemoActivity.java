package com.alarmproject.zhaobf.mydemos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class SurfaceViewDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(getApplicationContext())); // 别忘了开始的时候载入我们加工好的的SurfaceView
    }

    /*
         * 这个类用来当测试的物件，会沿着方形路线持续移动
         */
    class GameObject {
        private float x;
        private float y;
        private Bitmap img;
        private Paint paint;

        public GameObject() {
            this.img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            this.x = 100;
            this.y = 100;
            this.paint = new Paint();
        }

        // 在SurfaceView加锁同步后传给自己的Canvas上绘制自己
        public void drawSelf(Canvas canvas) {
            canvas.drawBitmap(img, x, y, paint);
        }

        // 获取物件下一次要绘制的位置(这里是沿着一个边长为400的正方形不断运动的)
        public void getNextPos() {
            if (y == 100 && x != 500)
                x += 5;
            else if (x == 500 && y != 500)
                y += 5;
            else if (y == 500 && x != 100)
                x -= 5;
            else if (x == 100 && y != 100)
                y -= 5;
        }
    }

    /*
         * 这个类就是加工了SurfaceView之后的类，所有要运动的物件都最终放在这里进行绘制
         */
    class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
        private Thread thread; // SurfaceView通常需要自己单独的线程来播放动画
        private Canvas canvas;
        private SurfaceHolder surfaceHolder;

        private GameObject obj;

        public MySurfaceView(Context c) {
            super(c);
            this.surfaceHolder = this.getHolder();
            this.surfaceHolder.addCallback(this);
            this.obj = new GameObject();
        }

        @Override
        public void run() {
            while (true) {
                obj.getNextPos();
                canvas = this.surfaceHolder.lockCanvas(); // 通过lockCanvas加锁并得到該SurfaceView的画布
                canvas.drawColor(Color.BLACK);
                obj.drawSelf(canvas); // 把SurfaceView的画布传给物件，物件会用这个画布将自己绘制到上面的某个位置
                this.surfaceHolder.unlockCanvasAndPost(canvas); // 释放锁并提交画布进行重绘
                try {
                    Thread.sleep(30); // 这个就相当于帧频了，数值越小画面就越流畅
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder arg0) {

            Toast.makeText(getApplicationContext(), "SurfaceView已经销毁", Toast.LENGTH_LONG).show();
        }

        @Override
        public void surfaceCreated(SurfaceHolder arg0) {
            Toast.makeText(getApplicationContext(), "SurfaceView已经创建", Toast.LENGTH_LONG).show();
            this.thread = new Thread(this);
            this.thread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
            // 这里是SurfaceView发生变化的时候触发的部分
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
