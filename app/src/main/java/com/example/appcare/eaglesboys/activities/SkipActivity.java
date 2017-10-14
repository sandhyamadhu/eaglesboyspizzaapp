package com.example.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.appcare.eaglesboys.Constants.CommonActivity;
import com.example.appcare.eaglesboys.R;
import com.example.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SkipActivity extends CommonActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickskip);

        initSetUpViews();

        HttpHandler.sendRequest("cities_api/cities",mResponseHandler,"CityTab");

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

    List<String> mCityName = new ArrayList<>();
    List<String> mCityIDs = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response,tag);



        try {
            JSONObject jsonObj = new JSONObject(response.toString());
            // Getting JSON Array node
            JSONArray contacts = jsonObj.getJSONArray("contacts");

            for (int i=0; i<contacts.length(); i++) {
                JSONObject jsonObject = contacts.getJSONObject(i);
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

        mSpnrCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String mCitiesID = mCityIDs.get(position);
                HttpHandler.sendRequest("locality_api/localtiy?city_id="+mCitiesID,mResponseHandler,"CityTab");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}

