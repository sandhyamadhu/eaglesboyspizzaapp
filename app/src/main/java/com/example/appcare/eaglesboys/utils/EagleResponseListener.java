package com.example.appcare.eaglesboys.utils;

import org.json.JSONArray;

public interface EagleResponseListener {

    void handleResponse(JSONArray response);

    void handleError(int errorCode, String volleyError);
}
