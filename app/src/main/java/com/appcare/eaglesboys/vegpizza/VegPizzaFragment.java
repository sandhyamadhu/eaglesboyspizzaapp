package com.appcare.eaglesboys.vegpizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VegPizzaFragment extends CommonFragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mVegPizzaView = inflater.inflate(R.layout.fragment_vegpizza,null);

        HttpHandler.sendRequest("categories_api/allcat_pizza?categories_id=1",mResponseHandler,"VegPizza");
        initNonVegPizzaViews(mVegPizzaView);
        return mVegPizzaView;

    }


    private ExpandableListView mVegPizzaListview;
    private void initNonVegPizzaViews(View mVegPizzaView) {
        mVegPizzaListview = (ExpandableListView)mVegPizzaView.findViewById(R.id.lvVegPizza);

    }

    String mString = "{\"Classic Veg\":[{\"name\":\"Pizza Hut\",\"description\":\"test descrition\",\"price\":\"365\",\"image\":\"http://marssofttech.com/demos/eaglepizza//uploads/foods/43_2017/679a0ae57b87dcb61cb783ad265d2fb2.jpg\",\"child\":[{\"size\":[{\"size_id\":\"24\",\"name\":\"13 inches\",\"price\":\"21\",\"extracheese\":\"55\",\"crust\":[{\"name\":\"New Hand \",\"price\":\"10\"},{\"name\":\"Tossed\",\"price\":\"20\"},{\"name\":\"Thin\",\"price\":\"30\"}]},{\"size_id\":\"25\",\"name\":\"10 inches\",\"price\":\"23\",\"extracheese\":\"65\",\"crust\":[{\"name\":\"New Hand Tossed\",\"price\":\"40\"},{\"name\":\"Thick\",\"price\":\"50\"}]},{\"size_id\":\"26\",\"name\":\"7 inches\",\"price\":\"45\",\"extracheese\":\"80\",\"crust\":[{\"name\":\"New Hand Tossed\",\"price\":\"60\"}]}]}]},{\"name\":\"Delux Pizza\",\"description\":\"Onion  Capsicum\",\"price\":\"380\",\"image\":\"http://marssofttech.com/demos/eaglepizza//uploads/foods/43_2017/0aee2cd4fd15f77d984c68b4c06d1c0c.jpg\",\"child\":[{\"size\":[{\"size_id\":\"24\",\"name\":\"90 inches\",\"price\":\"90\",\"extracheese\":\"55\",\"crust\":[{\"name\":\"New Hand Tossed\",\"price\":\"70\"},{\"name\":\"Thick\",\"price\":\"80\"},{\"name\":\"Thin\",\"price\":\"90\"}]},{\"size_id\":\"25\",\"name\":\"110 inches\",\"price\":\"110\",\"extracheese\":\"65\",\"crust\":[{\"name\":\"New Hand Tossed\",\"price\":\"100\"},{\"name\":\"Thick\",\"price\":\"110\"}]},{\"size_id\":\"26\",\"name\":\"201 inches\",\"price\":\"120\",\"extracheese\":\"80\",\"crust\":[{\"name\":\"New Hand Tossed\",\"price\":\"120\"}]}]}]}]}";

    ArrayList<ClassicVeg> mClassicHeaderVegs = new ArrayList<> ();
    Map<Integer, List<Child>> mChild = new HashMap<>();

    @Override
    public void handleResponse(Object response, String tag) {
        super.handleResponse(response, tag);


        try {
            JSONObject mJSObject = new JSONObject(response.toString());
            JSONArray mClassicVegArray = mJSObject.getJSONArray("Classic Veg");

            for (int i=0; i<mClassicVegArray.length(); i++) {

                ClassicVeg mClassicVeg = new ClassicVeg ();

                JSONObject mClassVegJSonObject = mClassicVegArray.getJSONObject(i);

                mClassicVeg.setName(mClassVegJSonObject.getString("name"));
                mClassicVeg.setDescription(mClassVegJSonObject.getString("description"));
                mClassicVeg.setImage(mClassVegJSonObject.getString("image"));
                mClassicVeg.setPrice(mClassVegJSonObject.getString("price"));

                JSONArray mChildArray = mClassVegJSonObject.getJSONArray("child");
                List<Child> childrens = new ArrayList<>();
                for (int j = 0; j< mChildArray.length(); j++) {

                    Child mInnerChild = new Child();
                    JSONObject mChildJsonObject = mChildArray.getJSONObject(j);
                    JSONArray mSizeArray = mChildJsonObject.getJSONArray("size");

                    ArrayList<Size> mPizzaSizes = new ArrayList<>();
                    for (int k = 0; k < mSizeArray.length(); k++) {

                        Size mSize = new Size();
                        JSONObject mSizeValues = mSizeArray.getJSONObject(k);

                        mSize.setName(mSizeValues.getString("name"));
                        mSize.setPrice(mSizeValues.getString("price"));
                        mSize.setExtracheese(mSizeValues.getString("extracheese"));

                        JSONArray mCrustArray = mSizeValues.getJSONArray("crust");

                        ArrayList<Crust> mCrustPrice = new ArrayList<>();

                        for (int l = 0; l < mCrustArray.length(); l++) {

                            Crust mCrust = new Crust();
                            JSONObject crustObject = mCrustArray.getJSONObject(l);

                            mCrust.setName(crustObject.getString("name"));
                            mCrust.setPrice(crustObject.getString("price"));

                            mCrustPrice.add(mCrust);
                        }


                        mSize.setCrust(mCrustPrice);
                        mPizzaSizes.add(mSize);
                    }
                    mInnerChild.setSize(mPizzaSizes);
                    childrens.add(mInnerChild);

                }
                mChild.put(i, childrens);
                mClassicHeaderVegs.add(mClassicVeg);
            }
            initVegPizzaAdapter();


            initVegPizzaAdapter();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void initVegPizzaAdapter() {

        VegListAdapter mNonVegListAdapter = new VegListAdapter(getContext(), mClassicHeaderVegs, mChild);
        mVegPizzaListview.setAdapter(mNonVegListAdapter);
        mNonVegListAdapter.notifyDataSetChanged();

        mVegPizzaListview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    mVegPizzaListview.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }
}
