package com.example.appcare.eaglesboys.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcare.eaglesboys.Constants.CommonActivity;
import com.example.appcare.eaglesboys.R;
import com.example.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initEnterMobileDialog();

    }

    private String mMobileNumber = "";
    private void initEnterMobileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View mOTPViews = inflater.inflate(R.layout.request_otp,null);

        builder.setView(mOTPViews);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.windowColor);
        dialog.show();


        final EditText mEdtPhoneNumber = (EditText)mOTPViews.findViewById(R.id.edtPhoneNumber);
        Button btnSubmit = (Button)mOTPViews.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMobileNumber = mEdtPhoneNumber.getText().toString();
                if(!"".equals(mMobileNumber)){
                    HttpHandler.sendRequest("auth_api/send_otp?phone="+mMobileNumber,mResponseHandler,"MobileNumber");
                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplication(),"Please Enter the Mobile Number.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initOTPDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.enter_otp,null);

        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.windowColor);
        dialog.show();

        TextView txt = (TextView)dialogView.findViewById(R.id.txtResendOTP);
        txt.setPaintFlags(txt.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        final EditText mEdtEnterOTP = (EditText)dialogView.findViewById(R.id.edtEnterOTP);

        Button mVerifyButton = (Button)dialogView.findViewById(R.id.btnVerify);
        mVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!"".equals(mEdtEnterOTP.getText().toString())){

                    String mPoneURL = "auth_api/login?phone="+mMobileNumber+"&otp="+mEdtEnterOTP.getText().toString();
                    HttpHandler.sendRequest("auth_api/send_otp?phone="+mPoneURL,mResponseHandler,"OTP");
                    mMobileNumber = "";
                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplication(),"Please Enter OTP.",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public void handleResponse(Object response,String tag) {
        super.handleResponse(response,tag);

        System.out.println("Nikhil 2222::>"+response+ "tag::>"+tag);

        try {
            if(tag.equals("OTP")){
                System.out.println("Nikhil OTP::>"+response);
                if ("true".equals(((JSONObject) response).getString("status"))) {
                    Toast.makeText(getApplication(), ((JSONObject) response).getString("message"), Toast.LENGTH_SHORT).show();
                } else if ("false".equals(((JSONObject) response).getString("status"))) {
                    Toast.makeText(getApplication(), ((JSONObject) response).getString("error"), Toast.LENGTH_SHORT).show();
                }
            }else if(tag.equals("MobileNumber")){
                System.out.println("Nikhil MobileNumber::>"+response);

                initOTPDialog();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleError(int errorCode, String volleyError) {
        super.handleError(errorCode, volleyError);
        mMobileNumber = "";
    }

    public void addNewAddress(View view) {
        Intent i=new Intent(MainActivity.this,AddAddress.class);
        startActivity(i);
    }

    public void skip(View view) {
        Intent i=new Intent(MainActivity.this,SkipActivity.class);
        startActivity(i);
    }
}
