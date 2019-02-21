package com.g45_jones.mobileappsassignment.views;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
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
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

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
    Paint pBlue;

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
    private boolean moved;
    private Rect currentView;
    private RectF contentRect;
    private ScaleGestureDetector scaleListener;
    private float scaleFactor = 1.0f;


    public DrawNodeView(Context context) {
        super(context);
        init();
        this.setDrawingCacheEnabled(true);
    }

    public DrawNodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        this.setDrawingCacheEnabled(true);
    }

    public DrawNodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        this.setDrawingCacheEnabled(true);
    }

    private void init() {
        scaleListener = new ScaleGestureDetector(getContext(), new scaleListener());


        pBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        pBlack.setColor(Color.BLACK);
        pBlack.setStyle(Paint.Style.FILL_AND_STROKE);

        pGreen = new Paint(Paint.ANTI_ALIAS_FLAG);
        pGreen.setColor(Color.GREEN);
        pGreen.setStyle(Paint.Style.FILL_AND_STROKE);

        pRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        pRed.setColor(Color.RED);
        pRed.setStyle(Paint.Style.FILL_AND_STROKE);

        pBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        pBlue.setColor(Color.BLUE);
        pBlue.setStyle(Paint.Style.FILL_AND_STROKE);
        currentView = new Rect();
    }

    //All the nodes and lines are executed within this function
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        invalidate();


        canvas.save();
        canvas.scale(scaleFactor, scaleFactor, 500, 500);
        if (!nodeList.isEmpty()) {
            //connect the nodes
            if (nodeList.size() == 2) {
                connections(canvas, 0, 1);
            } else if (nodeList.size() > 2) {
                for (int i = 1; i < nodeList.size(); i++) {
                    connections(canvas, 0, i);
                }
            }

            //Draw nodes
            for (int i = 0; i < nodeList.size(); i++) {
                canvas.drawCircle(nodeList.get(i).getX(),
                        nodeList.get(i).getY(),
                        nodeList.get(i).getRadius(),
                        nodeList.get(i).getColour());
            }
        }
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointers = event.getPointerCount();


        //Log.d("Hello", "pointers = " + pointers);
        //Two fingers for scaling
        if(pointers == 1){
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
                            Log.d("Hello", "Node" + i + " x pos =" + nodeList.get(i).getX() + "y pos" + nodeList.get(i).getY());
                            if (checkBounds(tX, tY, nodeList.get(i).getX(), nodeList.get(i).getY())) {
                                Log.d("Hello", "onTouchEvent: bounds" + i);
                                //nodeList.get(i).setColour(pBlack);
                                if (!moved) {
                                    Toast.makeText(getContext(), nodeList.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        //Log.d("Hello", "onTouchEvent: UP");

                        touched = null;
                        threshold = 0;
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    //Log.d("Hello", "ACtion down");
                    //Look if the action was inside a node
                    tX = event.getX();
                    tY = event.getY();
                    //Iterate through the nodeList checking if the touch event hit any of the nodes
                    for (int i = 0; i < nodeList.size(); i++) {
                        if (checkBounds(tX, tY, nodeList.get(i).getX(), nodeList.get(i).getY())) {
                            Log.d("Hello", "onTouchEvent: bounds" + i);
                            //nodeList.get(i).setColour(pRed);
                            touched = i;
                        }
                    }
                    touchFlag = true;
                    moved = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    //since the nodelist starts at 0 we need to use an Integer to check if its null or 0
                    //Log.d("Hello", "Movement event +1 threshold");
                    threshold += 1;
                    if (touched != null && threshold >= 15) {
                        Log.d("Hello", "Moving " + touched);
                        tX = event.getX();
                        tY = event.getY();
                        nodeList.get(touched).setX(tX);
                        nodeList.get(touched).setY(tY);
                        moved = true;
                        //nodeList.get(touched).setColour(pRed);
                    } else {

                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    Log.d("Hello", "onTouchEvent: Canceled");
                    break;
            }
        }
        else if(pointers == 2){
            scaleListener.onTouchEvent(event);
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
        if (!nodeList.isEmpty()) {
            canvas.drawLine(nodeList.get(node1).getX(),
                    nodeList.get(node1).getY(),
                    nodeList.get(node2).getX(),
                    nodeList.get(node2).getY(),
                    pBlack);
        }

    }

    public void createNodeDiagram(String companyName, String companyNumber, ArrayList<String> officers) {
        //Nodes are created with the company title at the center.
        //Spacing is used to increase the radius of the "circle" being used to calcuate the parametric
        float spacing = 200;
        int offset = 360 / officers.size();
        int base = 0;

        if (officers.size() >= 10 & officers.size() <= 20) {
            spacing += 100;
        } else if (officers.size() > 20) {
            spacing += 400;
        }

        nodeList.add(new circNode(nodeRad, (float) 500.0, (float) 500.0, companyName, pGreen));


        for (int i = 0; i < officers.size(); i++) {
            double angle = Math.toRadians(base);
            float x = (float) 500 + (float) (nodeRad + spacing) * (float) Math.cos(angle);
            float y = (float) 500 + (float) (nodeRad + spacing) * (float) Math.sin(angle);

            nodeList.add(new circNode(nodeRad, (float) x, (float) y, officers.get(i), pBlue));
            Log.d("Hello", "Base is current: " + base);
            base = base + offset;
            Log.d("Hello", "Base + offset = " + base);
        }

    }

    //Gets the bitmap image of the canvas from the cache
    public Bitmap getBitmap() {
        return this.getDrawingCache();
    }


    public class scaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {



        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }
}