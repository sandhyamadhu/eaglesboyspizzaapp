package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.R;

public class FullMenuFragment extends CommonFragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View mMenuView = inflater.inflate(R.layout.fragment_full_menu,null);
        ImageView vegpizza=(ImageView) mMenuView.findViewById(R.id.vegpizza);
        vegpizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(R.id.fragmentContent, new MenuFragment(),true,false);
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
