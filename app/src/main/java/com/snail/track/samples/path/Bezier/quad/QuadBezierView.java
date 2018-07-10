package com.snail.track.samples.path.Bezier.quad;

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

public class QuadBezierView extends View {


    private Paint mPaint;
    private PointF mStartPoint;
    private PointF mEndPoint;
    private PointF mControlPoint;
    private int mCenterX;
    private int mCenterY;
    private Path mPath;

    public QuadBezierView(Context context) {
        this(context, null);
    }

    public QuadBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        mPath = new Path();
        mStartPoint = new PointF(0, 0);
        mEndPoint = new PointF(0, 0);
        mControlPoint = new PointF(0, 0);
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

        mControlPoint.x = mCenterX;
        mControlPoint.y = mCenterY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mControlPoint.x = event.getX();
        mControlPoint.y = event.getY();
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
        canvas.drawPoint(mControlPoint.x, mControlPoint.y, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStartPoint.x, mStartPoint.y, mControlPoint.x, mControlPoint.y, mPaint);
        canvas.drawLine(mEndPoint.x, mEndPoint.y, mControlPoint.x, mControlPoint.y, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);
        mPath.reset();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);
        canvas.drawPath(mPath, mPaint);

    }
}
