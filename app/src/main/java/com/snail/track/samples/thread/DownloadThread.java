package com.snail.track.samples.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

/**
 * Description:
 * <br> 使用 HandlerThread 实现下载
 * <p>
 * <br> Created by shixinzhang on 17/6/7.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <a  href="https://about.me/shixinzhang">About me</a>
 */

public class DownloadThread extends HandlerThread implements Handler.Callback {

    private final String TAG = this.getClass().getSimpleName();
    public static final  String KEY_URL = "url";
    public static final int TYPE_PREPARED = 0;
    public static final int TYPE_START = 1;
    public static final int TYPE_FINISHED = 2;

    private Handler mWorkerHandler;
    private Handler mUIHandler;

    public DownloadThread(final String name) {
        super(name);
    }

    // 该方法在子线程中执行
    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mWorkerHandler = new Handler(getLooper(), this);
        if (mUIHandler == null) {
            throw new IllegalArgumentException("Not set UIHandler!");
        }
        Timber.e("当前线程：%s", Thread.currentThread().getName());
        Message message = mUIHandler.obtainMessage(TYPE_PREPARED);
        mUIHandler.sendMessage(message);
    }

    public Handler getWorkerHandler() {
        return mWorkerHandler;
    }

    public DownloadThread setUIHandler(final Handler UIHandler) {
        mUIHandler = UIHandler;
        return this;
    }

    /**
     * 子线程中执行任务，完成后发送消息到主线程
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(final Message msg) {
        Timber.e(Thread.currentThread().getName());
        if (msg == null || msg.obj == null) {
            return false;
        }
        List<String> urlList = (List<String>) msg.obj;
        for (String url : urlList) {
            //下载开始，通知主线程
            Message startMsg = mUIHandler.obtainMessage(TYPE_START, "\n 开始下载 @" + new SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(new Date()) + "\n" + url);
            mUIHandler.sendMessage(startMsg);

            SystemClock.sleep(2000);    //模拟下载

            //下载完成，通知主线程
            Message finishMsg = mUIHandler.obtainMessage(TYPE_FINISHED, "\n 下载完成 @" + new SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(new Date()) + "\n" + url);
            mUIHandler.sendMessage(finishMsg);
        }
        return true;
    }

    @Override
    public boolean quitSafely() {
        mUIHandler = null;
        return super.quitSafely();
    }
}
