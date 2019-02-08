package com.g45_jones.mobileappsassignment;

public class circNode {

    private float radius;
    private float x;
    private float y;
    private String title;

    public circNode(float r, float X, float Y, String t){
        radius = r;
        x = X;
        y = Y;
        title = t;
    }

    //getters for values.
    public float getRadius(){
        return radius;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public String getTitle(){
        return title;
    }
}
