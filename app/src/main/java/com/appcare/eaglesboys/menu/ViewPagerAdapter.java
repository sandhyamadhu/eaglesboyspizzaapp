package com.appcare.eaglesboys.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mlistFragments;
    // Tab Titles
    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> listFragments) {
        super(fm);
        this.mlistFragments = listFragments;
    }
    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int position) {
        return this.mlistFragments.get(position);
    }
    /* (non-Javadoc)
     * @see android.support.v4.view.PagerAdapter#getCount()
     */
    @Override
    public int getCount() {
        return this.mlistFragments.size();
    }
}
