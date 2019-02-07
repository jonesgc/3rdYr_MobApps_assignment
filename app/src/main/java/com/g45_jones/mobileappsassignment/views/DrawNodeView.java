package com.g45_jones.mobileappsassignment.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.g45_jones.mobileappsassignment.R;


public class DrawNodeView extends View{

    private static int selection_count = 4;
    private float width;
    private float height;

    private int activeSelection;
    Paint paint1;
    private final float [] tempResult = new float[2];

    //Node arrays: the data "within", radius
    private float rad;
    private float node1X;
    private float node1Y;
    private float node2;
    private float node2X;
    private float node2Y;
    Paint paint2;

    //Node specific constants
    private final float nodeRad = 50;

    //Variables to store the x and y position of a touch event
    private float tX;
    private float tY;

    //Animation variables
    private static final int ANIMATION_DURATION = 4000;
    private static final long ANIMATION_DELAY = 1000;

    private void init(){
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.BLACK);
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.GREEN);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        activeSelection = 0;
        rad = nodeRad;
        node2 = nodeRad;
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

    //All the nodes and lines are executed within this function
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        node1X = 500;
        node1Y = 500;

        node2X = 200;
        node2Y = 200;

        //draw a node
        canvas.drawLine(500, 500, 200, 200, paint1);
        canvas.drawCircle(node2X, node2Y, node2, paint2);
        canvas.drawCircle(node1X, node1Y, rad, paint1);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        width = w;
        height = h;
        //rad = (float) (Math.min(width, height) / 2*0.8);
    }

    private float[] computeXYForPosition(final int pos, final float radius) {
        float[] result = tempResult;
        Double startAngle = Math.PI * (9 / 8d);   // Angles are in radians.
        Double angle = startAngle + (pos * (Math.PI / 4));
        result[0] = (float) (radius * Math.cos(angle)) + (width / 2);
        result[1] = (float) (radius * Math.sin(angle)) + (height / 2);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            tX = event.getX();
            Log.d("Hello","X pos =" + tX);
            tY = event.getY();
            Log.d("Hello", "Y pos =" + tY);

            if(checkBounds(tX,tY)){
                Log.d("Hello", "onTouchEvent: bounds");
            }

        }
        return super.onTouchEvent(event);
    }

    public boolean checkBounds(float tx, float ty){

        //Loop through nodes checking if event was in that area.


        if((tx - node1X) * (tx - node1X) +
                (ty - node1Y) * (ty - node1Y) <= rad * rad){
            return true;
        }
        else{
            return false;
        }

    }
}
