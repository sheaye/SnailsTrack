/*
 * Copyright (c) 2017. shixinzhang (shixinzhang2016@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snail.track.samples.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.snail.track.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.snail.track.samples.thread.DownloadThread.KEY_URL;

/**
 * Description:
 * <br> HandlerThread 示例程序
 * <p>
 * <br> Created by shixinzhang on 17/6/7.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <a  href="https://about.me/shixinzhang">About me</a>
 */

public class HandlerThreadActivity extends AppCompatActivity implements Handler.Callback {

    @BindView(R.id.tv_start_msg)
    TextView mTvStartMsg;
    @BindView(R.id.tv_finish_msg)
    TextView mTvFinishMsg;
    @BindView(R.id.btn_start_download)
    Button mBtnStartDownload;

    private Handler mUIHandler;
    private DownloadThread mDownloadThread;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread_test);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mUIHandler = new Handler(this);
        mDownloadThread = new DownloadThread("下载线程");
        mDownloadThread.setUIHandler(mUIHandler);

    }

    @OnClick(R.id.btn_start_download)
    public void startDownload() {
        mDownloadThread.start();
        mBtnStartDownload.setText("正在下载");
        mBtnStartDownload.setEnabled(false);
    }

    @Override
    public boolean handleMessage(final Message msg) {
        Timber.e(msg.toString());
        switch (msg.what) {
            case DownloadThread.TYPE_PREPARED:
                Timber.e("mWorkHandler已准备就绪...");
                Handler workerHandler = mDownloadThread.getWorkerHandler();
                if (workerHandler != null) {
                    Message message = workerHandler.obtainMessage();
                    message.obj = Arrays.asList("http://pan.baidu.com/s/1qYc3EDQ", "http://pan.baidu.com/s/1qYc3EDQ", "http://pan.baidu.com/s/1qYc3EDQ", "http://pan.baidu.com/s/1qYc3EDQ", "http://bbs.005.tv/thread-589833-1-1.html", "http://list.youku.com/show/id_zc51e1d547a5b11e2a19e.html?");
                    workerHandler.sendMessage(message);
                }
                /*测试Looper和Handler所在线程的关系：Handler工作线程取决于looper所在线程
                Handler testHandler = new Handler(mDownloadThread.getLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        Timber.e("TestHandler 运行在" + Thread.currentThread().getName());
                        return false;
                    }
                });
                testHandler.sendMessage(testHandler.obtainMessage());*/
                break;
            case DownloadThread.TYPE_FINISHED:
                mTvFinishMsg.setText(mTvFinishMsg.getText().toString() + "\n " + msg.obj);
                break;
            case DownloadThread.TYPE_START:
                mTvStartMsg.setText(mTvStartMsg.getText().toString() + "\n " + msg.obj);
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownloadThread.quitSafely();
    }
}
