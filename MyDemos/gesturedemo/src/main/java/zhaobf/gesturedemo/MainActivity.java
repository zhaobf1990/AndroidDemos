package zhaobf.gesturedemo;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 手势按钮屏幕亮度,音量, 进度
 */
public class MainActivity extends AppCompatActivity implements OnGestureListener, View.OnTouchListener {
    public static final String SCREEN_BRIGHTNESS = "screenBrightness";
    boolean firstScroll = false; // 每次触摸屏幕后，第一次scroll的标志
    EnumGestureFlag GESTURE_FLAG = EnumGestureFlag.NULL;   //当前的操作对象     音量,进度 或者是屏幕亮度

    int currentProgress = 0;   //当前的进度值
    int progressMax = 100;   //进度条最大值
    int progressStep = 1;    //进度步长

    GestureDetector mGestureDetecotr;
    private TextView text;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetecotr = new GestureDetector(this, this);
        text = (TextView) findViewById(R.id.text);
        text.setLongClickable(true);
        text.setOnTouchListener(this);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


    }

    /**
     * This method is called after {@link #onStart} when the activity is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     * <p>
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(SCREEN_BRIGHTNESS)) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.screenBrightness = savedInstanceState.getFloat(SCREEN_BRIGHTNESS);
            getWindow().setAttributes(lp);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            GESTURE_FLAG = EnumGestureFlag.NULL;
            firstScroll = false;
        }
        return mGestureDetecotr.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        firstScroll = true;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    /**
     * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
     * current move {@link MotionEvent}. The distance in x and y is also supplied for
     * convenience.
     *
     * @param e1        The first down motion event that started the scrolling.
     * @param e2        The move motion event that triggered the current onScroll.
     * @param distanceX The distance along the X axis that has been scrolled since the last
     *                  call to onScroll. This is NOT the distance between {@code e1}
     *                  and {@code e2}.
     * @param distanceY The distance along the Y axis that has been scrolled since the last
     *                  call to onScroll. This is NOT the distance between {@code e1}
     *                  and {@code e2}.
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(MainActivity.class.getSimpleName(), "   e1.getRawX()=" + e1.getRawX());
        if (firstScroll == true) {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {    //横向拖动
                firstScroll = false;
                GESTURE_FLAG = EnumGestureFlag.GESTURE_MODIFY_PROGRESS;
            } else {   //竖向拖动
                firstScroll = false;
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                if (e1.getRawX() < (displayMetrics.widthPixels / 2)) {   //屏幕左侧拖动为音量
                    GESTURE_FLAG = EnumGestureFlag.GESTURE_MODIFY_VOLUME;
                } else {      //屏幕右侧拖动为屏幕亮度
                    GESTURE_FLAG = EnumGestureFlag.GESTURE_MODIFY_BRIGHT;
                }

            }
        } else {
            if (GESTURE_FLAG == EnumGestureFlag.GESTURE_MODIFY_PROGRESS) {
//                Log.d(MainActivity.class.getSimpleName(), EnumGestureFlag.GESTURE_MODIFY_PROGRESS.name() + "   distanceX=" + distanceX + "   distanceY=" + distanceY);
//                Log.d(MainActivity.class.getSimpleName(), EnumGestureFlag.GESTURE_MODIFY_PROGRESS.name() + "   distanceRawX=" + (e1.getRawX() - e2.getRawX()) + "   distanceRawY=" + (e1.getRawY() - e2.getRawX()));
                ChangeProgress(distanceX);
            } else if (GESTURE_FLAG == EnumGestureFlag.GESTURE_MODIFY_VOLUME) {
                ChangeVolume(distanceY);
            } else if (GESTURE_FLAG == EnumGestureFlag.GESTURE_MODIFY_BRIGHT) {   //屏幕亮度
                changeScreenBrightness(distanceY);
            }
        }
        return false;
    }

    /**
     * 改变进度条        就当是视频播放的进度
     *
     * @param distanceX
     */
    private void ChangeProgress(float distanceX) {
        if (distanceX > 0) {    //如果是向左滑动
            if (currentProgress > 0) {
                currentProgress = currentProgress - progressStep;
            }
        } else if (distanceX < 0) {    //如果是向右滑动

            if (currentProgress < progressMax) {
                currentProgress = currentProgress + progressStep;
            }
        }
        text.setText("进度:" + currentProgress);
    }

    /**
     * 改变音量
     *
     * @param distanceY
     */
    private void ChangeVolume(float distanceY) {
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //获取系统最大音量
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取系统当前音量
        if (distanceY > 0) {    //大于0表示向上
            if (currentVolume < maxVolume) {
                currentVolume++;
            }
        } else if (distanceY < 0) {   //小于0表示向下
            if (currentVolume > 0) {
                currentVolume--;
            }
        }
        text.setText("音量:" + currentVolume + "%");
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
    }

    /**
     * 改变屏幕亮度
     *
     * @param distanceY
     */
    private void changeScreenBrightness(float distanceY) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        if (lp.screenBrightness > 1.0f) {     //屏幕亮度的范围应该在0-1之间        如果大于1,则为1
            lp.screenBrightness = 1.0f;
        }
        if (lp.screenBrightness < 0.01f) {   //如果小于0.01    则为0.01;
            lp.screenBrightness = 0.01f;
        }
        if (distanceY > 0) {
            if (lp.screenBrightness + 0.01f < 1.0f) {
                lp.screenBrightness = lp.screenBrightness + 0.01f;
            }
        } else if (distanceY < 0) {
            if (lp.screenBrightness - 0.01f > 0) {
                lp.screenBrightness = lp.screenBrightness - 0.01f;
            }
        }
        text.setText("屏幕亮度:" + lp.screenBrightness + "%");
        getWindow().setAttributes(lp);
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    /**
     * Save all appropriate fragment state.
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(SCREEN_BRIGHTNESS, getWindow().getAttributes().screenBrightness);  //横竖屏切换时   保存屏幕亮度
    }
}
