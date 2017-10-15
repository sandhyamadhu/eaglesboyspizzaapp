package com.appcare.eaglesboys.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.menuotp.MainActivity;

/**
 * Created by Appcare on 01-09-2017.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}