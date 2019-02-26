package com.g45_jones.mobileappsassignment;

import android.graphics.Paint;

import org.json.JSONObject;

public class circNode {

    private float radius;
    private float x;
    private float y;
    private String title;
    private Paint colour;
    private JSONObject item;

    public circNode(float r, float X, float Y, String t, Paint p, JSONObject i){
        radius = r;
        x = X;
        y = Y;
        title = t;
        colour = p;
        item = i;
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

    public JSONObject getItem(){return item;}

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
