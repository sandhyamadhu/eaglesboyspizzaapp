package com.appcare.eaglesboys.MealDeal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

/**
 * Created by Appcare on 14-11-2017.
 */

public class InnerMealDealFragment extends CommonFragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mInnerDeal=inflater.inflate (R.layout.fragment_innermealdeal,null);
        initCreateView(mInnerDeal);
        return mInnerDeal;
    }
  private ViewPager viewPager;
    private void initCreateView(View mInnerDeal) {
        viewPager=(ViewPager) mInnerDeal.findViewById (R.id.viewDeal);
        ViewPagerAdapterDeal mViewPagerAdapter = new ViewPagerAdapterDeal (getChildFragmentManager());
        viewPager.setAdapter (mViewPagerAdapter);
        viewPager.setOffscreenPageLimit (2);
        viewPager.setClipToPadding (false);
        final TabLayout tabLayout = (TabLayout) mInnerDeal.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager (viewPager);
    }


}
