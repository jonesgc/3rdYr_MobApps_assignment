package com.g45_jones.mobileappsassignment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


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
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class drawAndDisplay extends AppCompatActivity {

    DrawNodeView drawNodeView;
    private ViewGroup rootLayout;

    private ArrayList<String> oName = new ArrayList<>();
    private ArrayList<JSONObject> oItems = new ArrayList<>();
    String name;
    String number;
    String cItems;
    private Button share;

    private android.support.v7.widget.ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Permissions code adapted from https://developer.android.com/guide/topics/permissions/overview.html
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("Hello", "onCreate: Requesting permission");

            //Request the permission if it has not already been granted to the app by the user.
            ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        setContentView(R.layout.activity_draw_and_display);
        drawNodeView = findViewById(R.id.nodeView);

        //Action bar for back button:
        //Adapted from: https://www.freakyjolly.com/how-to-add-back-arrow-in-android-activity/#more-590
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Button to be used with the sharing button in the action bar.
        share = findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("Hello", "Attempting to share");
            }
        });

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
            String tempCitems = results.getString("Company Items");
            if(tempCitems != null){
                Log.d("Hello", "Got the company items" + tempCitems);
                cItems = tempCitems;
            }
            String tempItems = results.getString("items");
            if(tempItems != null){
                Log.d("Hello", "Got the items");
                try{
                    JSONArray items = new JSONArray(tempItems);
                    //Iterate through the array extracting the name of each officer of the company
                    String o;
                    for (int i =0; i < items.length(); i++){
                        Log.d("Hello", "Officer =" + items.getJSONObject(i));
                        oName.add(items.getJSONObject(i).getString("name"));
                        oItems.add(items.getJSONObject(i));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }


        drawNodeView.createNodeDiagram(name, number, oName, oItems, cItems);
    }

    //Functions used for the back button.
    public boolean onOptionsItemSelected(MenuItem item){
        Log.d("Hello", "onOptionsItemSelected:"+ item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_item_share:
                Log.d("Hello","Pressed on the buton");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Share action bar button code adapted from https://developer.android.com/training/sharing/shareaction
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.share_menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        //Link the intent, requires calling the function to get the bitmap then the URI for the canvas
        if(shareActionProvider != null){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);

            shareIntent.setType("image/*");

            Uri bitmapPath = getBitmapURI(this, drawNodeView.getBitmap());
            shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapPath);
            shareActionProvider.setShareIntent(shareIntent);
        }

        // Return true to display menu
        return true;
    }

    public Uri getBitmapURI(Context c, Bitmap i){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        i.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(c.getContentResolver(),
                i, "SharedBmap", null);

        return Uri.parse(path);
    }
}
