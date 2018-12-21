package com.g45_jones.mobileappsassignment;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.View;
import android.widget.RadioButton;
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
    }

    public void showInput(View view) {
        //Get the value of the checked button.
        boolean checked = ((RadioButton) view).isChecked();
    }

    /*
    This function fetches the data from the company house api.
    The user input is taken from the search widget (STRING) and sent to the api.
    Results are displayed below.
    */
    public void getData(View view){
        //Get the query data from the input.
        final TextInputEditText input = (TextInputEditText)findViewById(R.id.input);
        final TextView mTextView = (TextView)findViewById(R.id.textView3);
        //Create request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.companieshouse.gov.uk/search/companies?q="+"L"; //Search URL

        //Request a response from the URL.
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        //Display the response in the textview
                        try{
                            /*Convert response into a string - this should be temporary!*/
                            JSONObject res = new JSONObject(response);
                            String test = res.getString("kind");
                            mTextView.setText(test);
                        }catch(JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                mTextView.setText("nope");
            }
        })
        {
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
