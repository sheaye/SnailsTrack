package com.snail.track.samples.custom_view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {


    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private float mAxisXLength;
    private float mAxisYLength;
    private int mTranslateX;
    private int mTranslateY;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private int mAngle;
    private float mSkewX;
    private float mSkewY;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mAxisXLength = w * 0.8f;
        mAxisYLength = h * 0.8f;
    }

    public void translate(float ratioX, float ratioY) {
        mTranslateX = (int) (ratioX * (mAxisXLength / 2));
        mTranslateY = (int) (ratioY * (mAxisYLength / 2));
        invalidate();
    }

    public void scale(float scaleX, float scaleY) {
        mScaleX = scaleX;
        mScaleY = scaleY;
        invalidate();
    }

    public void rotate(int angle) {
        mAngle = angle;
        invalidate();
    }

    public void skew(float x, float y) {
        mSkewX = x;
        mSkewY = y;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        drawAxis(canvas);
        canvas.save();
        canvas.translate(mTranslateX, mTranslateY);
        canvas.scale(mScaleX, mScaleY);
        canvas.rotate(mAngle);
        canvas.skew(mSkewX, mSkewY);
        RectF rect = new RectF(0, -50, 50, 0);
        canvas.drawRect(rect, mPaint);
        canvas.restore();
    }

    /**
     * 画坐标轴
     */
    private void drawAxis(Canvas canvas) {
//      X轴线
        canvas.drawLine(-mAxisXLength / 2, 0, mAxisXLength / 2, 0, mPaint);
//      X轴箭头
        canvas.save();
        canvas.rotate(30, mAxisXLength / 2, 0);
        canvas.drawLine(mAxisXLength / 2 - 30, 0, mAxisXLength / 2, 0, mPaint);
        canvas.restore();
        canvas.save();
        canvas.rotate(-30, mAxisXLength / 2, 0);
        canvas.drawLine(mAxisXLength / 2 - 30, 0, mAxisXLength / 2, 0, mPaint);
        canvas.restore();

//      Y轴线
        canvas.drawLine(0, -mAxisYLength / 2, 0, mAxisYLength / 2, mPaint);
//      Y轴箭头
        canvas.save();
        canvas.rotate(30, 0, mAxisYLength / 2);
        canvas.drawLine(0, mAxisYLength / 2, 0, mAxisYLength / 2 - 30, mPaint);
        canvas.restore();
        canvas.save();
        canvas.rotate(-30, 0, mAxisYLength / 2);
        canvas.drawLine(0, mAxisYLength / 2, 0, mAxisYLength / 2 - 30, mPaint);
        canvas.restore();
    }
}
