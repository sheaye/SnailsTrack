package com.snail.track.samples.custom_view.demos.pie_chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class PieChartView extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private int mWidth;
    private int mHeight;
    private List<PieData> mPieData;
    private int mStartAngle;
    private Paint mPaint;

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public void setPieData(List<PieData> pieData) {
        mPieData = pieData;
        if (mPieData != null && mPieData.size() > 0) {
            float valueSum = 0;
            for (int i = 0; i < pieData.size(); i++) {
                PieData data = pieData.get(i);
                valueSum += data.getValue();
                if (data.getColor() == -1) {
                    data.setColor(mColors[i % mColors.length]);
                }
            }
            for (int i = 0; i < pieData.size(); i++) {
                PieData data = pieData.get(i);
                data.setupPercentage(valueSum);
            }
        }
        invalidate();
    }

    public void setStartAngle(int startAngle) {
        mStartAngle = startAngle;
        invalidate();
    }

    // 确定View的大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPieData == null) {
            return;
        }
        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = 0.8f * Math.min(mWidth, mHeight) / 2;
        RectF rectF = new RectF(-r, -r, r, r);
        for (int i = 0; i < mPieData.size(); i++) {
            PieData data = mPieData.get(i);
            mPaint.setColor(data.getColor());
            canvas.drawArc(rectF, currentStartAngle, data.getAngle(), true, mPaint);
            currentStartAngle += data.getAngle();
        }

    }
}
