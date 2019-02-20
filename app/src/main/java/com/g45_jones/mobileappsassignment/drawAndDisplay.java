package com.g45_jones.mobileappsassignment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class drawAndDisplay extends AppCompatActivity {

    DrawNodeView drawNodeView;
    private ViewGroup rootLayout;

    private ArrayList<String> oName = new ArrayList<>();
    String name;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_and_display);
        this.rootLayout = findViewById(R.id.relativeLayoutDrawAndDisplay);
        drawNodeView = findViewById(R.id.nodeView);

        //Get related data
        Bundle results = getIntent().getExtras();
        if(results != null)
        {
            Log.d("Hello", "Bundle = " + results);
            String companyName = results.getString("Company Name");
            if (companyName != null){
                Log.d("Hello",companyName);
                name = companyName;

            }
            String companyNo = results.getString("Company Number");
            if(companyNo != null){
                Log.d("Hello", companyNo);
                number = companyNo;
            }
            String tempItems = results.getString("items");
            if(tempItems != null){
                Log.d("Hello", "Got the items");
                try{
                    JSONArray items = new JSONArray(tempItems);
                    //Iterate through the array extracting the name of each officer of the company
                    String o;
                    for (int i =0; i < items.length(); i++){
                        Log.d("Hello", "Officer =" + items.getJSONObject(i).getString("name"));
                        oName.add(items.getJSONObject(i).getString("name"));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }

        drawNodeView.createNodeDiagram(name, number, oName);
        // Draw graph
        //drawNodeView = new DrawNodeView(this);
        //drawNodeView.setBackgroundColor(Color.CYAN);
        //setContentView(drawNodeView);

    }
}
