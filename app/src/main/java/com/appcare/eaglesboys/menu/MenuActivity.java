package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appcare.eaglesboys.constants.CommonActivity;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.placeorder.CartFragment;


public class MenuActivity extends CommonActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        initDrawerLayoutView();
        initNavigationBarView();

        addFragment(R.id.fragmentContent,new HomeFragment(),false,true);
    }

    private ImageView mImgOrderCount;
    private AppBarLayout mAppBarLayout;
    private LinearLayout mHeaderImageLayout;
    private LinearLayout mHeaderSearchLayout;


    private void initDrawerLayoutView() {

        mImgOrderCount = (ImageView)findViewById(R.id.imgOrderCount);
        mImgOrderCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCartSelected();
            }
        });
        mHeaderImageLayout = (LinearLayout)findViewById(R.id.headerImageLayout);
        mHeaderSearchLayout = (LinearLayout)findViewById(R.id.headerSearchLayout);

        mAppBarLayout = (AppBarLayout)findViewById(R.id.appBarLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        EditText mEditText = (EditText)toolbar.findViewById(R.id.edtPizzaSearch);
//        ImageView mImageView = (ImageView)toolbar.findViewById(R.id.imgOrderCount);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void onCartSelected(){

        addFragment(R.id.fragmentContent,new CartFragment(),true,false);
    }

    public void hideImageLayout(int mVisibility){
        mHeaderImageLayout.setVisibility(mVisibility);
    }

    public void hideSearchLayout(int mVisibility){
        mHeaderSearchLayout.setVisibility(mVisibility);
    }

    public void hideAppBarLayout(int mVisibility){
        mAppBarLayout.setVisibility(mVisibility);
    }


    private void initNavigationBarView() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menuOffers) {
                    // Handle the preference  action
                } else if (id == R.id.menuNewOrders) {
                    // Handle the About action
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}