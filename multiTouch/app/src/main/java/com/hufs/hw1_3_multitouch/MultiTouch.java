package com.hufs.hw1_3_multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouch extends View {
    private static final int SIZE = 60;
    final int MAX_POINTS = 10;
    float[] x = new float[MAX_POINTS];
    float[] y = new float[MAX_POINTS];
    boolean[] touching = new boolean[MAX_POINTS];
    private Paint paint;

    public MultiTouch(Context context){
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i =0; i< MAX_POINTS; i++){
            if(touching[i]) {
                canvas.drawCircle(x[i], y[i], SIZE, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int idx = event.getActionIndex();
        int id = event.getPointerId(idx);
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                x[id] = (int) event.getX(idx);
                y[id] = (int) event.getY(idx);
                touching[id] = true;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                touching[id] = false;
                break;
        }
        invalidate();
        return true;
    }
}
