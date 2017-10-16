package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;


public class DessertsFragment extends CommonFragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_topins,null);
        return mTopinsView;
    }
}
