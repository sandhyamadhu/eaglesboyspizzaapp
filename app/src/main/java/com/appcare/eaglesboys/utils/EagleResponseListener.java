package com.appcare.eaglesboys.utils;

public interface EagleResponseListener {

    void handleResponse(Object response, String tag);

    void handleError(int errorCode, String volleyError);
}
