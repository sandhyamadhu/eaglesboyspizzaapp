package com.appcare.eaglesboys.menu;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.appcare.eaglesboys.Constants.CommonActivity;
import com.appcare.eaglesboys.R;


public class MenuActivity extends CommonActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        initDrawerLayoutView();
        initNavigationBarView();

        addFragment(R.id.fragmentContent,new HomeFragment(),false,true);
    }



    private void initDrawerLayoutView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        EditText mEditText = (EditText)toolbar.findViewById(R.id.edtPizzaSearch);
        ImageView mImageView = (ImageView)toolbar.findViewById(R.id.imgOrderCount);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }


    private void initNavigationBarView() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //View header = navigationView.getHeaderView(0);
        // initMenuViews(headerView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_preferences) {
                    // Handle the preference  action
                } else if (id == R.id.nav_about) {
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

    private void initMenuViews(View headerView) {


    }

}
