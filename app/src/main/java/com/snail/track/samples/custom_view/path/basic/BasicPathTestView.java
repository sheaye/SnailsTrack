package com.snail.track.samples.custom_view.path.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BasicPathTestView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Path mPath;

    public BasicPathTestView(Context context) {
        this(context, null);
    }

    public BasicPathTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPath = new Path();
    }

    public void testBasic() {
        mPath.reset();
        mPath.lineTo(200, 200);
        mPath.moveTo(200, 100); //
        mPath.setLastPoint(200, 100);
        mPath.lineTo(200, 0);
        mPath.close();
        mPath.addRect(-200, -200, 200, 200, Path.Direction.CW);
        mPath.setLastPoint(-100, 100);
        invalidate();

    }

    public void testAddPath() {
        Path src = new Path();
        mPath.reset();
        mPath.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);
        mPath.addPath(src, 0, 200);
        invalidate();
    }

    public void testAddArc() {
        mPath.reset();
        mPath.lineTo(100, 100);
        RectF rect = new RectF(0, 0, 100, 100);
        mPath.addArc(rect, 0, 120);
        invalidate();
    }

    public void testArcTo() {
        mPath.reset();
        mPath.lineTo(100, 100);
//        RectF rect = new RectF(0, 0, 100, 100);
        RectF rect = new RectF(0, 0, 100, 100);
        mPath.arcTo(rect, 0, 120, true);
        invalidate();
    }

    public void testRPath() {
        mPath.reset();
        mPath.moveTo(100, 100);
        mPath.rLineTo(100, 200);
        invalidate();
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
        canvas.drawLine(0, -mHeight / 2, 0, mHeight / 2, mPaint);
        canvas.drawLine(-mWidth / 2, 0, mWidth / 2, 0, mPaint);
//        canvas.scale(1, -1);// 坐标系Y轴翻转
        canvas.drawPath(mPath, mPaint);
    }
}
