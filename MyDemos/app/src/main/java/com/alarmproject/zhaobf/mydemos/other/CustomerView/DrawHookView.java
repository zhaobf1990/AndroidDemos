package com.alarmproject.zhaobf.mydemos.other.CustomerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.alarmproject.zhaobf.mydemos.R;

/**
 * 先画圆   再画勾   动画
 * 作者：zhaobf on 2016-07-27 16:24
 * # 公司:杭州天谷信息科技有限公司
 */
public class DrawHookView extends View {
    //绘制圆弧的进度值
    private int progress = 0;
    //线1的x轴
    private int line1_x = 0;
    //线1的y轴
    private int line1_y = 0;
    //线2的x轴
    private int line2_x = 0;
    //线2的y轴
    private int line2_y = 0;
    private int step;

    OnFinishListener mListener;
    /**
     * 是否开始绘制
     */
    private boolean isStart;

    public DrawHookView(Context context) {
        super(context);
    }

    public DrawHookView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawHookView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //绘制

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isStart == true) {
            progress++;

            /**
             * 绘制圆弧
             */
            Paint paint = new Paint();
            //设置画笔颜色
            paint.setColor(getResources().getColor(R.color.colorAccent));
            //设置圆弧的宽度
            paint.setStrokeWidth(5);
            //设置圆弧为空心
            paint.setStyle(Paint.Style.STROKE);
            //消除锯齿
            paint.setAntiAlias(true);

            //获取圆心的x坐标
            int center = getWidth() / 2;
            int center1 = center - getWidth() / 5;
            //圆弧半径
            int radius = getWidth() / 2 - 5;
            step = radius / 15;
            //定义的圆弧的形状和大小的界限
            RectF rectF = new RectF(center - radius - 1, center - radius - 1, center + radius + 1, center + radius + 1);

            //根据进度画圆弧
            canvas.drawArc(rectF, 235, -360 * progress / 30, false, paint);

            /**
             * 绘制对勾
             */
            //先等圆弧画完，才话对勾
            if (progress >= 30) {
                if (line1_x < radius / 3) {
                    line1_x += step;
                    line1_y += step;
                }
                //画第一根线
                canvas.drawLine(center1, center, center1 + line1_x, center + line1_y, paint);

                if (Math.abs(line1_x - radius / 3) < step) {
                    line2_x = line1_x;
                    line2_y = line1_y;
                    line1_x += step;
                    line1_y += step;
                }
                if (line1_x >= radius / 3 && line2_x <= radius) {
                    line2_x += step;
                    line2_y -= step;
                }
                //画第二根线
                canvas.drawLine(center1 + line1_x - 1, center + line1_y, center1 + line2_x, center + line2_y, paint);
                Log.d("DrawHookView", "center=" + center + "    center1 + line1_x - 1=" + (center1 + line1_x - 1) + "   center + line1_y=" + (center + line1_y) + "    center1 + line2_x=" + (center1 + line2_x) + "    center + line2_y=" + (center + line2_y));
            }
            if (line2_x <= radius) {
                //每隔10毫秒界面刷新
                postInvalidateDelayed(10);
            } else {
                isStart = false;
                if (mListener != null) {
                    mListener.onFinish();
                }
            }
        }
    }

    public void setOnFinish(OnFinishListener listener) {
        this.mListener = listener;
    }


    public void startAnim() {
        isStart = true;
        progress = 0;
        //线1的x轴
        line1_x = 0;
        //线1的y轴
        line1_y = 0;
        //线2的x轴
        line2_x = 0;
        //线2的y轴
        line2_y = 0;
        postInvalidateDelayed(10);
    }

    public interface OnFinishListener {
        void onFinish();
    }
}
