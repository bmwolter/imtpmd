package com.example.fridget;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NetworkManager {
    //Singleton
    private static final String TAG = "NetworkManager";
    private static NetworkManager instance = null;

    public RequestQueue requestQueue;

    private NetworkManager(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized NetworkManager getInstance(Context context){
        if(instance == null){
            instance = new NetworkManager(context);
        }
        return instance;
    }
    public static synchronized NetworkManager getInstance(){
        if(instance == null){
            throw new IllegalStateException("Hij bestaat niet, eerst get instance met context aanroepen");
        }
        return instance;
    }
    public void getRequest(String prefixUrl, final VolleyCallBack volleyCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, prefixUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallBack.onSucces(response);
            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d(TAG,"oeps" );
            }
        });
        requestQueue.add(stringRequest);
    }



    }

