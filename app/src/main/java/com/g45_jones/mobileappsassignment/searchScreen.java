package com.g45_jones.mobileappsassignment;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class searchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("Hello","Sumbmitted");
                //Intent searchAndResults = new Intent(getApplicationContext(), searchAndResults.class);
                Intent searchAndResults = new Intent(Intent.ACTION_SEARCH);
                startActivity(searchAndResults);
                return false;
            }
        });
    }

    public void showInput(View view) {
        //Get the value of the checked button.
        boolean checked = ((RadioButton) view).isChecked();
    }

}
