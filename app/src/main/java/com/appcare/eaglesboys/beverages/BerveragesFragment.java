package com.appcare.eaglesboys.beverages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.sides.SideDetails;
import com.appcare.eaglesboys.sides.SideListAdapter;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BerveragesFragment extends CommonFragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_beverages,null);
        initCreateViews(mTopinsView);
        return mTopinsView;
    }

    ListView mBeveragesListView;
    private void initCreateViews(View mTopinsView) {
        mBeveragesListView=(ListView) mTopinsView.findViewById(R.id.beveragesList);
        HttpHandler.sendRequest("foods_api/beverage",mResponseHandler,"beverages");
    }

    ArrayList<BerveragesDetails> mBerveragesDetails = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);

        mBerveragesDetails.clear();
        try {
            JSONObject mJSObject = new JSONObject(response.toString());
            JSONArray jsonarray= mJSObject.getJSONArray("beverages");
            for (int i=0;i<jsonarray.length();i++){

                BerveragesDetails mDetails = new BerveragesDetails();
                JSONObject jsonObj=jsonarray.getJSONObject(i);
                System.out.println("Nikhil::>"+jsonObj.getString("image"));
                mDetails.setBerveragesImage(jsonObj.getString("image"));
                mDetails.setBerveragesName(jsonObj.getString("name"));
                mDetails.setBerveragesPrice(jsonObj.getString("price"));

                mBerveragesDetails.add(mDetails);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        BerveragesListAdapter mBerveragesListAdapter = new BerveragesListAdapter(getContext(),mBerveragesDetails);
        mBeveragesListView.setAdapter(mBerveragesListAdapter);
        mBerveragesListAdapter.notifyDataSetChanged();
    }
}
