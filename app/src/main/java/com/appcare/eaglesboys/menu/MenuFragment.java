package com.appcare.eaglesboys.menu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.appcare.eaglesboys.Constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.nonvegpiza.NonVegPizzaFragment;

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

        final ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        VegPizzaFragment mVegPizzaFragment = new VegPizzaFragment();
        NonVegPizzaFragment mNonVegPizzaFragment = new NonVegPizzaFragment();
        SpecialChickenFragment mSpecialChickenFragment = new SpecialChickenFragment();
        PizzaManiaFragment mPizzaManiaFragment = new PizzaManiaFragment();
        DessertsFragment mDessertsFragment = new DessertsFragment();
        BerveragesFragment mBerveragesFragment = new BerveragesFragment();
        BurgerPizzaFragment mBurgerPizzaFragment = new BurgerPizzaFragment();
        SidesFragment mSidesFragment = new SidesFragment();

        fragments.add(mVegPizzaFragment);
        fragments.add(mNonVegPizzaFragment);
        fragments.add(mSpecialChickenFragment);
        fragments.add(mPizzaManiaFragment);
        fragments.add(mDessertsFragment);
        fragments.add(mBerveragesFragment);
        fragments.add(mBurgerPizzaFragment);
        fragments.add(mSidesFragment);




        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments);
        mPizzaPager.setAdapter(mViewPagerAdapter);
        // Tab Initialization
        //initialiseTabHost(mPizzaView);
        mPizzaPager.setOffscreenPageLimit(7);
        mPizzaPager.setClipToPadding(false);

       final TabLayout tabLayout = (TabLayout) mPizzaView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("VEG PIZZA"));
        tabLayout.addTab(tabLayout.newTab().setText("NON_VEG PIZZA"));
        tabLayout.addTab(tabLayout.newTab().setText("SPECIALTY CHICKEN"));
        tabLayout.addTab(tabLayout.newTab().setText("PIZZA MANIA"));
        tabLayout.addTab(tabLayout.newTab().setText("DESSERTS"));
        tabLayout.addTab(tabLayout.newTab().setText("BEVERAGES"));
        tabLayout.addTab(tabLayout.newTab().setText("BURGER PIZZA"));
        tabLayout.addTab(tabLayout.newTab().setText("SIDES"));

        mPizzaPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPizzaPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}