package com.appcare.eaglesboys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.appcare.eaglesboys.R;


public class OffersPage extends AppCompatActivity {
    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mtoggle;
    public NavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawlayout);
        nav=(NavigationView) findViewById(R.id.navview);
        mtoggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        nav.setItemIconTintList(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
        return mtoggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    public void profile(MenuItem item) {
        Intent in=new Intent(OffersPage.this, Profile.class);
        startActivity(in);
    }

    public void offers(MenuItem item) {
        Intent in=new Intent(OffersPage.this, OffersPage.class);
        startActivity(in);
    }

    public void neworder(MenuItem item) {
        Intent in=new Intent(OffersPage.this, FullMenu.class);
        startActivity(in);
    }

    public void backtomenu(MenuItem item) {
        Intent in=new Intent(OffersPage.this, DupeMenu.class);
        startActivity(in);
    }

    public void dailyoffer(MenuItem item) {
        Intent in=new Intent(OffersPage.this,OffersPage.class);
        startActivity(in);
    }
}
