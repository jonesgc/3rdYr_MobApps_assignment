package com.g45_jones.mobileappsassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class nodeInfomationDisplay extends AppCompatActivity {

    String title;
    JSONObject items;
    TextView titleView;
    TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_info_display);

        titleView = findViewById(R.id.titleView);
        infoView = findViewById(R.id.infoView);

        //Get the contents of the bundle sent from drawAndDisplay
        Bundle query = getIntent().getExtras();
        if (query != null) {
            String stringTitle = query.getString("title");
            if (stringTitle != null) {
                //Log.d("Hello", stringTitle);
                title = stringTitle;
                titleView.setText(title);

            } else {
                Log.d("Hello", "title is NUll");
            }
            String stringItems = query.getString("items");
            if (stringItems != null) {
                //Log.d("Hello", stringItems);
                try{
                    items = new JSONObject(stringItems);
                    Log.d("Hello", "items =" + items);
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }else{
                Log.d("Hello", "Items is null");
            }
        }
    }
}
