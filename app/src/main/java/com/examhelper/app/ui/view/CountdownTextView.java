package com.examhelper.app.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;

import com.examhelper.app.constant.EventBusMessageConstant;
import com.examhelper.app.messageevent.ChangeTVEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/7/27.
 */

public class CountdownTextView extends android.support.v7.widget.AppCompatTextView {
    Handler handler = new Handler(Looper.getMainLooper());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
    Timer timer;
    TimerTask timerTask;
    int second;

    public CountdownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTime(String time) {
        setText(time);
        String[] data = time.split(":");
        second = Integer.parseInt(data[0]) * 60 + Integer.parseInt(data[1]);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                String currentTime = simpleDateFormat.format(new Date(second * 1000));
                ChangeTVEvent changeTVEvent = new ChangeTVEvent();
                if (Pattern.matches("^\\d2:\\d\\d$", currentTime)) {
                    changeTVEvent.setColor(Color.RED);
                }
                changeTVEvent.setContentText(currentTime);
                //再主线程改变倒计时时间和颜色
                EventBus.getDefault().post(changeTVEvent);
                if ((second--) == 0) {
                    timer.cancel();
                    // 倒时间到,统计分数
                    EventBus.getDefault().post(EventBusMessageConstant.COUNTING_SCORE);
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    public void stopTime() {
        Log.d("CountdownTextView", "stopTime");
        if (timerTask != null) {
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
