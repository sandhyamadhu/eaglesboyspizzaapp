package com.example.appcare.eaglesboys.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appcare.eaglesboys.R;

public class CartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void havecoupon(View view) {
        EditText coupon=(EditText) findViewById(R.id.coupon);
        coupon.setVisibility(View.VISIBLE);
        Button applycoupon=(Button) findViewById(R.id.apply);
        applycoupon.setVisibility(View.VISIBLE);
    }
}
