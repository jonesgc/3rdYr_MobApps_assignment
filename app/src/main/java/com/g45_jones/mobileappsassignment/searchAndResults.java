package com.g45_jones.mobileappsassignment;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class searchAndResults extends AppCompatActivity {
    private ArrayList<String> companyTitles = new ArrayList<>();
    private ArrayList<String> companyNumbers = new ArrayList<>();
    private ArrayList<String> companyItems = new ArrayList<>();
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_results);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Get the contents of the bundle sent from searchScreen
        Bundle query = getIntent().getExtras();
        if(query != null)
        {
            String searchQuery = query.getString("queryString");
            if(searchQuery != null)
            {
                Log.d("Hello",searchQuery);
                getTitles(searchQuery);
            }
            else
            {
                Log.d("Hello","NUll");
            }
        }
    }


    /*
    This function fetches the data from the company house api.
    The user input is taken from the search widget (STRING) and sent to the api.
    Results are displayed below.
    */
    //
    public void getTitles(String query) {

        String url = "https://api.companieshouse.gov.uk/search/companies?q=" + query; //Search URL

        //Request a response from the URL.
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Display the response in the textview
                        try {
                            JSONObject res = new JSONObject(response);

                            //Extract all of the names of campanies
                            int arraylen = res.getJSONArray("items")
                                    .getJSONObject(0).length();
                            for(int i =0; i < arraylen; i++) {
                                String companyName = res.getJSONArray("items")
                                        .getJSONObject(i)
                                        .getString("title");
                                String coNumber = res.getJSONArray("items")
                                        .getJSONObject(i)
                                        .getString("company_number");
                                Log.d("Hello", companyName);
                                String coItems = res.getJSONArray("items").toString();
                                addIdentification(companyName, coNumber, coItems);
                            }
                            initRecyclerView();
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

        RequestQueue queue = requestQueueSingleton.
                getInstance(this.getApplicationContext()).getRequestQueue();

        requestQueueSingleton.getInstance(this).addToRequestQueue(req);
    }

    public void getRelatedData(String companyName ,String companyNumber, String cItems){
        String officers = "https://api.companieshouse.gov.uk/company/"+ companyNumber +"/officers";
        bundle.putString("Company Name", companyName);
        bundle.putString("Company Number", companyNumber);
        bundle.putString("Company Items", cItems);
        //Get the officers for the input company
        StringRequest req = new StringRequest(Request.Method.GET, officers,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Display the response in the textview
                        try {
                            JSONObject res = new JSONObject(response);
                            Log.d("Hello", "Query sent");
                            Log.d("Hello", res.toString());
                            JSONArray items = res.getJSONArray("items");
                            int active = res.getInt("active_count");
                            if(active == 0){
                                items = null;
                            }
                            Log.d("Hello", "Items" + items);
                            packageAndIntent(items);
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

        RequestQueue queue = requestQueueSingleton.
                getInstance(this.getApplicationContext()).getRequestQueue();

        requestQueueSingleton.getInstance(this).addToRequestQueue(req);
    }

    //Creation of recycler view was adapted from the following video tutorial
    //because the android documentation is not very helpful on recycler views.
    //URL: https://www.youtube.com/watch?v=Vyqz_-sJGFk
    private void initRecyclerView()
    {
        Log.d("Hello", "initRecyclerView");
        RecyclerView recycler =  findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recyclerViewAdapter adapter = new recyclerViewAdapter(companyNumbers,companyTitles,
                companyItems,this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    //Inputs the company names intro the array so they can be displayed in the recycler view
    private void addIdentification(String string, String coNumb, String coItems)
    {
        Log.d("Hello","Added" + string + "to titles");
        companyTitles.add(string);
        companyNumbers.add(coNumb);
        companyItems.add(coItems);
    }

    //Packs the relevant data to the user input select then send it to the drawAndDisplay activity
    private void packageAndIntent(JSONArray jArr){
        Log.d("Hello", "packageAndIntent: starting new activity");
        //Need to collect the data to be used in the creation of the node diagram
        if(jArr != null){
            bundle.putString("items",jArr.toString());
            Intent drawAndDisplay = new Intent(this, drawAndDisplay.class);
            drawAndDisplay.putExtras(bundle);
            this.startActivity(drawAndDisplay);
        }
        else{
            Log.d("Hello", "NO ACTIVE OFFICERS");
            Toast.makeText(this, "No officers for that company", Toast.LENGTH_SHORT).show();
        }
    }

    //Functions used for the back button.
    public boolean onOptionsItemSelected(MenuItem item){
        Log.d("Hello", "onOptionsItemSelected:"+ item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(){
        return true;
    }
}
