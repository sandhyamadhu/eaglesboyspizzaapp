package com.appcare.eaglesboys.sides;

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

public class SidesFragment extends CommonFragment{
    ListView mSideListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_sides,null);
        initCreateView(mTopinsView);
        return mTopinsView;
    }

    private void initCreateView(View mTopinsView) {
        mSideListView = (ListView)mTopinsView.findViewById(R.id.sidesList);
        HttpHandler.sendRequest("foods_api/beverage",mResponseHandler,"beverages");
    }

    ArrayList<SideDetails> mSideDetails = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);
        mSideDetails.clear();

        try {
            JSONObject mJSObject = new JSONObject(response.toString());
            JSONArray jsonarray= mJSObject.getJSONArray("beverages");
            for (int i=0;i<jsonarray.length();i++){

                SideDetails mDetails = new SideDetails();


                String image = "http://marssofttech.com/demos/eaglepizza//uploads/beverage/39_2017/6f34a7e82ed234ffbe9fceb26735fc3f.jpg";
                JSONObject jsonObj=jsonarray.getJSONObject(i);
                mDetails.setSideImage(image);
                mDetails.setSideName(jsonObj.getString("name"));
                mDetails.setSidePrice(jsonObj.getString("price"));

                mSideDetails.add(mDetails);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        SideListAdapter mSideListAdapter = new SideListAdapter(getContext(),mSideDetails);
        mSideListView.setAdapter(mSideListAdapter);
        mSideListAdapter.notifyDataSetChanged();
    }
}
