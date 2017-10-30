package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.R;

public class HomeFragment extends CommonFragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View mMenuView = inflater.inflate(R.layout.fragment_home,null);

        Button btnMenu = (Button) mMenuView.findViewById(R.id.btnMenu);
        Button btnEveryDayOffers=(Button) mMenuView.findViewById(R.id.btnEveryDayValueOfers);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new FullMenuFragment(),true,false);

            }
        });
        btnEveryDayOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new OffersFragment(),false,true);
            }
        });

        initMenuVisibility();

        return mMenuView;
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
}
