package com.g45_jones.mobileappsassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class drawAndDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_and_display);
        RelativeLayout relativeLayout1 = findViewById(R.id.relativeLayoutDrawAndDisplay);
        ImageView image = new ImageView(this);
        image.setBackgroundResource(R.drawable.ic_android_black_24dp);
        relativeLayout1.addView(image);
    }
}
