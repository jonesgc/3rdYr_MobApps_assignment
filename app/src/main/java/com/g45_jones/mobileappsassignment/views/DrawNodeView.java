package com.g45_jones.mobileappsassignment.views;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.g45_jones.mobileappsassignment.R;
import com.g45_jones.mobileappsassignment.circNode;

import java.util.ArrayList;

public class DrawNodeView extends View {

    //Node variables
    private final float nodeRad = 50;
    private float rad;
    private ArrayList<circNode> nodeList = new ArrayList<circNode>();
    Paint pBlack;
    Paint pGreen;
    Paint pRed;

    //Screen
    private float width;
    private float height;

    //Variables to store the x and y position of a touch event
    private float tX;
    private float tY;

    //Animation variables
    private boolean touchFlag;
    private Integer touched;
    private float threshold;


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

    private void init() {

        pBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        pBlack.setColor(Color.BLACK);
        pBlack.setStyle(Paint.Style.FILL_AND_STROKE);

        pGreen = new Paint(Paint.ANTI_ALIAS_FLAG);
        pGreen.setColor(Color.GREEN);
        pGreen.setStyle(Paint.Style.FILL_AND_STROKE);

        pRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        pRed.setColor(Color.RED);
        pRed.setStyle(Paint.Style.FILL_AND_STROKE);


    }

    //All the nodes and lines are executed within this function
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        invalidate();

        if(!nodeList.isEmpty()){
            //connect the two nodes.
            //connections(canvas, 0, 1);
            //connections(canvas, 1, 2);

            //Draw nodes
            for (int i = 0; i < nodeList.size(); i++) {
                canvas.drawCircle(nodeList.get(i).getX(),
                        nodeList.get(i).getY(),
                        nodeList.get(i).getRadius(),
                        nodeList.get(i).getColour());
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        //rad = (float) (Math.min(width, height) / 2*0.8);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked() & MotionEvent.ACTION_MASK) {
            //This event follows an ACTION_DOWN and consittudes a tap.
            case MotionEvent.ACTION_UP:
                if (touchFlag) {
                    tX = event.getX();
                    Log.d("Hello", "X pos =" + tX);
                    tY = event.getY();
                    Log.d("Hello", "Y pos =" + tY);
                    touchFlag = true;
                    //Iterate through the nodeList checking if the touch event hit any of the nodes
                    for (int i = 0; i < nodeList.size(); i++) {
                        if (checkBounds(tX, tY, nodeList.get(i).getX(), nodeList.get(i).getY())) {
                            Log.d("Hello", "onTouchEvent: bounds" + i);
                            nodeList.get(i).setColour(pBlack);
                        }
                    }
                    Log.d("Hello", "onTouchEvent: UP");

                    touched = null;
                    threshold=0;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                Log.d("Hello", "ACtion down");
                //Look if the action was inside a node
                tX = event.getX();
                tY = event.getY();
                //Iterate through the nodeList checking if the touch event hit any of the nodes
                for (int i = 0; i < nodeList.size(); i++) {
                    if (checkBounds(tX, tY, nodeList.get(i).getX(), nodeList.get(i).getY())) {
                        Log.d("Hello", "onTouchEvent: bounds" + i);
                        nodeList.get(i).setColour(pRed);
                        touched = i;
                    }
                }
                touchFlag = true;
                break;
            case MotionEvent.ACTION_MOVE:
                //since the nodelist starts at 0 we need to use an Integer to check if its null or 0
                Log.d("Hello", "Movement event +1 threshold");
                threshold+=1;
                if (touched != null && threshold >= 5) {
                    Log.d("Hello", "Moving " + touched);
                    tX = event.getX();
                    tY = event.getY();
                    nodeList.get(touched).setX(tX);
                    nodeList.get(touched).setY(tY);
                    nodeList.get(touched).setColour(pRed);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("Hello", "onTouchEvent: Canceled");
                break;
        }
        return true;
    }

    //Used to check of the touch input is triggered inside a node.
    public boolean checkBounds(float tx, float ty, float nx, float ny) {
        if ((tx - nx) * (tx - nx) + (ty - ny) * (ty - ny) <= nodeRad * nodeRad) {
            return true;
        } else {
            return false;
        }
    }

    public void connections(Canvas canvas, int node1, int node2) {
        if(!nodeList.isEmpty()){
            canvas.drawLine(nodeList.get(node1).getX(),
                    nodeList.get(node1).getY(),
                    nodeList.get(node2).getX(),
                    nodeList.get(node2).getY(),
                    pBlack);
        }

    }

    public void createNodeDiagram(String companyName,String companyNumber ,ArrayList<String> officers){

        //Data is retrieved.
        //Nodes are created with the company title at the center.

        Log.d("Hello", companyName);
        if (nodeList == null){
            Log.d("Hello", "Its null");

        }

        nodeList.add(new circNode(nodeRad, (float) 500.0, (float) 500.0, companyName, pGreen));
        //double angle = Math.cos(Math.toRadians(90));
        //Log.d("Hello", "createNodeDiagram:" + angle);

//        for (int i = 0; i < officers.size(); i++){
//            nodeList.add(new circNode(nodeRad, (float) 500.0, (float) 300.0, officers.get(i), pGreen));
//        }

    }
}

