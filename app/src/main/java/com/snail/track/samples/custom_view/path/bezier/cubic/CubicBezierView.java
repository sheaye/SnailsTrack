package com.snail.track.samples.custom_view.path.bezier.cubic;

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

public class CubicBezierView extends View {

    private Paint mPaint;
    private Path mPath;
    private PointF mStartPoint;
    private PointF mEndPoint;
    private PointF mControlPoint1;
    private PointF mControlPoint2;
    private int mCenterX;
    private int mCenterY;

    public CubicBezierView(Context context) {
        this(context, null);
    }

    public CubicBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
        mStartPoint = new PointF(0, 0);
        mEndPoint = new PointF(0, 0);
        mControlPoint1 = new PointF(0, 0);
        mControlPoint2 = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;

        mStartPoint.x = mCenterX - 200;
        mStartPoint.y = mCenterY;

        mEndPoint.x = mCenterX + 200;
        mEndPoint.y = mCenterY;

        mControlPoint1.x = mCenterX;
        mControlPoint1.y = mCenterY - 100;

        mControlPoint2.x = mCenterX;
        mControlPoint2.y = mCenterY + 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX = event.getX();
        float curY = event.getY();
        double d1 = Math.pow(curX - mControlPoint1.x, 2) + Math.pow(curY - mControlPoint1.y, 2);
        double d2 = Math.pow(curX - mControlPoint2.x, 2) + Math.pow(curY - mControlPoint2.y, 2);
        if (d1 <= d2) {
            mControlPoint1.x = curX;
            mControlPoint1.y = curY;
        }else {
            mControlPoint2.x = curX;
            mControlPoint2.y = curY;
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
        canvas.drawPoint(mControlPoint1.x, mControlPoint1.y, mPaint);
        canvas.drawPoint(mControlPoint2.x, mControlPoint2.y, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStartPoint.x, mStartPoint.y, mControlPoint1.x, mControlPoint1.y, mPaint);
        canvas.drawLine(mEndPoint.x, mEndPoint.y, mControlPoint2.x, mControlPoint2.y, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);
        mPath.reset();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.cubicTo(mControlPoint1.x, mControlPoint1.y, mControlPoint2.x, mControlPoint2.y,mEndPoint.x, mEndPoint.y);
        canvas.drawPath(mPath, mPaint);
    }
}
