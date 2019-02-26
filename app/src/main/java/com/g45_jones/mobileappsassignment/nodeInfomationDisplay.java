package com.g45_jones.mobileappsassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class nodeInfomationDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_info_display);

        //Get the contents of the bundle sent from drawAndDisplay
        Bundle query = getIntent().getExtras();
        if(query != null)
        {
            String title = query.getString("title");
            if(title != null)
            {
                Log.d("Hello",title);
            }
            else
            {
                Log.d("Hello","NUll");
            }
        }
    }
}
