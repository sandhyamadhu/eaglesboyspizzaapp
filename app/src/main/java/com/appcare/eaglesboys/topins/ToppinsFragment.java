package com.appcare.eaglesboys.topins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ToppinsFragment extends CommonFragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_topins,null);
        initTopinsFragment(mTopinsView);

        HttpHandler.sendJSONRequest("extras_api/extras",mResponseHandler,"Topins");

        return mTopinsView;
    }

    private GridView mToppinGridView;

    private void initTopinsFragment(View topinsView) {

        mToppinGridView = (GridView)topinsView.findViewById(R.id.toppinGridView);

    }

    List<String> mTopinsName = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);
        mTopinsName.clear();
        try {

            JSONArray mJsonArray = new JSONArray(response.toString());

            for (int i=0; i<mJsonArray.length(); i++) {
                JSONObject jsonObject = mJsonArray.getJSONObject(i);
                String cityID = jsonObject.getString("id");
                String cityName = jsonObject.getString("name");
                mTopinsName.add(cityName);
            }
            initTopinAdapter(mTopinsName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initTopinAdapter(List<String> mTopinsName){

        ToppinAdapter mtToppinAdapter = new ToppinAdapter(mContext, mTopinsName);
        mToppinGridView.setAdapter(mtToppinAdapter);
    }
}
