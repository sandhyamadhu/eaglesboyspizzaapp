package com.appcare.eaglesboys.MealDeal;

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
 * Created by Appcare on 14-11-2017.
 */

public class VegPizzaDealFragment extends CommonFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mInnerDeal=inflater.inflate (R.layout.fragment_vegpizzadeal,null);
        initCreateViews(mInnerDeal);
        return mInnerDeal;
    }
    private ListView mVegPizzaDealLv;
    private void initCreateViews(View mInnerDeal) {
        mVegPizzaDealLv=(ListView) mInnerDeal.findViewById (R.id.vegpizzadeallist);
        init();
    }

    ArrayList<VegpizzaDealDetails> mVegPizzaDetails=new ArrayList<> ();
    String mVegdeals="{\"vegdeals\":[{\"image\":\"http://marssofttech.com/demos/eaglepizza//uploads/foods/43_2017/0aee2cd4fd15f77d984c68b4c06d1c0c.jpg\",\"name\":\"Veg Pizza\"}]}";

    private void init() {
        try {
            JSONObject jsonObject=new JSONObject (mVegdeals);

            JSONArray jsonArray=jsonObject.getJSONArray ("vegdeals");
            for(int i=0;i<jsonArray.length ();i++)
            {
                VegpizzaDealDetails mDetails=new VegpizzaDealDetails ();
                JSONObject innerJsonObject=jsonArray.getJSONObject (i);
                mDetails.setImgVegPizzaDeal (innerJsonObject.getString ("image"));
                mDetails.setTxtVegPizzaDealName (innerJsonObject.getString ("name"));
                mVegPizzaDetails.add(mDetails);

            }
        } catch (JSONException e) {
            e.printStackTrace ();
        }
        VegPizzaDealAdapter adapter=new VegPizzaDealAdapter (getContext (),mVegPizzaDetails);
        mVegPizzaDealLv.setAdapter (adapter);
        adapter.notifyDataSetChanged ();

    }
}
