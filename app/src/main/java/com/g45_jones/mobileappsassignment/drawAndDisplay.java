package com.g45_jones.mobileappsassignment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.g45_jones.mobileappsassignment.views.DrawNodeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class drawAndDisplay extends AppCompatActivity {

    DrawNodeView drawNodeView;
    private ViewGroup rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_and_display);
        this.rootLayout = findViewById(R.id.relativeLayoutDrawAndDisplay);

        //Get related data

        //Draw graph
        drawNodeView = new DrawNodeView(this);
        drawNodeView.setBackgroundColor(Color.CYAN);
        //setContentView(drawNodeView);

    }

}
