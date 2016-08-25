package com.alarmproject.zhaobf.mydemos.other.CustomerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：zhaobf on 2016-07-26 13:52
 * # 公司:杭州天谷信息科技有限公司
 */
public class MyDrawView extends View {

    public MyDrawView(Context context) {
        super(context);
    }


    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // 设置画布的背景颜色
        canvas.drawColor(Color.WHITE);
        /**
         * 定义矩形为空心
         */
        // 定义画笔1
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(200);
        // 消除锯齿
        paint.setAntiAlias(true);
        // 设置画笔的颜色
        paint.setColor(Color.RED);
        // 设置paint的外框宽度
        paint.setStrokeWidth(2);

//        // 画一个圆
//        canvas.drawCircle(40, 30, 20, paint);
        // 画一个正放形
        canvas.drawRect(20, 20, 800, 500, paint);
//        // 画一个长方形
//        canvas.drawRect(20, 170, 90, 130, paint);
//        // 画一个椭圆
//        RectF re = new RectF(20, 230, 100, 190);
//        canvas.drawOval(re, paint);

//        /**
//         * 定义矩形为实心
//         */
//        paint.setStyle(Paint.Style.FILL);
//        // 定义画笔2
//        Paint paint2 = new Paint();
//        // 消除锯齿
//        paint2.setAntiAlias(true);
//        // 设置画笔的颜色
//        paint2.setColor(Color.BLUE);
//        // 画一个空心圆
//        canvas.drawCircle(150, 30, 20, paint2);
//        // 画一个正方形
//        canvas.drawRect(185, 70, 130, 120, paint2);
//        // 画一个长方形
//        canvas.drawRect(200, 130, 130, 180, paint2);
//        // 画一个椭圆形
//        RectF re2 = new RectF(200, 230, 130, 190);
//        canvas.drawOval(re2, paint2);
    }
}
