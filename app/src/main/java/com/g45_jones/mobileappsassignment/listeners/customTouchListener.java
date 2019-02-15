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
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float xDist, float yDist){

        return true;
    }
}
