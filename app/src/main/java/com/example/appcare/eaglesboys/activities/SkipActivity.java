package com.example.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.appcare.eaglesboys.Constants.CommonActivity;
import com.example.appcare.eaglesboys.R;
import com.example.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SkipActivity extends CommonActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickskip);

        initSetUpViews();

        String url = "http://marssofttech.com/demos/eaglepizza/api/cities_api/cities";
        HttpHandler.postNewComment(url,mResponseHandler,"CityTab");

    }

    private Spinner mSpnrCity;
    private void initSetUpViews() {
        mSpnrCity = (Spinner) findViewById(R.id.spnrCity);
        Spinner spinner2=(Spinner) findViewById(R.id.spnrArea);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.locality,  R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_layout_item);
        spinner2.setAdapter(adapter2);
    }

    public void onShowMenu(View view) {
        Intent i=new Intent(SkipActivity.this,DupeMenu.class);
        startActivity(i);
    }

    @Override
    public void handleResponse(JSONArray response) {
        super.handleResponse(response);

        List<String> mCityName = new ArrayList<>();
        List<String> mCityIDs = new ArrayList<>();

        try {
            for (int i=0; i<response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                String cityID = jsonObject.getString("id");
                String cityName = jsonObject.getString("name");
                mCityIDs.add(cityID);
                mCityName.add(cityName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initSetUpCityViews(mCityName);
    }

    private void initSetUpCityViews(List<String> mCityName){

        ArrayAdapter<String> mSprFilterAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, mCityName);
        mSprFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnrCity.setAdapter(mSprFilterAdapter);

    }
}

