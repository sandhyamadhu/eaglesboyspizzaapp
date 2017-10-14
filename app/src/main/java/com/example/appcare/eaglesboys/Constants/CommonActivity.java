package com.example.appcare.eaglesboys.Constants;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.appcare.eaglesboys.utils.EagleResponseListener;
import com.example.appcare.eaglesboys.utils.ResponseHandler;

import java.io.InputStream;

public class CommonActivity extends AppCompatActivity implements EagleResponseListener{

    public ResponseHandler mResponseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResponseHandler = new ResponseHandler(CommonActivity.this, this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    @Override
    public void handleResponse(Object response, String tag) {

    }

    @Override
    public void handleError(int errorCode, String volleyError) {

    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView mEagelImageView;

        public DownloadImageTask(ImageView bmImage) {
            this.mEagelImageView = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String mUrlDisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(mUrlDisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            mEagelImageView.setImageBitmap(result);
        }
    }
}
