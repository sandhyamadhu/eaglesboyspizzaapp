package com.appcare.eaglesboys.OrderHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Appcare on 01-11-2017.
 */

public class OHDetailedFragment extends CommonFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mOrderHistoryDetails = inflater.inflate(R.layout.fragment_ohdetails, null);
        initCreateViews(mOrderHistoryDetails);

        return mOrderHistoryDetails;
    }
private ListView mOrderDetailslv;
    private void initCreateViews(View mOrderHistoryDetails) {
        mOrderDetailslv=(ListView) mOrderHistoryDetails.findViewById(R.id.lvdetails);
        init();

    }
    ArrayList<OHDetails> mOHDetails=new ArrayList<>();
    String abc= "{\"details\":[{\"summary\":\"pizza\",\"qty\":\"2\",\"price\":\"400\"},{\"summary\":\"pizza\",\"qty\":\"2\",\"price\":\"400\"},{\"summary\":\"pizza\",\"qty\":\"2\",\"price\":\"400\"},{\"summary\":\"pizza\",\"qty\":\"2\",\"price\":\"400\"}]}";
    private void init() {
        try {
            JSONObject mJSObject = new JSONObject(abc.toString());
            JSONArray jsonarray= mJSObject.getJSONArray("details");
            for (int i=0;i<jsonarray.length();i++){

               OHDetails mDetails=new OHDetails();

                JSONObject jsonObj=jsonarray.getJSONObject(i);
                mDetails.setMsummary(jsonObj.getString("summary"));
                mDetails.setMqty(jsonObj.getString("qty"));
                mDetails.setMprice(jsonObj.getString("price"));

               mOHDetails.add(mDetails);


            }
            setAspToList();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setAspToList() {
        OHListAdapter mohListAdapter=new OHListAdapter(getContext(),mOHDetails);
        mOrderDetailslv.setAdapter(mohListAdapter);
        mohListAdapter.notifyDataSetChanged();

}

}
