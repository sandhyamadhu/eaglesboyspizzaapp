package com.appcare.eaglesboys.beverages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BerveragesFragment extends CommonFragment{
    ArrayList<Product> arrayList;
    ListView listView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_beverages,null);
        createviews(mTopinsView);
        return mTopinsView;
    }

    private void createviews(View mTopinsView) {
        arrayList=new ArrayList<>();
        listView=(ListView) mTopinsView.findViewById(R.id.beverageslist);
        HttpHandler.sendJSONRequest("foods_api/beverage",mResponseHandler,"beverages");
    }

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);
        try {
            JSONArray jsonarray= new JSONArray(response.toString());
            for (int i=0;i<jsonarray.length();i++)
            {
                JSONObject jsonObj=jsonarray.getJSONObject(i);
                arrayList.add(new Product(
                        jsonObj.getString("image"),
                        jsonObj.getString("name"),
                        jsonObj.getString("price")
                ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomListAdapter customListAdapter=new CustomListAdapter(getContext(),R.layout.nonveglist,arrayList);
        listView.setAdapter(customListAdapter);

    }
}
