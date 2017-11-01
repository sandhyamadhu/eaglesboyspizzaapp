package com.appcare.eaglesboys.OrderHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class OrderHistoryFragment extends CommonFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mOrderHistory = inflater.inflate(R.layout.fragment_order_history, null);
        initCreateViews(mOrderHistory);

        return mOrderHistory;
    }

    private ListView mOrederHistorylv;

    private void initCreateViews(View mOrderHistory) {
        mOrederHistorylv = (ListView) mOrderHistory.findViewById(R.id.lvOrderHistory);
        init();
//        HttpHandler.sendRequest("foods_api/sides",mResponseHandler,"sides");
    }

    ArrayList<OrderHistoryDetails> mHistoryDetails = new ArrayList<>();
    String mNonVegPizza = "{\"sides\":[{\"time\":\"01/11/2017\",\"cost\":\"365\"},{\"time\":\"01/11/2017\",\"cost\":\"365\"},{\"time\":\"01/11/2017\",\"cost\":\"365\"},{\"time\":\"01/11/2017\",\"cost\":\"365\"},{\"time\":\"01/11/2017\",\"cost\":\"365\"}]}";

      private void init() {
        try {
            JSONObject mJSObject = new JSONObject(mNonVegPizza.toString());
            JSONArray jsonarray= mJSObject.getJSONArray("sides");
            for (int i=0;i<jsonarray.length();i++){

                OrderHistoryDetails details=new OrderHistoryDetails();

//                String time="01/11/2017";
//                String cost = "₹200";
                JSONObject jsonObj=jsonarray.getJSONObject(i);
//                details.setMtiming(time);
//                details.setMcost(cost);
                details.setMcost(jsonObj.getString("cost"));
                details.setMtiming(jsonObj.getString("time"));

                mHistoryDetails.add(details);

            }
            setAspToList();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void handleResponse(Object response, String tag) {
//        super.handleResponse(response, tag);
//        mHistoryDetails.clear();
//
//
//        try {
//            JSONObject mJSObject = new JSONObject(mNonVegPizza.toString());
//            JSONArray jsonarray= mJSObject.getJSONArray("sides");
//            for (int i=0;i<jsonarray.length();i++){
//
//                OrderHistoryDetails details=new OrderHistoryDetails();
//
////                String time="01/11/2017";
////                String cost = "₹200";
//                JSONObject jsonObj=jsonarray.getJSONObject(i);
////                details.setMtiming(time);
////                details.setMcost(cost);
//                details.setMcost(jsonObj.getString("cost"));
//                details.setMtiming(jsonObj.getString("time"));
//
//                mHistoryDetails.add(details);
//
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//      OrderHistoryListAdapter mOrderHistoryListAdapter=new   OrderHistoryListAdapter(getContext(),mHistoryDetails);
//        mOrederHistorylv.setAdapter(mOrderHistoryListAdapter);
//        mOrderHistoryListAdapter.notifyDataSetChanged();
    private void setAspToList() {
    OrderHistoryListAdapter mOrderHistoryListAdapter=new   OrderHistoryListAdapter(getContext(),mHistoryDetails);
    mOrederHistorylv.setAdapter(mOrderHistoryListAdapter);
    mOrderHistoryListAdapter.notifyDataSetChanged();
        mOrederHistorylv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addFragment(R.id.fragmentContent,new OHDetailedFragment(),true,false);
            }
        });

}
    }

