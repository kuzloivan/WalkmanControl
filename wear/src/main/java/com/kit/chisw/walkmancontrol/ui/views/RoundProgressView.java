package com.kit.chisw.walkmancontrol.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class RoundProgressView extends View {
    private float strokeWidth = 7;

    public RoundProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas, (int) (strokeWidth/2));
    }

    private void drawArc(Canvas pCanvas, int offset) {
        Paint paint3 = new Paint();
        paint3.setDither(true);
        paint3.setDither(true);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeCap(Paint.Cap.BUTT);
        paint3.setStrokeJoin(Paint.Join.BEVEL);
        paint3.setStrokeWidth(strokeWidth);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.parseColor("#01D3E4"));
        RectF mRectF2 = new RectF(offset, offset, pCanvas.getWidth() - offset, pCanvas.getHeight() - offset);
        pCanvas.drawArc(mRectF2, -90, 180, false, paint3);
    }
    public void setAngle(double angle) {
        invalidate();
    }






}
