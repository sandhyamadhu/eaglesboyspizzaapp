package com.appcare.eaglesboys.pasta;

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
import com.appcare.eaglesboys.menu.HomeFragment;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PastaFragment extends CommonFragment implements MenuActivity.OnBackPressedListener{

    RecyclerView mrecylerListView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mpastaView = inflater.inflate(R.layout.fragment_pasta,null);
        initPastaViews(mpastaView);
        return mpastaView ;
    }

    private void initPastaViews(View mpastaView) {

        mrecylerListView = (RecyclerView) mpastaView.findViewById(R.id.beveragesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity().getBaseContext());
        mrecylerListView.setLayoutManager(linearLayoutManager);
        mrecylerListView.setHasFixedSize(true);
        HttpHandler.sendRequest("foods_api/pasta",mResponseHandler,"beverages");
    }
    ArrayList<ProductModel> mPastaDetails = new ArrayList<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);
        mPastaDetails.clear();
        try {
            JSONObject mJSObject = new JSONObject(response.toString());
            JSONArray jsonarray= mJSObject.getJSONArray("dip");
            for (int i=0;i<jsonarray.length();i++){

                ProductModel mDetails = new ProductModel();

                JSONObject jsonObj=jsonarray.getJSONObject(i);
                System.out.println("Nikhil::>"+jsonObj.getString("image"));
                mDetails.setImageUrl(jsonObj.getString("image"));
                mDetails.setProductName(jsonObj.getString("name"));
                mDetails.setMrp(jsonObj.getString("price"));
                mDetails.setProductId(jsonObj.getString("id"));
                mDetails.setQuantity("1");

                mPastaDetails.add(mDetails);
                setList();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setList() {

        // Fill Recycler View

        ProductAdapter mBerveragesListAdapter = new ProductAdapter(getContext(),mPastaDetails);
        mrecylerListView.setAdapter(mBerveragesListAdapter);
        mBerveragesListAdapter.notifyDataSetChanged();

    }


    @Override
    public void doBack() {
        addFragment (R.id.fragmentContent,new HomeFragment (),false,true);

    }
}
