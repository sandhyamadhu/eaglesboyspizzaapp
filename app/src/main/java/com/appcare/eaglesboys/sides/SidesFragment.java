package com.appcare.eaglesboys.sides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;

public class SidesFragment extends CommonFragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_sides,null);
        return mTopinsView;
    }
}
