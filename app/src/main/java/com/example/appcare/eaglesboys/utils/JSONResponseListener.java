package com.example.appcare.eaglesboys.utils;


import com.android.volley.VolleyError;

import org.json.JSONArray;

public interface JSONResponseListener {

    void handleResponse(JSONArray response);

    void handleError(VolleyError error);
}
