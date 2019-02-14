package com.g45_jones.mobileappsassignment;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


// Code adapted from https://developer.android.com/training/volley/requestqueue

public class requestQueueSingleton {
    private static requestQueueSingleton instance;
    private RequestQueue requestQueue;
    private static Context ctext;

    private requestQueueSingleton(Context context) {
        ctext = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized requestQueueSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new requestQueueSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
