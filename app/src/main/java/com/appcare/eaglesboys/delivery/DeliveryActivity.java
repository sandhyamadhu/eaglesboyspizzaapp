package com.appcare.eaglesboys.delivery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.appcare.eaglesboys.Constants.CommonActivity;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.location.AppLocationService;
import com.appcare.eaglesboys.location.LocationAddress;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends CommonActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickskip);

        initSetUpViews();

        HttpHandler.sendJSONRequest("cities_api/cities",mResponseHandler,"CityTab");

        initCurrentLocation();

    }

    public void onShowMenu(View view) {
        Intent i=new Intent(DeliveryActivity.this,MenuActivity.class);
        startActivity(i);
    }

    private Spinner mSpnrCity;
    private Spinner mSpnrArea;
    private void initSetUpViews() {
        mSpnrCity = (Spinner) findViewById(R.id.spnrCity);
        mSpnrArea=(Spinner) findViewById(R.id.spnrArea);
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

        ArrayAdapter<String> mSprFilterAdapter = new ArrayAdapter<String>(this,R.layout.spinner_list, mCityName);
        mSprFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnrCity.setAdapter(mSprFilterAdapter);

        mSpnrCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String mCitiesID = mCityIDs.get(position);
                //HttpHandler.sendRequest("locality_api/localtiy?city_id="+mCitiesID,mResponseHandler,"CityTab");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initSetUpLocalViews(List<String> mCityName){

        ArrayAdapter<String> mSprFilterAdapter = new ArrayAdapter<String>(this,R.layout.spinner_list, mCityName);
        mSprFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnrArea.setAdapter(mSprFilterAdapter);

        mSpnrArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String mCitiesID = mCityIDs.get(position);
                //HttpHandler.sendRequest("locality_api/localtiy?city_id="+mCitiesID,mResponseHandler,"CityTab");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            btnSkipLocateMe.setText(locationAddress);
        }
    }

    private Button btnSkipLocateMe;
    AppLocationService appLocationService;
    private void initCurrentLocation() {

        btnSkipLocateMe = (Button)findViewById(R.id.btnSkipLocateMe);
        appLocationService = new AppLocationService(this);

        btnSkipLocateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Location location = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);

                //you can hard-code the lat & long if you have issues with getting it
                //remove the below if-condition and use the following couple of lines
                //double latitude = 37.422005;
                //double longitude = -122.084095

                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                } else {
                    showSettingsAlert();
                }

            }
        });
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
}

