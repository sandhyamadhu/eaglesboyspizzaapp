package com.example.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appcare.eaglesboys.R;

public class CartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        populatelistview();


    }

    private void populatelistview() {
        String[] items1 = {"one", "two", "three", "podlskos","lpelKDOWQK","LWQPOS"};
        ArrayAdapter<String> arrayadapter=new ArrayAdapter<>(this,R.layout.listitems,items1);
        ListView listview=(ListView) findViewById(R.id.list);
        listview.setAdapter(arrayadapter);

    }

    public void havecoupon(View view) {
        EditText coupon=(EditText) findViewById(R.id.coupon);
        coupon.setVisibility(View.VISIBLE);
        Button applycoupon=(Button) findViewById(R.id.apply);
        applycoupon.setVisibility(View.VISIBLE);
    }

    public void placeorder(View view) {
           Intent in=new Intent(this,Payment.class);
        startActivity(in);
    }
}
