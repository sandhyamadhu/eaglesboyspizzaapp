package com.example.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.appcare.eaglesboys.R;

public class Clickskip extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickskip);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2=(Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array,  R.layout.spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.locality,  R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_layout_item);
        adapter2.setDropDownViewResource(R.layout.spinner_layout_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

    }

    public void showmenu(View view) {
        Intent i=new Intent(Clickskip.this,DupeMenu.class);
        startActivity(i);
    }

      }

