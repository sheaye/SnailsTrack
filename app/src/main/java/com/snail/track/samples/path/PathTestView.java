package com.snail.track.samples.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathTestView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Path mPath;

    public PathTestView(Context context) {
        this(context, null);
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPath = new Path();
//        testBasic();
//        testAddPath();
//        testAddArc();
        testArcTo();
    }

    private void testBasic() {

//        mPath.lineTo(200, 200);
//        mPath.moveTo(200,100); //
//        mPath.setLastPoint(200,100);
//        mPath.lineTo(200, 0);
//        mPath.close();
//        mPath.addRect(-200,-200,200,200,Path.Direction.CW);
//        mPath.setLastPoint(-100,100);

    }

    private void testAddPath() {
        Path src = new Path();
        mPath.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);
        mPath.addPath(src, 0, 200);
    }

    private void testAddArc() {
        mPath.lineTo(100, 100);
        RectF rect = new RectF(0, 0, 100, 100);
        mPath.addArc(rect, 0, 120);
    }

    private void testArcTo() {
        mPath.lineTo(100, 100);
//        RectF rect = new RectF(0, 0, 100, 100);
        RectF rect = new RectF(0, 0, 100, 100);
        mPath.arcTo(rect, 0, 120, true);
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
