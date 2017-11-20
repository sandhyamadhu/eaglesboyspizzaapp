package com.appcare.eaglesboys.MealDeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Appcare on 02-11-2017.
 */

public class MealDealFragment extends CommonFragment{
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
     {
         View mDeal=inflater.inflate(R.layout.fragment_mealdeal,null);
         initCreateViews(mDeal);
         return mDeal;
     }
    private ExpandableListView mDealslv;

    private void initCreateViews(View mDeal) {
        mDealslv= (ExpandableListView) mDeal.findViewById(R.id.dealsList);


        init();


    }
  ArrayList<DealOneParentDetails> mParentDetails=new ArrayList<>();
    ArrayList<DealOneChildDetails> mChildDetails=new ArrayList<>();
    String dealsOne="{\"deals\":[{\"image\":\"http://marssofttech.com/demos/eaglepizza//uploads/foods/43_2017/0aee2cd4fd15f77d984c68b4c06d1c0c.jpg\",\"name\":\"Deal 1\",\"price\":\"400\"}]}";
    String dealsTwo="{\"count\":[{\"num\":\"3\"}]}";

    private void init() {
        try {
            JSONObject mJSObject = new JSONObject(dealsOne.toString());
            JSONArray jsonarray = mJSObject.getJSONArray("deals");
            for (int i = 0; i < jsonarray.length(); i++) {

                DealOneParentDetails pDetails = new DealOneParentDetails();

                JSONObject jsonObj = jsonarray.getJSONObject(i);
                pDetails.setmDealImg(jsonObj.getString("image"));
                pDetails.setmDealName(jsonObj.getString("name"));
                pDetails.setmDealPrice(jsonObj.getString("price"));
                mParentDetails.add(pDetails);

//
//                JSONObject mJSObjectt = new JSONObject(dealsTwo.toString());
//                JSONArray mJsonArrayy = mJSObjectt.getJSONArray("count");
//                for (int j = 0; j < mJsonArrayy.length(); j++) {
//
                    DealOneChildDetails cDetails = new DealOneChildDetails();
//                    JSONObject jsonObjectt = mJsonArrayy.getJSONObject(j);
//                    cDetails.setTxtPizzaCount(jsonObjectt.getString("num"));
                    mChildDetails.add(cDetails);
//
//
//                }
pDetails.setChildDetails(mChildDetails);
                mParentDetails.add(pDetails);
            }
            setAdpToList();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


            }

    private void setAdpToList() {
        DealOneAdapter mDealOneAdapter=new DealOneAdapter(getContext(),mParentDetails,mChildDetails);
        mDealslv.setAdapter(mDealOneAdapter);
        mDealOneAdapter.notifyDataSetChanged();
       mDealslv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    mDealslv.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }


}
