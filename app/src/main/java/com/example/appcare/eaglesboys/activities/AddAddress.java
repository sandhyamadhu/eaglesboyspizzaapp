package com.example.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.appcare.eaglesboys.R;

public class AddAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
    }

    public void saveandcontinue(View view) {
        Intent i=new Intent(AddAddress.this,Clickskip.class);
        startActivity(i);
    }


}
