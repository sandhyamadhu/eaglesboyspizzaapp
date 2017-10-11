package com.example.appcare.eaglesboys.utils;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class HttpHandler {

    public static void postNewComment(String mServiceURL,final JSONResponseListener mJsonResponseListener, String Tag ){

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, mServiceURL,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mJsonResponseListener.handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mJsonResponseListener.handleError(error);
            }

        });
        AppController.getInstance().addToRequestQueue(getRequest);

    }
}
