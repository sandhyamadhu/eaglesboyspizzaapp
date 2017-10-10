package com.example.appcare.eaglesboys.Constants;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import com.example.appcare.eaglesboys.utils.EagleResponseListener;
import com.example.appcare.eaglesboys.utils.ResponseHandler;

import org.json.JSONArray;

public class CommonActivity extends AppCompatActivity implements EagleResponseListener{

    public ResponseHandler mResponseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResponseHandler = new ResponseHandler(CommonActivity.this, this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    @Override
    public void handleResponse(JSONArray response) {

    }

    @Override
    public void handleError(int errorCode, String volleyError) {

    }
}
