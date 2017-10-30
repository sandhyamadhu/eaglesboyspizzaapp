package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.R;
//import com.appcare.eaglesboys.R;

public class OffersFragment extends CommonFragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View mMenuView = inflater.inflate(R.layout.fragment_full_menu,null);

        /*String mImageID = "http://marssofttech.com/demos/eaglepizza//uploads/foods/39_2017/dafc942952aab8e3fb6a29e99a14b1a4.png";

        new DownloadImageTask((ImageView) mMenuView.findViewById(R.id.imgMenuBackGround))
                .execute(mImageID);*/


        return mMenuView;
    }
}
