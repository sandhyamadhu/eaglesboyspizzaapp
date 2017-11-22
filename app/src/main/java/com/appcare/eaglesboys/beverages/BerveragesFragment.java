package com.appcare.eaglesboys.beverages;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.Product.ProductAdapter;
import com.appcare.eaglesboys.Product.ProductModel;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BerveragesFragment extends CommonFragment{

    RecyclerView mBeveragesListView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_beverages,null);
        initCreateViews(mTopinsView);
        return mTopinsView;
    }


    private void initCreateViews(View mTopinsView) {
        mBeveragesListView = (RecyclerView) mTopinsView.findViewById(R.id.beveragesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity().getBaseContext());
        mBeveragesListView.setLayoutManager(linearLayoutManager);
        mBeveragesListView.setHasFixedSize(true);
        HttpHandler.sendRequest("foods_api/beverage",mResponseHandler,"beverages");
    }

    ArrayList<ProductModel> mProductModel_List = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);

        mProductModel_List.clear();
        try {
            JSONObject mJSObject = new JSONObject(response.toString());
            JSONArray jsonarray= mJSObject.getJSONArray("beverages");

            for (int i=0;i<jsonarray.length();i++){

                ProductModel mDetails = new ProductModel();

                JSONObject jsonObj=jsonarray.getJSONObject(i);
                System.out.println("Nikhil::>"+jsonObj.getString("image"));
                mDetails.setImageUrl(jsonObj.getString("image"));
                mDetails.setProductName(jsonObj.getString("name"));
                mDetails.setMrp(jsonObj.getString("price"));
                mDetails.setProductId(jsonObj.getString("id"));
                mDetails.setQuantity("1");

                mProductModel_List.add(mDetails);
                setList();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setList() {

        // Fill Recycler View

        ProductAdapter mBerveragesListAdapter = new ProductAdapter(getContext(),mProductModel_List);
        mBeveragesListView.setAdapter(mBerveragesListAdapter);
        mBerveragesListAdapter.notifyDataSetChanged();

    }
}