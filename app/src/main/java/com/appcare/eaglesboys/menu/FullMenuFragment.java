package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

public class FullMenuFragment extends CommonFragment {



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View mMenuView = inflater.inflate(R.layout.fragment_full_menu,null);
        ImageView vegpizza=(ImageView) mMenuView.findViewById(R.id.vegpizza);
        ImageView nonvegpizza=(ImageView) mMenuView.findViewById(R.id.nonvegpizza);
        vegpizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("index",0); // Put anything what you want

                MenuFragment fragment2 = new MenuFragment();
                fragment2.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.fragmentContent, fragment2).commit();
                // addFragment(R.id.fragmentContent, new MenuFragment(),true,false);

            }
        });
        nonvegpizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("index",1); // Put anything what you want

                MenuFragment fragment2 = new MenuFragment();
                fragment2.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.fragmentContent, fragment2).commit();
                // addFragment(R.id.fragmentContent, new MenuFragment(),true,false);

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