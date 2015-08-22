package com.example.c.t28_customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by c on 2015-08-22.
 */
public class MyView extends View {

    private Paint mPaint;
    RectF oval;
    float mStart , mSweep;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        oval = new RectF(40,10,280,250);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawLine(20, 50, 100, 100, paint);
        canvas.drawRect(10, 100, 150, 150, paint);
        canvas.drawCircle(50, 200, 30, paint);
        canvas.drawText("Hello customView", 200, 200, paint);

        canvas.drawArc(oval, mStart, mSweep, false, mPaint);
        mSweep += 2;
        if(mSweep> 360){
            mSweep -= 360;
            mStart +=2;
            if(mStart >= 360){
                mStart -= 360;
            }
        }
        invalidate();
    }
}
