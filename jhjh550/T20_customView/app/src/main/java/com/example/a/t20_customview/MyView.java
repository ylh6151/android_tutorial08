package com.example.a.t20_customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by a on 2015-04-20.
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawLine(20,50, 100,50, paint);
        canvas.drawRect(10,110, 150,150, paint);
        canvas.drawCircle(50,200,30,paint);
        canvas.drawText("Hello World",10,300,paint);
    }
}

