package com.appcare.eaglesboys.menu;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.placeorder.CartFragment;

public class MenuFragment extends CommonFragment {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mPizzaView = inflater.inflate(R.layout.fragment_menu, null);
        readBundle(getArguments());
        initSetUPPizzaTypes(mPizzaView);

        initMenuVisibility();
        return mPizzaView;
    }

    private void initMenuVisibility() {

        hideHeaderImageVisibility(View.GONE);
        hideHeaderSearchVisibility(View.VISIBLE);
    }

    @Override
    public void onFragmentResume() {
        initMenuVisibility();
        super.onFragmentResume();
        System.out.println("222 onFragmentResume");

    }

    private ViewPager mPizzaPager;
    int index;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initSetUPPizzaTypes(View mPizzaView) {

        mPizzaPager = (ViewPager) mPizzaView.findViewById(R.id.pizzaPager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mPizzaPager.setAdapter(mViewPagerAdapter);
        mPizzaPager.setOffscreenPageLimit(7);
        mPizzaPager.setClipToPadding(false);
        mPizzaPager.setCurrentItem(index);
        final TabLayout tabLayout = (TabLayout) mPizzaView.findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(mPizzaPager);

    }


    private void readBundle(Bundle bundle) {
        Bundle args=getArguments();
        if (bundle != null) {
            index = args.getInt("index",0);
            Log.e("hai if", String.valueOf(index));

        }else{
            index = 0;
            Log.e("hai else", String.valueOf(index));
        }
    }


    @Override
    public void onCartItemSelected() {
        super.onCartItemSelected();

        System.out.println("22222");
        addFragment(R.id.fragmentContent, new CartFragment(), true, false);
    }

}