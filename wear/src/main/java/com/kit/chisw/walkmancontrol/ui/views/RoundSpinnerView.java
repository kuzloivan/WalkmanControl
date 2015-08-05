package com.kit.chisw.walkmancontrol.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class RoundSpinnerView extends View {
    private double maxAngle = 100;
    private float strokeWidth = 7;

    double angle = 100;
    double oldAngle = 270;

    private ProgressListener mProgressListener;

    public RoundSpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setProgressListener(ProgressListener pProgressListener) {
        mProgressListener = pProgressListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas, 30);
    }

    private void drawArc(Canvas pCanvas, int offset) {
        {
            Paint paint = new Paint();
            paint.setDither(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setStrokeJoin(Paint.Join.BEVEL);
            paint.setStrokeWidth(strokeWidth);
            paint.setAntiAlias(true);
            paint.setColor(Color.argb(255, 160, 160, 160));
            RectF mRectF = new RectF(offset, offset, pCanvas.getWidth() - offset, pCanvas.getHeight() - offset);
            pCanvas.drawArc(mRectF, (float) (90 + (maxAngle / 2)), -(float) maxAngle, false, paint);

            float x1 = (float) (pCanvas.getWidth() / 2 + (mRectF.width() / 2) * Math.cos(((90 + maxAngle / 2) * Math.PI) / 180));
            float y1 = (float) (pCanvas.getHeight() / 2 + (mRectF.width() / 2) * Math.sin(((90 + maxAngle / 2) * Math.PI) / 180));
            Paint paint1 = new Paint();
            paint1.setColor(Color.argb(255, 160, 160, 160));
            paint1.setAntiAlias(true);
            pCanvas.drawCircle(x1, y1, (float) strokeWidth / 2, paint1);

            float x2 = (float) (pCanvas.getWidth() / 2 + (mRectF.width() / 2) * Math.cos(((90 - maxAngle / 2) * Math.PI) / 180));
            float y2 = (float) (pCanvas.getHeight() / 2 + (mRectF.width() / 2) * Math.sin(((90 - maxAngle / 2) * Math.PI) / 180));
            Paint paint2 = new Paint();
            paint2.setColor(Color.argb(255, 160, 160, 160));
            paint2.setAntiAlias(true);
            pCanvas.drawCircle(x2, y2, (float) strokeWidth / 2, paint2);
        }


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
        pCanvas.drawArc(mRectF2, (float) (90 + (maxAngle / 2)), -(float) angle, false, paint3);


        float x4 = (float) (pCanvas.getWidth() / 2 + (mRectF2.width() / 2) * Math.cos(((90 + maxAngle / 2) * Math.PI) / 180));
        float y4 = (float) (pCanvas.getHeight() / 2 + (mRectF2.width() / 2) * Math.sin(((90 + maxAngle / 2) * Math.PI) / 180));
        Paint paint4 = new Paint();
        paint4.setColor(Color.parseColor("#01D3E4"));
        paint4.setAntiAlias(true);
        pCanvas.drawCircle(x4, y4, (float) strokeWidth / 2, paint4);


        float x5 = (float) (pCanvas.getWidth() / 2 + (mRectF2.width() / 2) * Math.cos(((90 - maxAngle / 2 + (maxAngle - angle)) * Math.PI) / 180));
        float y5 = (float) (pCanvas.getHeight() / 2 + (mRectF2.width() / 2) * Math.sin(((90 - maxAngle / 2 + (maxAngle - angle)) * Math.PI) / 180));
        Paint paint5 = new Paint();
        paint5.setColor(Color.WHITE);
        paint5.setAntiAlias(true);
        pCanvas.drawCircle(x5, y5, 10, paint5);
    }


    public void setAngle(double angle) {
        this.angle = angle;
        this.oldAngle = angle;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Write your code to perform an action on down
                break;
            case MotionEvent.ACTION_MOVE:
                double x2 = getWidth() / 2;
                double y2 = getHeight() / 2;
                double x1 = (int) event.getX();
                double y1 = (int) event.getY();
                double inRads = Math.atan2((y2 - y1), (x2 - x1));


                if (inRads < 0)
                    inRads = Math.abs(inRads);
                else
                    inRads = 2 * Math.PI - inRads;

                angle = Math.toDegrees(inRads) - maxAngle / 2;
                Log.d("ANGLE", "" + angle);
                if (angle < 0 | angle > 101) {
                    return true;
                }
                if (mProgressListener != null)
                    mProgressListener.onChange((int) angle);

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (angle < 0 | angle > 101) {
                    return true;
                }
                if (mProgressListener != null)
                    mProgressListener.onChange((int) angle);
                break;
        }


        return true;
    }

    public interface ProgressListener {
        public void onProgressChange(int progress);

        public void onChange(int progress);
    }


}
