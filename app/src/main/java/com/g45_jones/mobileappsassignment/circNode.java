package com.g45_jones.mobileappsassignment;

import android.graphics.Paint;

public class circNode {

    private float radius;
    private float x;
    private float y;
    private String title;
    private Paint colour;

    public circNode(float r, float X, float Y, String t, Paint p){
        radius = r;
        x = X;
        y = Y;
        title = t;
        colour = p;
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

    public Paint getColour(){
        return colour;
    }

    //Setters for values
    public void setColour(Paint p){
        colour = p;
    }

    public void setX(float eX){
        x = eX;
    }

    public void setY(float eY){
        y = eY;
    }
}
