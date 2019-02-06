package com.g45_jones.mobileappsassignment.views;

import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class DrawNodeView extends View{

    Paint paint = new Paint();

    private void init(){
        paint.setColor(Color.BLACK);
    }

    public DrawNodeView(Context context) {
        super(context);

        init();
    }

    public DrawNodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawNodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(0, 0, 500, 500, paint);
        canvas.drawLine(20, 0, 0, 20, paint);
    }
}
