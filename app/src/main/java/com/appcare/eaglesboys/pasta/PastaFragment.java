package com.appcare.eaglesboys.pasta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.R;


public class PastaFragment extends CommonFragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mTopinsView = inflater.inflate(R.layout.fragment_pasta,null);
        return mTopinsView;
    }
}
