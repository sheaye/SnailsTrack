package com.snail.track.samples.path.Bezier.heart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class HeartBezierView extends View {

    private Paint mPaint;
    private PointF mStartPoint;
    private PointF mEndPoint;
    private PointF mControlPoint1;
    private PointF mControlPoint2;
    private PointF mControlPoint3;
    private PointF mControlPoint4;
    private Path mPath;
    private int mWidth;
    private int mHeight;

    public HeartBezierView(Context context) {
        this(context, null);
    }

    public HeartBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(30);

        mPath = new Path();
        mStartPoint = new PointF(0, 0);
        mEndPoint = new PointF(0, 0);
        mControlPoint1 = new PointF(0, 0);
        mControlPoint2 = new PointF(0, 0);
        mControlPoint3 = new PointF(0, 0);
        mControlPoint4 = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int centerX = w / 2;
        int centerY = h / 2;
        mStartPoint.y = mControlPoint1.y = mControlPoint4.y = centerY - 200;
        mEndPoint.y = mControlPoint2.y = mControlPoint3.y = centerY + 200;
        mStartPoint.x = mEndPoint.x = centerX;
        mControlPoint1.x = mControlPoint2.x = centerX + 260;
        mControlPoint3.x = mControlPoint4.x = centerX - 260;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX = event.getX();
        float curY = event.getY();
        double d1 = Math.pow(curX - mControlPoint1.x, 2) + Math.pow(curY - mControlPoint1.y, 2);
        double d2 = Math.pow(curX - mControlPoint2.x, 2) + Math.pow(curY - mControlPoint2.y, 2);
        double d3 = Math.pow(curX - mControlPoint3.x, 2) + Math.pow(curY - mControlPoint3.y, 2);
        double d4 = Math.pow(curX - mControlPoint4.x, 2) + Math.pow(curY - mControlPoint4.y, 2);
        double min = Math.min(Math.min(d1, d2), Math.min(d3, d4));
        if (min == d1) {
            mControlPoint1.x = curX;
            mControlPoint1.y = curY;
        } else if (min == d2) {
            mControlPoint2.x = curX;
            mControlPoint2.y = curY;
        } else if (min == d3) {
            mControlPoint3.x = curX;
            mControlPoint3.y = curY;
        } else {
            mControlPoint4.x = curX;
            mControlPoint4.y = curY;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(mStartPoint.x, mStartPoint.y, mPaint);
        canvas.drawPoint(mEndPoint.x, mEndPoint.y, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawPoint(mControlPoint1.x, mControlPoint1.y, mPaint);
        canvas.drawPoint(mControlPoint2.x, mControlPoint2.y, mPaint);
        canvas.drawPoint(mControlPoint3.x, mControlPoint3.y, mPaint);
        canvas.drawPoint(mControlPoint4.x, mControlPoint4.y, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        canvas.drawText("1", mControlPoint1.x + 50, mControlPoint1.y + 50, mPaint);
        canvas.drawText("2", mControlPoint2.x + 50, mControlPoint2.y + 50, mPaint);
        canvas.drawText("3", mControlPoint3.x + 50, mControlPoint3.y + 50, mPaint);
        canvas.drawText("4", mControlPoint4.x + 50, mControlPoint4.y + 50, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStartPoint.x, mStartPoint.y, mControlPoint1.x, mControlPoint1.y, mPaint);
        canvas.drawLine(mEndPoint.x, mEndPoint.y, mControlPoint2.x, mControlPoint2.y, mPaint);
        canvas.drawLine(mEndPoint.x, mEndPoint.y, mControlPoint3.x, mControlPoint3.y, mPaint);
        canvas.drawLine(mStartPoint.x, mStartPoint.y, mControlPoint4.x, mControlPoint4.y, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);
        mPath.reset();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.cubicTo(mControlPoint1.x, mControlPoint1.y, mControlPoint2.x, mControlPoint2.y, mEndPoint.x, mEndPoint.y);
        mPath.cubicTo(mControlPoint3.x, mControlPoint3.y, mControlPoint4.x, mControlPoint4.y, mStartPoint.x, mStartPoint.y);
        canvas.drawPath(mPath, mPaint);
    }
}
