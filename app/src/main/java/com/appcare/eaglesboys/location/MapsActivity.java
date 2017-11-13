package com.appcare.eaglesboys.location;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.activities.AddAddress;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private  String addressGot;
    private  PlaceAutocompleteFragment places;
    private Geocoder geocoder;
    List<Address> address;
    private static GoogleMap mMap;
    private TrackGPS gps;
    Double lat, lng;
    private String placeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        initResizeMyLocBtn(mapFragment);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this, Locale.getDefault());

    }

    private void initResizeMyLocBtn(SupportMapFragment mapFragment) {
        View mapView = mapFragment.getView();
        if (mapView != null &&
                mapView.findViewById(1) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(1).getParent()).findViewById(2);
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 30, 30);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        gps = new TrackGPS(MapsActivity.this);
        lat = gps.getLatitude();
        lng = gps.getLongitude();
        LatLng latLng = new LatLng(lat, lng);
        float zoomLevel = 14.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
        initSetAutoSearch();
        try {
            address = geocoder.getFromLocation(lat, lng, 1);
            addressGot = address.get(0).getAddressLine(0);
            places.setHint(addressGot);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        mMap.setMyLocationEnabled(true);



    }

    private void initSetAutoSearch() {
        places = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        places.setOnPlaceSelectedListener(new PlaceSelectionListener () {
            @Override
            public void onPlaceSelected(Place place) {

                Toast.makeText(getApplicationContext(), place.getName(), Toast.LENGTH_SHORT).show();
                placeName= (String) place.getName();
                dowhatiwant(placeName);

            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(), status.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_GEOCODE)
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT)
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_REGIONS)
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                .build();


        places.setFilter(typeFilter);
    }

    private void dowhatiwant(String searchString) {
        List<Address> addressList;
        LatLng coord = null;



        try {
            addressList = geocoder.getFromLocationName(searchString, 5);
            if (addressList != null) {

                Address location = addressList.get(0);

                coord = new LatLng(location.getLatitude(), location.getLongitude());
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        MarkerOptions mMarkerOptions = new MarkerOptions();
        mMarkerOptions.position(coord);

        mMap.addMarker(mMarkerOptions).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coord));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 20.0f));


    }
    public  void addThisLocation(View view)
    {
        Toast.makeText (getApplicationContext (),"Your address has been saved",Toast.LENGTH_SHORT).show ();
    }
    public void addNewLocation(View view)
    {
        Intent i=new Intent (MapsActivity.this, AddAddress.class);
        startActivity (i);
    }
}