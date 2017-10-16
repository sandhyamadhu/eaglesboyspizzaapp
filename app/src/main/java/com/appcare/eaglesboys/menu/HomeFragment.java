package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.topins.ToppinsFragment;

public class HomeFragment extends CommonFragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View mMenuView = inflater.inflate(R.layout.fragment_home,null);

        /*String mImageID = "http://marssofttech.com/demos/eaglepizza//uploads/foods/39_2017/dafc942952aab8e3fb6a29e99a14b1a4.png";

        new DownloadImageTask((ImageView) mMenuView.findViewById(R.id.imgMenuBackGround))
                .execute(mImageID);*/

        Button btnMenu = (Button) mMenuView.findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new MenuFragment(),false,true);
            }
        });

        return mMenuView;
    }
}
