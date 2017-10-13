package com.example.appcare.eaglesboys.utils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

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

    public static void postNewCommen(String urllocality, final JSONResponseListener mResponseHandler, String localityTab) {
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, urllocality,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mResponseHandler.handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mResponseHandler.handleError(error);
            }

        });
        AppController.getInstance().addToRequestQueue(getRequest);

    }
}

