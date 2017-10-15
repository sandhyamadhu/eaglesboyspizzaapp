package com.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.cities.SkipActivity;

public class AddAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
    }

    public void saveAndContinue(View view) {
        Intent i=new Intent(AddAddress.this,SkipActivity.class);
        startActivity(i);
    }



}
