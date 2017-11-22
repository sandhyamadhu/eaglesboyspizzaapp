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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appcare.eaglesboys.Feedback.FeedbackFragment;
import com.appcare.eaglesboys.MealDeal.MealDealFragment;
import com.appcare.eaglesboys.OrderHistory.OrderHistoryFragment;
import com.appcare.eaglesboys.Profile.ProfileFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.TnCDesclaimer.TCDesclaimerFragment;
import com.appcare.eaglesboys.constants.CommonActivity;
import com.appcare.eaglesboys.placeorder.CartFragment;
import com.appcare.eaglesboys.utils.CenterRepository;


public class MenuActivity extends CommonActivity {
    private int itemCount = 0;
    public static   TextView itemCountTextView;
    protected OnBackPressedListener onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        initDrawerLayoutView();
        initNavigationBarView();

        addFragment(R.id.fragmentContent,new HomeFragment(),false,true);

    }
    public interface OnBackPressedListener {
        void doBack();
    }
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }
    private ImageView mImgOrderCount;
    private AppBarLayout mAppBarLayout;
    private LinearLayout mHeaderImageLayout;
    private LinearLayout mHeaderSearchLayout;


    private void initDrawerLayoutView() {

        itemCount = CenterRepository.getCenterRepository().getListOfProductsInShoppingList()
                .size();

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

        EditText mEditText = (EditText)toolbar.findViewById(R.id.edtPizzaSearch);

        ImageView mImageView = (ImageView)toolbar.findViewById(R.id.imgOrderCount);
        itemCountTextView = (TextView)findViewById(R.id.ordercount);
        itemCountTextView.setSelected(true);
        itemCountTextView.setText(String.valueOf(itemCount));



        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void onCartSelected(){

        addFragment(R.id.fragmentContent,new CartFragment(),false,true);
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

                if (id==R.id.menuProfile) {
                    addFragment(R.id.fragmentContent,new ProfileFragment(),false,true);

                }else if(id==R.id.menuDeals)
                {
                    addFragment(R.id.fragmentContent,new MealDealFragment(),false,true);
                }
                else if(id==R.id.menuNewOrders)
                {

                    addFragment(R.id.fragmentContent,new HomeFragment (),false,true);
                }
                else if (id==R.id.menuBackToMenu){
                    addFragment(R.id.fragmentContent,new HomeFragment(),false,true );

                }else if(id==R.id.menuEveryDay){
                    addFragment(R.id.fragmentContent,new OffersFragment(),false,true);

                }else if(id==R.id.menuOrderHistory) {
                    addFragment(R.id.fragmentContent, new OrderHistoryFragment(), true, false);

                }else if(id==R.id.menuFavouriteOrder){

                }else if(id==R.id.menuTradeOrder){

                }else if(id==R.id.menuDisclaimer){
                    addFragment(R.id.fragmentContent,new TCDesclaimerFragment(),true,false);

                }else if(id==R.id.menuFeedBack){
                    addFragment(R.id.fragmentContent,new FeedbackFragment(),true,false);

                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            }
        });

    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

}

