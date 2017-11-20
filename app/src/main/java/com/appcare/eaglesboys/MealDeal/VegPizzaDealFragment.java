package com.appcare.eaglesboys.MealDeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

/**
 * Created by Appcare on 14-11-2017.
 */

public class VegPizzaDealFragment extends CommonFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mInnerDeal=inflater.inflate (R.layout.fragment_vegpizzadeal,null);

        return mInnerDeal;
    }
}
