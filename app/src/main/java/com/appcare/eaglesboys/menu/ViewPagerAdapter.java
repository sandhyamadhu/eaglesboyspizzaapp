package com.appcare.eaglesboys.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.appcare.eaglesboys.beverages.BerveragesFragment;
import com.appcare.eaglesboys.deserts.DessertsFragment;
import com.appcare.eaglesboys.nonvegpiza.NonVegPizzaFragment;
import com.appcare.eaglesboys.pasta.PastaFragment;
import com.appcare.eaglesboys.pizzamates.PizzaMatesFragment;
import com.appcare.eaglesboys.sides.SidesFragment;
import com.appcare.eaglesboys.vegpizza.VegPizzaFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"VEG PIZZA", "NON-VEG PIZZA", "PASTA",
            "PIZZAMATES", "DESSERTS", "BEVERAGES", "SIDES"};

    public ViewPagerAdapter(FragmentManager fm) {
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
                return new VegPizzaFragment();
            case 1:
                return new NonVegPizzaFragment();
            case 2:
                return new PastaFragment();
            case 3:
                return new PizzaMatesFragment();
            case 4:
                return new DessertsFragment();
            case 5:
                return new BerveragesFragment();
            case 6:
                return new SidesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
