package com.g45_jones.mobileappsassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
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
                Log.d("Hello", stringItems);
                try{
                    items = new JSONObject(stringItems);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                //Its the company node.
                if(items.has("company_number")){
                    infoView.setText(getCompanyInfo(stringItems));
                }else{
                    infoView.setText(getOfficerInfo(stringItems));
                }
            }else{
                Log.d("Hello", "Items is null");
            }
        }
    }

    String getCompanyInfo(String ci){
        String info = "";
        Log.d("Hello", "getCompanyInfo: "+ ci);
        try{
            JSONObject i = new JSONObject(ci);
            if(i.has("company_status")){
                info += "Company Status" + i.getJSONObject("items").getString("company_status")+"\n";
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return info;
    }

    String getOfficerInfo(String oI){
        String officerInfo = "";
        try{
            //Add the various items to a string that will then be put into a text view.
            //Due to this most of the formatting is done inside the string.
            //THis could be replaced by programmatically generating the text views for each item.
            items = new JSONObject(oI);

            if(items.has("date_of_birth")){
                officerInfo += "Date of birth: " + items.getJSONObject("date_of_birth").
                        getInt("month") + "/" + items.getJSONObject("date_of_birth")
                        .getInt("year")+"\n";
            }

            if(items.has("appointed_on")){
                officerInfo += "Officer appointed on: " +
                        items.getString("appointed_on") + "\n";
            }

            if(items.has("country_of_residence")){
                officerInfo += "Officer country of residence: " +
                        items.getString("country_of_residence") + "\n";
            }
            if(items.has("officer_role")){
                officerInfo += "Officer role: " +
                        items.getString("officer_role") + "\n";
            }
            if(items.has("occupation")){
                officerInfo += "Officer occupation: " +
                        items.getString("occupation") + "\n";
            }
            if(items.has("active_count")){
                officerInfo += "Officer active in: " +
                        items.getString("active_count") + "comapnies" + "\n";
            }
            if(items.has("resigned_count")){
                officerInfo += "Officer resigned from: " +
                        items.getString("resigned_count") + "comapnies" + "\n";
            }

            //Address is an important entry since it can be used for the google maps integration.
            if(items.has("address_snippet")){
                officerInfo += "Address: " +
                        items.getString("address_snippet") + "\n";
            }else if(items.has("address")){
                officerInfo += "Address: " +
                        items.getJSONObject("address").getString("premises") + ", " +
                        items.getJSONObject("address").getString("address_line_1") + ", " +
                        items.getJSONObject("address").getString("region") + ", " +
                        items.getJSONObject("address").getString("locality") + ", " +
                        items.getJSONObject("address").getString("country") + "\n";
            }


        }catch(JSONException e){
            e.printStackTrace();
        }

        return officerInfo;
    }

}
