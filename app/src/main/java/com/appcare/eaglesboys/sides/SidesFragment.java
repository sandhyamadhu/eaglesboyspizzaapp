package com.appcare.eaglesboys.sides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.utils.HttpHandler;
import com.appcare.eaglesboys.beverages.CustomListAdapter;
import com.appcare.eaglesboys.beverages.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SidesFragment extends CommonFragment{
    ArrayList<Product> arrayList;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_sides,null);
        createviews(mTopinsView);
        return mTopinsView;
    }

    private void createviews(View mTopinsView) {
        arrayList=new ArrayList<>();
        listView=(ListView) mTopinsView.findViewById(R.id.sideslist);
        HttpHandler.sendJSONRequest("foods_api/sides",mResponseHandler,"sides");
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
