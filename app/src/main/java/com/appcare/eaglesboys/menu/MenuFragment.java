package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.beverages.BerveragesFragment;
import com.appcare.eaglesboys.burger.BurgerPizzaFragment;
import com.appcare.eaglesboys.deserts.DessertsFragment;
import com.appcare.eaglesboys.nonvegpiza.NonVegPizzaFragment;
import com.appcare.eaglesboys.pasta.PizzaManiaFragment;
import com.appcare.eaglesboys.pizzamates.PizzaMatesFragment;
import com.appcare.eaglesboys.sides.SidesFragment;
import com.appcare.eaglesboys.vegpizza.VegPizzaFragment;

import java.util.ArrayList;

public class MenuFragment extends CommonFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View mPizzaView = inflater.inflate(R.layout.fragment_menu,null);
        initSetUPPizzaTypes(mPizzaView);

        return mPizzaView;
    }

    private ViewPager mPizzaPager;

    private void initSetUPPizzaTypes(View mPizzaView) {

        mPizzaPager = (ViewPager) mPizzaView.findViewById(R.id.pizzaPager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mPizzaPager.setAdapter(mViewPagerAdapter);
        mPizzaPager.setOffscreenPageLimit(7);
        mPizzaPager.setClipToPadding(false);

        final TabLayout tabLayout = (TabLayout) mPizzaView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mPizzaPager);

    }
}