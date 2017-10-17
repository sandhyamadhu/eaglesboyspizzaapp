package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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

        final ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        VegPizzaFragment mVegPizzaFragment = new VegPizzaFragment();
        NonVegPizzaFragment mNonVegPizzaFragment = new NonVegPizzaFragment();
        PizzaMatesFragment mSpecialChickenFragment = new PizzaMatesFragment();
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
        tabLayout.addTab(tabLayout.newTab().setText("PIZZAMATES"));
        tabLayout.addTab(tabLayout.newTab().setText("PASTA"));
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