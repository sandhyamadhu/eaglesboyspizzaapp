package com.appcare.eaglesboys.pasta;

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


public class PastaFragment extends CommonFragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mpastaView = inflater.inflate(R.layout.fragment_pasta,null);
        initPastaViews(mpastaView);
        return mpastaView ;
    }
    private ListView mPastaListview;
    private void initPastaViews(View mpastaView) {
        mPastaListview=(ListView) mpastaView.findViewById(R.id.pastaListview);
        HttpHandler.sendRequest("foods_api/pasta",mResponseHandler,"dip");
    }
    ArrayList<PastaDetails> mPastaDetails = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);
        mPastaDetails.clear();
        try {
            JSONObject mJSObject = new JSONObject(response.toString());
            JSONArray jsonarray= mJSObject.getJSONArray("dip");
            for (int i=0;i<jsonarray.length();i++){

                PastaDetails mDetails = new PastaDetails();

                String desc="Penne Pasta,Red Onion,Capsicum & Tomato sauce";
                String image = "http://marssofttech.com/demos/eaglepizza//uploads/beverage/39_2017/6f34a7e82ed234ffbe9fceb26735fc3f.jpg";
                JSONObject jsonObj=jsonarray.getJSONObject(i);
//                mDetails.setmPastaImage(image);
                mDetails.setmPastaName(jsonObj.getString("name"));
                mDetails.setmPastaPrice(jsonObj.getString("price"));
                mDetails.setmPastaImage (jsonObj.getString ("image"));


                mPastaDetails.add(mDetails);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        PastaListAdapter mPastaAdapter=new PastaListAdapter(getContext(),mPastaDetails);
        mPastaListview.setAdapter(mPastaAdapter);
        mPastaAdapter.notifyDataSetChanged();
    }
    }

