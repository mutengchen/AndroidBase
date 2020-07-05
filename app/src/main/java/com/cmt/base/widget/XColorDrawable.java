package com.cmt.base.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;

/**
 * Date: 2019/12/27
 * Time: 14:38
 * author: cmt
 * desc: 自定义drawable类，对drawable进行了基本配置上的实现
 */
public class XColorDrawable extends Drawable {
    //画笔
    private Paint mPaint;
    //画笔颜色
    private int color;
    //矩阵
    private RectF rectF;

    public XColorDrawable(){
        mPaint = new Paint();
        //抗锯齿模式
        mPaint.setAntiAlias(true);

    }
    public XColorDrawable(int color) {
        this.color = color;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }
    public void setColor(@ColorInt int color){
        this.color = color;
    }


    @Override
    public void draw(Canvas canvas) {
        mPaint.setColor(color);
        canvas.drawRoundRect(rectF,20,20,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
