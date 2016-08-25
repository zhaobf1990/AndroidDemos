package com.alarmproject.zhaobf.mydemos.other.CustomerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义控件   跟随手指的小球
 * 作者：zhaobf on 2016-05-30 10:38
 * # 公司:杭州天谷信息科技有限公司
 */
public class DrawView extends View {
    private float currentX;
    private float currentY;
    Paint p = new Paint();

    public DrawView(Context context) {
        super(context);
    }


    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔颜色
        p.setColor(Color.RED);
        //绘制一个小圆
        canvas.drawCircle(currentX, currentY, 10f, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        currentY = event.getY();
        //通知当前组件重绘自己
        invalidate();
        return true;
    }
}
