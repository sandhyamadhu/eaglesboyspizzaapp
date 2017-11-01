package com.appcare.eaglesboys.TnCDesclaimer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

/**
 * Created by Appcare on 01-11-2017.
 */

public class TCDesclaimerFragment extends CommonFragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mDesclaimer=inflater.inflate(R.layout.fragment_desclaimer,null);
        initCreateViews(mDesclaimer);
        return  mDesclaimer;
    }

    private void initCreateViews(View mDesclaimer) {
        TextView tnc=(TextView)mDesclaimer.findViewById(R.id.txtTC);
    }
}
