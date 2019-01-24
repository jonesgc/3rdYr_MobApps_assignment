package com.g45_jones.mobileappsassignment;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class searchAndResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_results);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getData(query);
        }
        Log.d("Hello","intent");
    }

    /*
    This function fetches the data from the company house api.
    The user input is taken from the search widget (STRING) and sent to the api.
    Results are displayed below.
    */

    public void getData(String query) {
        Log.d("Hello",query);
        //Create request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.companieshouse.gov.uk/search/companies?q=" + "L"; //Search URL

        //Request a response from the URL.
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Display the response in the textview
                        try {
                            JSONObject res = new JSONObject(response);
                            String test = res.getString("kind");
                            Log.d("Hello",test);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Hello","Error");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization", "rCg4O9MO8Pv8uluvHRknYxcwWnXSnC3d2ST5wYbD");
                return headers;
            }
        };

        queue.add(req);
    }

}
