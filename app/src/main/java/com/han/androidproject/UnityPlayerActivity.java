package com.han.androidproject;

/**
 * 项目名称：AndroidProject
 * 创建人：BenC Zhang zhangzhihua@yy.com
 * 类描述：TODO(这里用一句话描述这个方法的作用)
 * 创建时间：2019/5/31 11:37
 *
 * @version V1.0
 */

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.unity3d.player.UnityPlayer;

public class UnityPlayerActivity extends Activity {
    static Activity mActivity;
    protected UnityPlayer mUnityPlayer;

    public static void SetLiveWallpaper() {
        ComponentName localComponentName = null;
        Intent localIntent2 = null;
        if (Build.VERSION.SDK_INT >= 16) {
            localComponentName = new ComponentName(mActivity, Unity3DFluidWallpaperService.class);
            localIntent2 = new Intent("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
        }
        try {
            localIntent2.putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT",
                    localComponentName);
            mActivity.startActivity(localIntent2);
            return;
        } catch (ActivityNotFoundException e1) {
            Log.d("UnityPlayerActivity", e1.toString());
            try {
                Intent localIntent1 = new Intent("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER");
                mActivity.startActivity(localIntent1);
                return;
            } catch (ActivityNotFoundException e2) {
                Log.d("UnityPlayerActivity", e2.toString());
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
        if (paramKeyEvent.getAction() == 2)
            return this.mUnityPlayer.injectEvent(paramKeyEvent);
        return super.dispatchKeyEvent(paramKeyEvent);
    }

    boolean isOrientationLocked() {
        ContentResolver localContentResolver = getContentResolver();
        if (Settings.System.getInt(localContentResolver, "accelerometer_rotation", 0) == 0) {
            return true;
        }
        return false;
    }

    public void onConfigurationChanged(Configuration paramConfiguration) {
        super.onConfigurationChanged(paramConfiguration);
        this.mUnityPlayer.configurationChanged(paramConfiguration);
    }

    protected void onCreate(Bundle paramBundle) {
        requestWindowFeature(1);
        super.onCreate(paramBundle);
        this.mUnityPlayer = new UnityPlayer(this);
        setContentView(R.layout.activity_main);
        ((ViewGroup) findViewById(R.id.content_rl)).addView(this.mUnityPlayer);
        findViewById(R.id.set_wallpaper).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetLiveWallpaper();
            }
        });
        this.mUnityPlayer.requestFocus();
        mActivity = this;
    }

    protected void onDestroy() {
        this.mUnityPlayer.quit();
        super.onDestroy();
    }

    public boolean onGenericMotionEvent(MotionEvent paramMotionEvent) {
        return this.mUnityPlayer.injectEvent(paramMotionEvent);
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        return this.mUnityPlayer.injectEvent(paramKeyEvent);
    }

    public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
        return this.mUnityPlayer.injectEvent(paramKeyEvent);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mUnityPlayer.lowMemory();
    }

    protected void onNewIntent(Intent paramIntent) {
        setIntent(paramIntent);
    }

    protected void onPause() {
        super.onPause();
        this.mUnityPlayer.pause();
    }

    protected void onResume() {
        super.onResume();
        this.mUnityPlayer.resume();
    }

    protected void onStart() {
        super.onStart();
        this.mUnityPlayer.resume();
    }

    protected void onStop() {
        super.onStop();
        this.mUnityPlayer.pause();
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        return this.mUnityPlayer.injectEvent(paramMotionEvent);
    }

    public void onTrimMemory(int paramInt) {
        super.onTrimMemory(paramInt);
        if (paramInt != 15)
            return;
        this.mUnityPlayer.lowMemory();
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        StringBuilder localObject = new StringBuilder();
        localObject.append("");
        localObject.append(isOrientationLocked());
        UnityPlayer.UnitySendMessage("AppController",
                "ReceiveOrientationLock",
                localObject.toString());
        this.mUnityPlayer.windowFocusChanged(paramBoolean);
    }
}