package com.snail.track.samples.custom_view.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.snail.track.R;

public class CheckView extends View implements View.OnClickListener {

    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Bitmap mBitmap;
    private int mBitmapSize;
    private int currentPage;
    private Handler mHandler;
    private int mMaxPages;
    private int mAnimDuration = 50;

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.checkmark);
        mBitmapSize = mBitmap.getHeight();
        mMaxPages = mBitmap.getWidth() / mBitmapSize;
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (++currentPage > mMaxPages - 1) {
                    currentPage = 0;
                    return;
                }
                invalidate();
                this.sendEmptyMessageDelayed(0, mAnimDuration);
            }
        };
        mHandler.sendEmptyMessageDelayed(0, mAnimDuration);
        setOnClickListener(this);
    }

    public void setAnimDuration(int animDuration) {
        mAnimDuration = animDuration;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 240, mPaint);
        Rect src = new Rect(mBitmapSize * currentPage, 0, mBitmapSize * (currentPage + 1), mBitmapSize);
        Rect dst = new Rect(-200, -200, 200, 200);
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBitmap.recycle();
    }

    @Override
    public void onClick(View v) {
        invalidate();
        mHandler.sendEmptyMessageDelayed(0,50);
    }
}
