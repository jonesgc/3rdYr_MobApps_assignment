package com.g45_jones.mobileappsassignment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.g45_jones.mobileappsassignment.views.DrawNodeView;


public class drawAndDisplay extends AppCompatActivity {

    DrawNodeView drawNodeView;
    private ViewGroup rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_and_display);
        this.rootLayout = findViewById(R.id.relativeLayoutDrawAndDisplay);

        drawNodeView = new DrawNodeView(this);
        drawNodeView.setBackgroundColor(Color.CYAN);
        //setContentView(drawNodeView);



    }
}
