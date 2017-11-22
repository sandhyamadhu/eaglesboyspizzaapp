package com.appcare.eaglesboys.TnCDesclaimer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Appcare on 01-11-2017.
 */

public class TCDesclaimerFragment extends CommonFragment {

    TextView tnc;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mDesclaimer = inflater.inflate(R.layout.fragment_desclaimer, null);
        new GetOrFetchData().execute();
        initCreateViews(mDesclaimer);
        return mDesclaimer;
    }

    private void initCreateViews(View mDesclaimer) {


        tnc = (TextView) mDesclaimer.findViewById(R.id.txtTC);
    }

    private class GetOrFetchData extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // This is called before sending actual HTTP call...
        }
        @Override
        protected Void doInBackground(String... arg0) {


            String url = "http://marssofttech.com/demos/eaglepizza/api/pages_api/list?pageid=1";
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {

                        JSONObject jsonObject = new JSONObject(s);
                        String terms_condition = jsonObject.getString("terms_condition");
                        tnc.setText(terms_condition);
                        Log.e("hai", terms_condition);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("DATA", "Some error occurred!!");
                }
            });
            RequestQueue rQueue = Volley.newRequestQueue(getContext());
            rQueue.add(request);


            return null;
        }
    }
}