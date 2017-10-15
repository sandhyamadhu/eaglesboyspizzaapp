package com.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.menuotp.MainActivity;

public class
Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    }

//    public void ok(View view) {
////        EditText name=(EditText) findViewById(R.id.name);
////        EditText number=(EditText) findViewById(R.id.num);
////        EditText address=(EditText) findViewById(R.id.addresss);
////        if(name.length()==0|| number.length()==0||address.length()==0)
////        {
////            Toast.makeText(getApplicationContext()," Please Enter your details",Toast.LENGTH_SHORT).show();
////        }
////        else
////        {
////            Toast.makeText(getApplicationContext()," Your Profile has been Updated Successfully",Toast.LENGTH_SHORT).show();
////        }
////
////    }

    public void logout(View view) {
        Intent i=new Intent(Profile.this,MainActivity.class);
        startActivity(i);
    }
}
