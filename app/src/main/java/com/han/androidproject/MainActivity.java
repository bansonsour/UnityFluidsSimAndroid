package com.han.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import  android.os.Handler;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.unity3d.player.UnityPlayerActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }


    //显示弹窗
    public void showToast(final String content, final int time) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast mToast = Toast.makeText(MainActivity.this, content, time);
                mToast.show();
            }
        });
    }

    //获取当前时间
    public String getNowTime() {
        long time = System.currentTimeMillis();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE).format(new Date(time));
    }

}
