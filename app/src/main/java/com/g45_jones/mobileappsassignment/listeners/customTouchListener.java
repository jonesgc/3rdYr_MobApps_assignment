package com.g45_jones.mobileappsassignment.listeners;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.g45_jones.mobileappsassignment.circNode;

import java.util.ArrayList;

public class customTouchListener extends GestureDetector.SimpleOnGestureListener {

    ArrayList<circNode> nodeList;

    public customTouchListener(ArrayList<circNode> nl) {
        nodeList = nl;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        // triggers after onDown only for single tap
        Log.d("Hello", "single: ");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        super.onDoubleTap(event);

        int eIndex = event.getActionIndex();
        float eX = event.getX(eIndex);
        float eY = event.getY(eIndex);

        Paint pBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        pBlue.setColor(Color.BLUE);
        pBlue.setStyle(Paint.Style.FILL_AND_STROKE);

        for (int i = 0; i < nodeList.size(); i++) {
            if (checkBounds(eX, eY, nodeList.get(i).getX(), nodeList.get(i).getY())) {
                Log.d("Hello", "onDoubleTap of " + nodeList.get(i).getTitle());
                nodeList.get(i).setColour(pBlue);
            }
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        super.onLongPress(event);
        // event will give you the location and everything else you want
        // This is where you will be doing whatever you want to.
        int eIndex = event.getActionIndex();
        float eX = event.getX(eIndex);
        float eY = event.getY(eIndex);

        Log.d("Hello", "X:Y = " + eX + " : " + eY);

        Paint pRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        pRed.setColor(Color.RED);
        pRed.setStyle(Paint.Style.FILL_AND_STROKE);

        for (int i = 0; i < nodeList.size(); i++) {
            if (checkBounds(eX, eY, nodeList.get(i).getX(), nodeList.get(i).getY())) {
                Log.d("Hello", "onLongPress of " + nodeList.get(i).getTitle());
                nodeList.get(i).setColour(pRed);
            }
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        super.onDown(e);
        Log.d("Hello", "onDown");
        return true;
    }

    //Used to check of the touch input is triggered inside a node.
    private boolean checkBounds(float tx, float ty, float nx, float ny) {

        if ((tx - nx) * (tx - nx) + (ty - ny) * (ty - ny) <= 50 * 50) {
            return true;
        } else {
            return false;
        }
    }
}
