package com.g45_jones.mobileappsassignment.listeners;

import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.g45_jones.mobileappsassignment.circNode;
import java.util.ArrayList;

public class customTouchListener extends GestureDetector.SimpleOnGestureListener {

    ArrayList<circNode> nodeList;

    public customTouchListener(ArrayList<circNode> nl){
        nodeList = nl;
    }

    @Override
    public void onLongPress(MotionEvent event){
        super.onLongPress(event);
        // e will give you the location and everything else you want
        // This is where you will be doing whatever you want to.
        int eIndex = event.getActionIndex();
        float eX = event.getX(eIndex);
        float eY = event.getY(eIndex);
        Log.d("Hello","X:Y = " + eX + " : " + eY);

        for (int i = 0; i < nodeList.size(); i++){
            if(checkBounds(eX,eY, nodeList.get(i).getX(), nodeList.get(i).getY())){
                Log.d("Hello", "onLongPress of " + nodeList.get(i).getTitle());
            }
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    //Used to check of the touch input is triggered inside a node.
    private boolean checkBounds(float tx, float ty, float nx, float ny){
        if((tx - nx) * (tx - nx) + (ty - ny) * (ty - ny) <= 50 * 50){
            return true;
        }
        else{
            return false;
        }
    }
}
