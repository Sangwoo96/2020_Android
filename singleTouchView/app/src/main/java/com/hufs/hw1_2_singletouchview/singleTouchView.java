package com.hufs.hw1_2_singletouchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class singleTouchView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();

    public singleTouchView(Context context) {
        super(context);
        paint.setAntiAlias(true); //글자 주변에 투명도를 준다
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLUE);//파랑색
        paint.setStyle(Paint.Style.STROKE);//일반선
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint); //path를 paint로 그려줌
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float evenX = event.getX(); //event가 발생한 x좌표
        float evenY = event.getY(); //event가 발생한 y좌표

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //클릭
                path.moveTo(evenX, evenY);
                return true;
            case MotionEvent.ACTION_MOVE: //클릭 후 움직임
                path.lineTo(evenX, evenY);
                break;
            case MotionEvent.ACTION_UP: //클릭 취소
                break;
            default:
                return false;
        }
        invalidate(); //view의 onDraw 메소드 호출
        return true;
    }
}
