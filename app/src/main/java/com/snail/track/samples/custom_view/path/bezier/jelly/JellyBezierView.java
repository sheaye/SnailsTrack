package com.snail.track.samples.custom_view.path.bezier.jelly;

import android.animation.ValueAnimator;
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


public class JellyBezierView extends View {


    private Paint mPaint;
    private Path mPath;
    private int mCenterX;
    private int mCenterY;
    private int mRadius = 100;

    // 三阶贝塞尔曲线画圆的控制点位置相对半径的比例
    private float factor = 0.552284749831f;
    // 4个路径点
    private PointF p1;
    private PointF p2;
    private PointF p3;
    private PointF p4;
    // 8个控制点
    private PointF c1;
    private PointF c2;
    private PointF c3;
    private PointF c4;
    private PointF c5;
    private PointF c6;
    private PointF c7;
    private PointF c8;

    private float mLastX;
    private float mLastY;
    private int mDegree;
    private float mDownY;
    private float mDownX;

    // 开始触摸的点必须在圆圈区域内
    private boolean mMoveAble = false;

    public JellyBezierView(Context context) {
        this(context, null);
    }

    public JellyBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLUE);

        mPath = new Path();
        p1 = new PointF();
        p2 = new PointF();
        p3 = new PointF();
        p4 = new PointF();

        c1 = new PointF();
        c2 = new PointF();
        c3 = new PointF();
        c4 = new PointF();
        c5 = new PointF();
        c6 = new PointF();
        c7 = new PointF();
        c8 = new PointF();

        p1.y = -mRadius;
        p2.x = mRadius;
        p3.y = mRadius;
        p4.x = -mRadius;
        c1.x = c4.x = mRadius * factor;
        c8.x = c5.x = -mRadius * factor;
        c2.y = c7.y = -mRadius * factor;
        c3.y = c6.y = mRadius * factor;
        updateControlPoints();
    }

    private void updateControlPoints() {
        c1.y = c8.y = p1.y;
        c2.x = c3.x = p2.x;
        c4.y = c5.y = p3.y;
        c6.x = c7.x = p4.x;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX = event.getX();
        float curY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (Math.abs(curX - mCenterX) > mRadius || Math.abs(curY - mCenterY) > mRadius) {
                    mMoveAble = false;
                    return false;
                }
                mMoveAble = true;
                mDownX = curX;
                mDownY = curY;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mMoveAble) {
                    return false;
                }
                float dx = curX - mLastX;
                float dy = curY - mLastY;
                mDegree = (int) Math.toDegrees(Math.atan((curY - mDownY) / (curX - mDownX)));
                double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                if (curX - mDownX > 0) {
                    p2.x += dist;
                } else {
                    p4.x -= dist;
                }
                updateControlPoints();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                rebound(p2, mRadius);
                rebound(p4, -mRadius);
                break;
        }
        mLastX = curX;
        mLastY = curY;
        return true;
    }

    private void rebound(final PointF point, int target) {
        ValueAnimator animator = ValueAnimator.ofFloat(point.x, target);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                point.x = (float) animation.getAnimatedValue();
                updateControlPoints();
                invalidate();
            }
        });
        animator.setDuration(1000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mCenterY);
        canvas.rotate(mDegree);
        mPath.reset();
        drawFrame1(canvas);
    }

    /**
     * 画第一帧：圆
     */
    private void drawFrame1(Canvas canvas) {
        mPath.moveTo(p1.x, p1.y);
        mPath.cubicTo(c1.x, c1.y, c2.x, c2.y, p2.x, p2.y);
        mPath.cubicTo(c3.x, c3.y, c4.x, c4.y, p3.x, p3.y);
        mPath.cubicTo(c5.x, c5.y, c6.x, c6.y, p4.x, p4.y);
        mPath.cubicTo(c7.x, c7.y, c8.x, c8.y, p1.x, p1.y);
        canvas.drawPath(mPath, mPaint);
    }
}
