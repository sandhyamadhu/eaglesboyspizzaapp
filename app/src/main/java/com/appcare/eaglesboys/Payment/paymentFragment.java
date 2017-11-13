package com.appcare.eaglesboys.Payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

/**
 * Created by Appcare on 13-11-2017.
 */

public class paymentFragment extends CommonFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       View mPayment=inflater.inflate (R.layout.fragment_payment,null);
        return mPayment;
    }
}


