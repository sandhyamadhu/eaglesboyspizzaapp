package com.appcare.eaglesboys.cities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.appcare.eaglesboys.Constants.CommonActivity;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.activities.DupeMenu;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.appcare.eaglesboys.utils.HttpHandler;

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

        HttpHandler.sendJSONRequest("cities_api/cities",mResponseHandler,"CityTab");

    }

    public void onShowMenu(View view) {
        Intent i=new Intent(SkipActivity.this,MenuActivity.class);
        startActivity(i);
    }

    private Spinner mSpnrCity;
    private Spinner mSpnrArea;
    private void initSetUpViews() {
        mSpnrCity = (Spinner) findViewById(R.id.spnrCity);
        Spinner spinner2=(Spinner) findViewById(R.id.spnrArea);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.locality,  android.R.layout.simple_list_item_1);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
    }

    List<String> mCityName = new ArrayList<>();
    List<String> mCityIDs = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response,tag);

        try {
            JSONArray jsonObj = new JSONArray(response.toString());
            for (int i=0; i<jsonObj.length(); i++) {
                JSONObject jsonObject = jsonObj.getJSONObject(i);
                String cityID = jsonObject.getString("id");
                String cityName = jsonObject.getString("name");
                mCityIDs.add(cityID);
                mCityName.add(cityName);
                initSetUpCityViews(mCityName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void initSetUpCityViews(List<String> mCityName){

        ArrayAdapter<String> mSprFilterAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mCityName);
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

