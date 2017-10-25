package com.appcare.eaglesboys.utils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.appcare.eaglesboys.utils.JSONResponseListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class HttpHandler {

    public static void sendRequest(String mServiceURL,final JSONResponseListener mJsonResponseListener,final String mTag ){

        String mServiceURLString = "http://marssofttech.com/demos/eaglepizza/api/"+mServiceURL;

        System.out.println("Nikhil::>"+mServiceURLString);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, mServiceURLString,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("Nikhil 111::>"+response +"mTag 111::>"+mTag);
                mJsonResponseListener.handleResponse(response,mTag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Nikhil Error::>"+error);
                //mJsonResponseListener.handleError(error);
            }

        });
        AppController.getInstance().addToRequestQueue(getRequest);

    }

    public static void sendJSONRequest(String mServiceURL,final JSONResponseListener mJsonResponseListener,final String mTag ){

        String mServiceURLString = "http://marssofttech.com/demos/eaglepizza/api/"+mServiceURL;

        System.out.println("Nikhil::>"+mServiceURLString);

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, mServiceURLString,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                System.out.println("Nikhil 111::>"+response +"mTag 111::>"+mTag);
                mJsonResponseListener.handleResponse(response,mTag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Nikhil Error::>"+error);
                //mJsonResponseListener.handleError(error);
            }

        });
        AppController.getInstance().addToRequestQueue(getRequest);

    }
}

