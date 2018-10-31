package com.snail.track.samples.custom_view.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.snail.track.R;

import timber.log.Timber;

public class LoadingView extends View {

    public static final String TAG = "LoadingView";

    private Paint mPaint;
    // LoadingView的宽度
    private int mTotalWidth;
    // LoadingView的高度
    private int mTotalHeight;


    // 绘制静态背景的Picture
    private Picture mBackgroundPicture;
    // 当前进度
    private float mProgress;
    // 风扇图
    private Bitmap mFanBitmap;
    // 风扇图的显示范围
    private Rect mFanSrcRect;
    // 风扇图在画布上的显示范围
    private Rect mFabDstRect;
    private Matrix mLeafMatrix;
    // 内环的半径
    private int mInnerRadius;
    // 外环的半径
    private int mOuterRadius;
    // 内环与外环的间距
    private int mGap = 15;
    // 图形区域距离View边缘的水平距离
    private int mHorPadding = 20;
    // 左环的圆心在View坐标系中的位置
    private Point mLeftCenter;
    // 右环的圆心在View坐标系中的位置
    private Point mRightCenter;
    // 内环的圆角矩形（以mLeftCenter为坐标原点）
    private RectF mInnerRoundRect;
    // 外环的圆角矩形（以mLeftCenter为坐标原点）
    private RectF mOuterRoundRect;
    private RectF mLiquidBound;
    private Bitmap mLeafBitmap;
    private int mLeafHeight;
    private float liquidLength;
    private int mLeafWidth;


    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.parseColor("#fdcc4b"));
        mPaint = new Paint();
        mBackgroundPicture = new Picture();

        mFanBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fanbellum);
        mInnerRadius = mFanBitmap.getHeight() / 2 + 20;
        mOuterRadius = mInnerRadius + mGap;

        mLeafBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pic_leaft);
        mLeafHeight = mLeafBitmap.getHeight();
        mLeafWidth = mLeafBitmap.getWidth();

        mFanSrcRect = new Rect(0, 0, mFanBitmap.getWidth(), mFanBitmap.getHeight());
        int halfW = mFanBitmap.getWidth() / 2;
        int halfH = mFanBitmap.getHeight() / 2;
        mFabDstRect = new Rect(-halfW, -halfH, halfW, halfH);
        mLeafMatrix = new Matrix();

    }

    public void setProgress(float progress) {
        mProgress = progress;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Timber.e("sizeChanged...");
        mTotalWidth = w;
        mTotalHeight = h;
        mLeftCenter = new Point(mHorPadding + mOuterRadius, mTotalHeight / 2);
        mRightCenter = new Point(mTotalWidth - mHorPadding - mOuterRadius, mTotalHeight / 2);
        mOuterRoundRect = new RectF(-mOuterRadius, -mOuterRadius, mRightCenter.x - mLeftCenter.x + mOuterRadius, mOuterRadius);
        mInnerRoundRect = new RectF(-mInnerRadius, -mInnerRadius, mRightCenter.x - mLeftCenter.x + mInnerRadius, mInnerRadius);
        mLiquidBound = new RectF();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画出静态背景
        drawBackground(canvas);
        // 移动的液面
        drawLiquid(canvas);
        // 飘动的树叶
        drawLeafs(canvas);
        // 转动的扇叶
        drawFan(canvas);

        postInvalidate();

    }

    private void drawBackground(Canvas canvas) {
        canvas.save();
        canvas.translate(mLeftCenter.x, mLeftCenter.y);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#fce196"));
        canvas.drawRoundRect(mOuterRoundRect, mOuterRadius, mOuterRadius, mPaint);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#fdcc4b"));
        canvas.drawRoundRect(mInnerRoundRect, mInnerRadius, mInnerRadius, mPaint);
        canvas.restore();
    }

    /**
     * 转动的扇叶
     */
    private void drawFan(Canvas canvas) {
        canvas.save();
        canvas.translate(mRightCenter.x, mRightCenter.y);
        mPaint.setColor(Color.parseColor("#fdd052"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, mOuterRadius, mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        int strokeWidth = 10;
        mPaint.setStrokeWidth(strokeWidth);
        canvas.drawCircle(0, 0, mOuterRadius - strokeWidth / 2, mPaint);
        canvas.rotate((System.currentTimeMillis() % 720) * 0.5f);
        canvas.drawBitmap(mFanBitmap, mFanSrcRect, mFabDstRect, null);
        canvas.restore();
    }

    /**
     * 移动的液面
     */
    private void drawLiquid(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#ffa800"));
        liquidLength = (mRightCenter.x - mLeftCenter.x + mInnerRadius) * mProgress;
        mLiquidBound.set(-mInnerRadius, -mInnerRadius, mInnerRadius, mInnerRadius);
        canvas.save();
        canvas.translate(mLeftCenter.x, mRightCenter.y);
        if (liquidLength < mInnerRadius) {
            int angle = (int) Math.toDegrees(Math.acos((mInnerRadius - liquidLength) / mInnerRadius));
            canvas.drawArc(mLiquidBound, 180 - angle, 2 * angle, false, mPaint);
        } else {
            canvas.drawArc(mLiquidBound, 90, 180, true, mPaint);
            canvas.drawRect(0, -mInnerRadius, liquidLength - mInnerRadius, mInnerRadius, mPaint);
        }
        canvas.restore();
    }

    // y=Asin(ωx+φ)+k
    private void drawLeafs(Canvas canvas) {
        long currentTime = System.currentTimeMillis()/10;
        int x = (int) (currentTime % (mRightCenter.x - mLeftCenter.x + mInnerRadius));
        if (x + liquidLength > (mRightCenter.x - mLeftCenter.x + mInnerRadius + mLeafWidth)) {
            return;
        }
        double w = (Math.PI * 2 / (mRightCenter.x - mLeftCenter.x));
        int y = (int) ((mInnerRadius - mLeafHeight / 2) * Math.sin(w * x));
        canvas.save();
        canvas.translate(mRightCenter.x, mRightCenter.y - mLeafHeight / 2);
        mLeafMatrix.reset();
        mLeafMatrix.postTranslate(-x, y);
        mLeafMatrix.postRotate(currentTime % 360, -x + mLeafWidth / 2, y + mLeafHeight / 2);
        canvas.drawBitmap(mLeafBitmap, mLeafMatrix, null);
        canvas.restore();
    }

}
