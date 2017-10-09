package com.example.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.appcare.eaglesboys.R;
import com.example.appcare.eaglesboys.praser.HttpHandler;

public class Clickskip extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickskip);

        initSetUpViews();

        String url = "http://marssofttech.com/demos/eaglepizza/api/cities_api/cities";
        HttpHandler sh = new HttpHandler();
        String jsonStr = sh.makeServiceCall(url);

        System.out.println("EAGLES APP::>"+jsonStr);

    }

    private void initSetUpViews() {

        Spinner spinner = (Spinner) findViewById(R.id.spnrCity);
        Spinner spinner2=(Spinner) findViewById(R.id.spnrArea);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array,  R.layout.spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.locality,  R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_layout_item);
        adapter2.setDropDownViewResource(R.layout.spinner_layout_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
    }

    public void onShowMenu(View view) {
        Intent i=new Intent(Clickskip.this,DupeMenu.class);
        startActivity(i);
    }

}

