package com.appcare.eaglesboys.placeorder;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;

public class CartFragment extends CommonFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mPizzaView = inflater.inflate(R.layout.fragment_cart,null);
        initMenuVisibility();

        return mPizzaView;
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        initMenuVisibility();
    }

    private void initMenuVisibility() {

        hideHeaderImageVisibility(View.VISIBLE);
        hideHeaderSearchVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        hideHeaderImageVisibility(View.GONE);
        hideHeaderSearchVisibility(View.VISIBLE);
    }
}
