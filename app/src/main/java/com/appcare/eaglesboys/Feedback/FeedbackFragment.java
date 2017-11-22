package com.appcare.eaglesboys.Feedback;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.menu.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Appcare on 01-11-2017.
 */

public class FeedbackFragment extends CommonFragment {
    Button mSubmit;
    EditText mtext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mFeed = inflater.inflate(R.layout.fragment_feedback, null);
        initviews(mFeed);

        return mFeed;
    }

    private void initviews(View mviews) {
        mSubmit = (Button) mviews.findViewById(R.id.submit);
        mtext = (EditText) mviews.findViewById(R.id.text);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isEmpty(mtext)){

                    new AlertDialog.Builder(getContext()).setMessage("Please Type Somthing") .setPositiveButton("OK", null).show();

                }else{
                    String mtextText= mtext.getText().toString();
                    new GetOrFetchData().execute(mtextText);

                }

            }
        });

    }
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public class GetOrFetchData extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // This is called before sending actual HTTP call...
        }

        @Override
        protected Void doInBackground(String... arg0) {

            final String identifier = arg0[0];
            String url = "http://marssofttech.com/demos/eaglepizza/api/userfb_api/add_feedback";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,

                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            // response

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String message = jsonObject.getString("message");
                                new AlertDialog.Builder(getContext()).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        addFragment(R.id.fragmentContent,new HomeFragment(),false,true);
                                    }
                                }).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.toString());
                        }
                    }
            ) {
                @Override

                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", "5");
                    params.put("feedback", identifier);

                    return params;
                }
            };
            RequestQueue rQueue = Volley.newRequestQueue(getContext());
            rQueue.add(postRequest);


            return null;
        }
    }
}