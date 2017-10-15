package com.appcare.eaglesboys.utils;


import com.android.volley.VolleyError;

public interface JSONResponseListener {

    void handleResponse(Object response,String tag);

    void handleError(VolleyError error);



}
