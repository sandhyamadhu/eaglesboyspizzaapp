package com.appcare.eaglesboys.delivery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonActivity;
import com.appcare.eaglesboys.location.AppLocationService;
import com.appcare.eaglesboys.location.LocationAddress;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends CommonActivity implements View.OnClickListener {

    String name = "chennai";
    String store;
    List<String> mLocalityName = new ArrayList<>();
    List<String> mStoreNameList = new ArrayList<>();
    List<String> mStoreIDList = new ArrayList<>();
    List<String> mCityName = new ArrayList<>();
    List<String> mCityIDs = new ArrayList<>();
    List<String> mLocalityIDs = new ArrayList<>();

    String str_cityId="", str_location_id="", str_branchid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickskip);

        initSetUpViews();
        HttpHandler.sendJSONRequest("cities_api/cities", mResponseHandler, "CityTab");
        initCurrentLocation();

    }

    private EditText mSpnrCity, mStore;
    private EditText mSpnrArea;
    private Button btnShow;

    private void initSetUpViews() {
        mSpnrCity = (EditText) findViewById(R.id.city);
        mSpnrArea = (EditText) findViewById(R.id.locality);
        mStore = (EditText) findViewById(R.id.branch);
        btnShow = (Button) findViewById(R.id.btnShow);
        mSpnrCity.setOnClickListener(this);
        mSpnrArea.setOnClickListener(this);
        mStore.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }


    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);

        try {
            JSONArray jsonObj = new JSONArray(response.toString());
            for (int i = 0; i < jsonObj.length(); i++) {

                JSONObject jsonObject = jsonObj.getJSONObject(i);
                String cityID = jsonObject.getString("id");
                String cityName = jsonObject.getString("name");
                mCityIDs.add(cityID);
                mCityName.add(cityName);

            }
            //initSetUpCityViews(mCityName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initSetUpCityViews(final List<String> mCityName) {
        mSpnrCity.setText("");
        str_cityId = "";
        if (mCityName.isEmpty()) {

            new AlertDialog.Builder(this).setMessage("it empty").show();
        } else {
            // TODO Auto-generated method stub
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Selcet the City");
            dialog.setContentView(R.layout.city_dailoglayout);
            ListView listview = (ListView) dialog.findViewById(R.id.listview);
            ArrayAdapter<String> data = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mCityName);
            listview.setAdapter(data);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    // TODO Auto-generated method stub

                    dialog.dismiss();
                    name = mCityName.get(arg2);
                    str_cityId = mCityIDs.get(arg2);
                    mSpnrCity.setText(name);

                    new GetOrFetchData().execute(name);
                    mSpnrArea.setFocusableInTouchMode(true);
                    mSpnrArea.requestFocus();
                }
            });

            dialog.show();

        }
    }

    private void initSetUpLocalViews(final List<String> mLocatitiesName) {
        mSpnrArea.setText("");
        str_location_id = "";
        if (mLocatitiesName.isEmpty()) {

            new AlertDialog.Builder(this).setMessage("please Select the city").setPositiveButton("Ok", null).show();

        } else {
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Selcet the Area");
            dialog.setContentView(R.layout.city_dailoglayout);
            ListView listview = (ListView) dialog.findViewById(R.id.listview);
            ArrayAdapter<String> data = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mLocatitiesName);
            listview.setAdapter(data);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    // TODO Auto-generated method stub
                    store = mLocatitiesName.get(arg2);
                    str_location_id = mLocalityIDs.get(arg2);
                    mSpnrArea.setText(store);

                    dialog.dismiss();
                    new GetOrFetchStore().execute(store);
                    mStore.setFocusableInTouchMode(true);
                    mStore.requestFocus();
                }
            });

            dialog.show();
        }
    }

    private void initSetUpStorsViews(final List<String> mStoreName) {

        mStore.setText("");
        str_branchid = "";
        if (mStoreName.isEmpty()) {

            new AlertDialog.Builder(this).setMessage("Please select Location").setPositiveButton("Ok", null).show();
        } else {
            // TODO Auto-generated method stub
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Selcet the Branch");
            dialog.setContentView(R.layout.city_dailoglayout);
            ListView listview = (ListView) dialog.findViewById(R.id.listview);
            ArrayAdapter<String> data = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
                    mStoreName);
            listview.setAdapter(data);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    // TODO Auto-generated method stub
                    String mStorename = mStoreName.get(position);
                    str_branchid = mStoreIDList.get(position);
                    mStore.setText(mStorename);
                    dialog.dismiss();

                }
            });

            dialog.show();

        }
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

        btnSkipLocateMe = (Button) findViewById(R.id.btnSkipLocateMe);
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

    /**
     * Async Task to make HTTP calls.
     */


    private class GetOrFetchData extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // This is called before sending actual HTTP call...
        }

        @Override
        protected Void doInBackground(String... arg0) {
            final String identifier = arg0[0];

            String url = "http://marssofttech.com/demos/eaglepizza/api/locality_api/locality";
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        mLocalityName.clear();
                        mLocalityIDs.clear();

                        JSONObject result = new JSONObject(s);
                        JSONArray jsonArray = result.getJSONArray(identifier);
                        Log.e("hai", identifier);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String localityName = jsonObject.getString("locality_name");
                            String localityID = jsonObject.getString("locality_id");
                            mLocalityIDs.add(localityID);
                            mLocalityName.add(localityName);


                            Log.e("hai", mLocalityName.toString());
                        }
                        initSetUpLocalViews(mLocalityName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("DATA", "Some error occurred!!");
                }
            });


            RequestQueue rQueue = Volley.newRequestQueue(DeliveryActivity.this);
            rQueue.add(request);


            return null;
        }


    }


    private class GetOrFetchStore extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // This is called before sending actual HTTP call...
        }

        @Override
        protected Void doInBackground(String... arg0) {
            final String identifier = arg0[0];

            String url = "http://marssofttech.com/demos/eaglepizza/api/store_api/list";
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        mStoreNameList.clear();
                        mStoreIDList.clear();
                        JSONObject result = new JSONObject(s);
                        JSONArray jsonArray = result.getJSONArray(identifier);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String mStoreName = jsonObject.getString("store_name");
                            String mStoreId = jsonObject.getString("store_id");
                            mStoreIDList.add(mStoreId);
                            mStoreNameList.add(mStoreName);
                        }
                        initSetUpStorsViews(mStoreNameList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("DATA", "Some error occurred!!");
                }
            });


            RequestQueue rQueue = Volley.newRequestQueue(DeliveryActivity.this);
            rQueue.add(request);


            return null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.city:
                initSetUpCityViews(mCityName);
                break;
            case R.id.locality:
                initSetUpLocalViews(mLocalityName);
                break;
            case R.id.branch:
                initSetUpStorsViews(mStoreNameList);
                break;
            case R.id.btnShow:
                validation();
                break;

        }

    }


    private void validation() {

        if (str_cityId.isEmpty()) {
            new AlertDialog.Builder(this).setMessage("Enter the CityName ").setNegativeButton("Ok", null).show();
            // mSpnrCity.setError("Please Select the City");

            return;
        } else if (str_location_id.isEmpty()) {
            mSpnrArea.setError("Please Select the Location");
            return;
        } else if (str_branchid.isEmpty()) {
            mSpnrArea.setError("Please Select the Barnch");
            return;
        } else {
            Intent i = new Intent(DeliveryActivity.this, MenuActivity.class);
            startActivity(i);
            return;
        }
    }
}
