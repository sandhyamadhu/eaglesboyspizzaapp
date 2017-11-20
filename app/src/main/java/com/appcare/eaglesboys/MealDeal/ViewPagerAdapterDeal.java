package com.appcare.eaglesboys.MealDeal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.appcare.eaglesboys.nonvegpiza.NonVegPizzaFragment;

public class ViewPagerAdapterDeal extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"VEG PIZZA", "NON-VEG PIZZA"};

    public ViewPagerAdapterDeal(FragmentManager fm) {
        super(fm);
    }

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new VegPizzaDealFragment ();
            case 1:
                return new NonVegPizzaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
