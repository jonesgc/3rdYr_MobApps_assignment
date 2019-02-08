package com.g45_jones.mobileappsassignment.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.g45_jones.mobileappsassignment.R;
import com.g45_jones.mobileappsassignment.circNode;
import com.g45_jones.mobileappsassignment.listeners.customTouchListener;
import java.util.ArrayList;
import java.util.Vector;

public class DrawNodeView extends View{

    private float width;
    private float height;

    //Node arrays: the data "within", radius
    private float rad;
    Paint pBlack;
    Paint pGreen;
    Paint pRed;
    private ArrayList<circNode> nodeList = new ArrayList<circNode>();

    //Node specific constants
    private final float nodeRad = 50;

    //Variables to store the x and y position of a touch event
    private float tX;
    private float tY;

    //Animation variables
    private static final int ANIMATION_DURATION = 4000;
    private static final long ANIMATION_DELAY = 1000;

    customTouchListener touchListener;
    GestureDetector gestureDetector;

    private void init(){
        touchListener = new customTouchListener(nodeList);
        gestureDetector = new GestureDetector(getContext(), touchListener);

        pBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        pBlack.setColor(Color.BLACK);
        pBlack.setStyle(Paint.Style.FILL_AND_STROKE);

        pGreen = new Paint(Paint.ANTI_ALIAS_FLAG);
        pGreen.setColor(Color.GREEN);
        pGreen.setStyle(Paint.Style.FILL_AND_STROKE);

        pRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        pRed.setColor(Color.RED);
        pRed.setStyle(Paint.Style.FILL_AND_STROKE);

        nodeList.add(new circNode(nodeRad, (float) 608.0, (float) 1311, "Test1", pGreen));
        nodeList.add(new circNode(nodeRad, (float) 500.0, (float) 400.0, "Test2", pGreen));
        nodeList.add(new circNode(nodeRad, (float) 200.0, (float) 1000.0, "test3", pGreen));
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
        invalidate();
        //Draw lines, connecting nodes showing "relationships"
        /*canvas.drawLine(nodeList.get(1).getX(),
                nodeList.get(1).getY(),
                nodeList.get(0).getX(),
                nodeList.get(0).getY(),
                pBlack);*/

        //connect the two nodes.
        connections(canvas, 0, 1);
        connections(canvas, 1, 2);

        //Draw nodes
        for(int i =0; i < nodeList.size(); i++){
            canvas.drawCircle(nodeList.get(i).getX(),
                    nodeList.get(i).getY(),
                    nodeList.get(i).getRadius(),
                    nodeList.get(i).getColour());
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        width = w;
        height = h;
        //rad = (float) (Math.min(width, height) / 2*0.8);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        /*
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            tX = event.getX();
            Log.d("Hello","X pos =" + tX);
            tY = event.getY();
            Log.d("Hello", "Y pos =" + tY);

            //Iterate through the nodeList checking if the touch event hit any of the nodes
            for (int i = 0; i < nodeList.size(); i++){
                if(checkBounds(tX,tY, nodeList.get(i).getX(), nodeList.get(i).getY())){
                    Log.d("Hello", "onTouchEvent: bounds");
                }
            }
        }*/
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //Used to check of the touch input is triggered inside a node.
    public boolean checkBounds(float tx, float ty, float nx, float ny){
        if((tx - nx) * (tx - nx) + (ty - ny) * (ty - ny) <= nodeRad * nodeRad){
            return true;
        }
        else{
            return false;
        }
    }

    public void connections(Canvas canvas, int node1, int node2){

        canvas.drawLine(nodeList.get(node1).getX(),
                nodeList.get(node1).getY(),
                nodeList.get(node2).getX(),
                nodeList.get(node2).getY(),
                pBlack);
    }
}

