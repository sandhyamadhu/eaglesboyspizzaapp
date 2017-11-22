package com.appcare.eaglesboys.AddAddress;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.menu.HomeFragment;

/**
 * Created by Appcare on 20-11-2017.
 */

public class addAddressFragment extends CommonFragment {
    public View onCreateView(LayoutInflater inflater, ViewPager container, Bundle savedInstanceState)
    {
        View mAddAddress=inflater.inflate (R.layout.activity_add_address,null);
        initMenuVisibility();
        initCreateViews(mAddAddress);
        return mAddAddress;
    }

    private void initCreateViews(View mAddAddress) {
        Button mSaveAndContinue=(Button) mAddAddress.findViewById (R.id.btnSaveAndContinue);
        mSaveAndContinue.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                addFragment (R.id.fragmentContent,new HomeFragment (),false,true);
            }
        });
    }

    private void initMenuVisibility() {
        handleAppBarVisibility (View.VISIBLE);
        hideHeaderImageVisibility(View.GONE);
        hideHeaderSearchVisibility(View.GONE);
//        hideHeadercartVisibility (View.VISIBLE);
    }

}
