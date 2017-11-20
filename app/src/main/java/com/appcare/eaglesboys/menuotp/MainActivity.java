package com.appcare.eaglesboys.menuotp;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.activities.AddAddress;
import com.appcare.eaglesboys.constants.CommonActivity;
import com.appcare.eaglesboys.delivery.DeliveryActivity;
import com.appcare.eaglesboys.location.MapsActivity;
import com.appcare.eaglesboys.utils.HttpHandler;

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
    private AlertDialog mMobileNumberDialog;
    private void initEnterMobileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View mOTPViews = inflater.inflate(R.layout.request_otp,null);

        builder.setView(mOTPViews);
        mMobileNumberDialog = builder.create();
        mMobileNumberDialog.getWindow().setBackgroundDrawableResource(R.color.transparentColor);
        mMobileNumberDialog.show();

        ImageView mImgNoClose = (ImageView)mOTPViews.findViewById(R.id.imgNoClose);
        mImgNoClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMobileNumberDialog.dismiss();
            }
        });

        final EditText mEdtPhoneNumber = (EditText)mOTPViews.findViewById(R.id.edtPhoneNumber);
        Button btnSubmit = (Button)mOTPViews.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMobileNumber = mEdtPhoneNumber.getText().toString();
                if(!"".equals(mMobileNumber)){
                    HttpHandler.sendRequest("auth_api/send_otp?phone="+mMobileNumber,mResponseHandler,"MobileNumber");
                    mMobileNumberDialog.dismiss();
                }else{
                    Toast.makeText(getApplication(),"Please Enter the Mobile Number.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private AlertDialog mOTPDialog;
    private void initOTPDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.enter_otp,null);

        builder.setView(dialogView);
        mOTPDialog = builder.create();
        mOTPDialog.getWindow().setBackgroundDrawableResource(R.color.transparentColor);
        mOTPDialog.show();

        TextView txt = (TextView)dialogView.findViewById(R.id.txtResendOTP);
        txt.setPaintFlags(txt.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        ImageView mImgOTPClose = (ImageView)dialogView.findViewById(R.id.imgOTPClose);
        mImgOTPClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOTPDialog.dismiss();
            }
        });

        final EditText mEdtEnterOTP = (EditText)dialogView.findViewById(R.id.edtEnterOTP);

        Button mVerifyButton = (Button)dialogView.findViewById(R.id.btnVerify);
        mVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!"".equals(mEdtEnterOTP.getText().toString())){

                    String mPoneURL = "auth_api/login?phone="+mMobileNumber+"&otp="+mEdtEnterOTP.getText().toString();
                    HttpHandler.sendRequest("auth_api/send_otp?phone="+mPoneURL,mResponseHandler,"OTP");
                    mMobileNumber = "";
                    mOTPDialog.dismiss();
                }else{
                    Toast.makeText(getApplication(),"Please Enter OTP.",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public void handleResponse(Object response,String tag) {
        super.handleResponse(response,tag);

        try {
            if(tag.equals("MobileNumber")){
                if ("true".equals(((JSONObject) response).getString("status"))) {
                    mMobileNumberDialog.dismiss();
                    initOTPDialog();
                    Toast.makeText(getApplication(), ((JSONObject) response).getString("message"), Toast.LENGTH_SHORT).show();
                } else if ("false".equals(((JSONObject) response).getString("status"))) {
                    initEnterMobileDialog();
                    Toast.makeText(getApplication(), ((JSONObject) response).getString("error"), Toast.LENGTH_SHORT).show();
                }
            }else if(tag.equals("OTP")){
                mOTPDialog.dismiss();
                if ("true".equals(((JSONObject) response).getString("status"))) {
                    Toast.makeText(getApplication(), ((JSONObject) response).getString("message"), Toast.LENGTH_SHORT).show();
                } else if ("false".equals(((JSONObject) response).getString("status"))) {
                    initEnterMobileDialog();
                }

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


    public void skip(View view) {
        Intent i=new Intent(MainActivity.this,DeliveryActivity.class);
        startActivity(i);
    }

    public void locateMe(View view){
        Intent i=new Intent (MainActivity.this, MapsActivity.class);
        startActivity (i);

    }


    public void addNewAddress(View view) {
        Intent i = new Intent (MainActivity.this, AddAddress.class);
        startActivity (i);
    }
}
