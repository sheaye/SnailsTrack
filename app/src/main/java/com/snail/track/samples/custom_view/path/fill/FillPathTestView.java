package com.snail.track.samples.custom_view.path.fill;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class FillPathTestView extends View{
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Path mPath;

    public FillPathTestView(Context context) {
        this(context, null);
    }

    public FillPathTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPath = new Path();
    }

    public void testFillEvenOdd() {
        mPaint.setStyle(Paint.Style.FILL);
        mPath.reset();
        mPath.setFillType(Path.FillType.EVEN_ODD);
        mPath.addRect(-200, -200, 200, 200, Path.Direction.CW);
        invalidate();
    }

    public void testFillInverseEvenOdd() {
        mPaint.setStyle(Paint.Style.FILL);
        mPath.reset();
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        mPath.addRect(-200, -200, 200, 200, Path.Direction.CW);
        invalidate();
    }

    public void testFillWinding() {
        mPaint.setStyle(Paint.Style.FILL);
        mPath.reset();
        mPath.addRect(-200, -200, 200, 200, Path.Direction.CW);
        mPath.addRect(-400, -400, 400, 400, Path.Direction.CCW);
        mPath.setFillType(Path.FillType.WINDING);
        invalidate();
    }

    public void testFillWinding2() {
        mPaint.setStyle(Paint.Style.FILL);
        mPath.reset();
        mPath.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        mPath.addRect(-400, -400, 400, 400, Path.Direction.CCW);
        mPath.setFillType(Path.FillType.WINDING);
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
        canvas.drawPath(mPath, mPaint);
    }
}
