package com.pingan.taijiview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created time : 2019/5/31 17:11.
 *
 * @author LKKJ
 */
public class TaiJiView extends View {
    private RotateAnimation rotateAnimation;//旋转动画
    private int width = 480;
    private int height = 480;
    private Paint mPaint;//画笔
    private RectF mRectF;//整体圆弧所在正方形
    private RectF blackHalfRect;//黑色圆弧所在正方形
    private RectF whiteHalfRect;//白色圆弧所在正方形

    public TaiJiView(Context context) {
        this(context, null);
    }

    public TaiJiView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaiJiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
        initAnim();

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
    }

    private void initAnim() {
        //以view的中心点为旋转参考点
        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(false);

        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());//不停顿
        startAnimation(rotateAnimation);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
        mRectF = new RectF(0, 0, width, height);
    }

    /**
     * 开始绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawHalfCircle(canvas);
        drawSmallCircle(canvas);
    }

    /*
     *绘制整体圆形
     */
    private void drawCircle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(mRectF, 270, 180, true, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(mRectF, 270, -180, true, mPaint);
    }

    /**
     * @param canvas 绘制半圆 一个黑色圆弧 一个白色圆弧
     */
    private void drawHalfCircle(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        blackHalfRect = new RectF(width / 4, 0, width / 2 + width / 4, width / 2);
        canvas.drawArc(blackHalfRect, 270, 180, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        whiteHalfRect = new RectF(width / 4, width / 2, width / 2 + width / 4, width);
        canvas.drawArc(whiteHalfRect, 270, -180, true, mPaint);
    }

    /**
     * 绘制两个实心小圆点
     *
     * @param canvas
     */
    private void drawSmallCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, width / 4, 20, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(width / 2, width / 4 * 3, 20, mPaint);
    }

}
