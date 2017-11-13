package com.appcare.eaglesboys.deserts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DessertsFragment extends CommonFragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mDessertsView = inflater.inflate(R.layout.fragment_deserts,null);

        initDesertsView(mDessertsView);
        return mDessertsView;
    }

    private ListView mDesertsList;
    private void initDesertsView(View mDessertsView) {
        mDesertsList = (ListView)mDessertsView.findViewById(R.id.desertsList);
        HttpHandler.sendRequest("foods_api/beverage",mResponseHandler,"beverages");

    }

    ArrayList<DesertsDetails> mDesertsDetails = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);
        mDesertsDetails.clear();

        try {
            JSONObject mJSObject = new JSONObject(response.toString());
            JSONArray jsonarray= mJSObject.getJSONArray("beverages");
            for (int i=0;i<jsonarray.length();i++){

                DesertsDetails mDetails = new DesertsDetails();


                String image = "http://marssofttech.com/demos/eaglepizza//uploads/beverage/39_2017/6f34a7e82ed234ffbe9fceb26735fc3f.jpg";
                JSONObject jsonObj=jsonarray.getJSONObject(i);
                mDetails.setDesertsImage(image);
                mDetails.setDesertsName(jsonObj.getString("name"));
                mDetails.setDesertsPrice(jsonObj.getString("price"));

                mDesertsDetails.add(mDetails);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        DesertsAdapter mDesertsAdapter = new DesertsAdapter(getContext(),mDesertsDetails);
        mDesertsList.setAdapter(mDesertsAdapter);
        mDesertsAdapter.notifyDataSetChanged();
    }
}
