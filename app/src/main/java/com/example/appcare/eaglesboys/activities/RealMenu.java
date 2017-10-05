package com.example.appcare.eaglesboys.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.appcare.eaglesboys.R;
import com.example.appcare.eaglesboys.fragments.CallsFragment;
import com.example.appcare.eaglesboys.fragments.ChatFragment;
import com.example.appcare.eaglesboys.fragments.ContactsFragment;


public class RealMenu extends AppCompatActivity {

//    public DrawerLayout mDrawerLayout;
//    public ActionBarDrawerToggle mtoggle;
//    public NavigationView nav;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    ChatFragment chatFragment;
    CallsFragment callsFragment;
    ContactsFragment contactsFragment;
    TextView addcart;

//    String[] tabTitle={"CALLS","CHAT","CONTACTS"};
//    int[] unreadCount={0,5,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_real_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addcart=(TextView) findViewById(R.id.addd);
//        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawlayout);
//        nav=(NavigationView) findViewById(R.id.navview);
//        mtoggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
//        mDrawerLayout.addDrawerListener(mtoggle);
//        mtoggle.syncState();
//        nav.setItemIconTintList(null);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return mtoggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
//
//    }
//    public void profile(MenuItem item) {
//        Intent in=new Intent(RealMenu.this, Profile.class);
//        startActivity(in);
//    }
//
//    public void offers(MenuItem item) {
//        Intent in=new Intent(RealMenu.this, OffersPage.class);
//        startActivity(in);
//    }
//
//    public void neworder(MenuItem item) {
//        Intent in=new Intent(RealMenu.this, FullMenu.class);
//        startActivity(in);
//    }
//
//    public void backtomenu(MenuItem item) {
//        Intent in=new Intent(RealMenu.this, DupeMenu.class);
//        startActivity(in);
//    }
//
//    public void dailyoffer(MenuItem item) {
//        Intent in=new Intent(RealMenu.this,OffersPage.class);
//        startActivity(in);
//    }

//    private void setupTabIcons()
//    {
//
//        for(int i=0;i<tabTitle.length;i++)
//        {
//            /*TabLayout.Tab tabitem = tabLayout.newTab();
//            tabitem.setCustomView(prepareTabView(i));
//            tabLayout.addTab(tabitem);*/
//
//            tabLayout.getTabAt(i).setCustomView(prepareTabView(i));
//        }//
//    @Override
//    public boolean onCreateOptionsMenu(final Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        // Associate searchable configuration with the SearchView
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.action_status:
//                Toast.makeText(this, "Home Status Click", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_settings:
//                Toast.makeText(this, "Home Settings Click", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        callsFragment=new CallsFragment();
        chatFragment=new ChatFragment();
        contactsFragment=new ContactsFragment();
        adapter.addFragment(callsFragment,"VEG PIZZA");
        adapter.addFragment(chatFragment,"NON-VEG PIZZA");
        adapter.addFragment(contactsFragment,"SIDES");
        viewPager.setAdapter(adapter);
    }

    public void addtocart(View view) {
          String abc=addcart.getText().toString();
        String getcount= increment(abc);
        addcart.setText(getcount);
    }

    private String increment(String takenumber) {
        int az=Integer.parseInt(takenumber.toString());
        int bz=az+1;
        String c = String.valueOf(bz);

        return c;
    }

//    private View prepareTabView(int pos) {
//        View view = getLayoutInflater().inflate(R.layout.custom_tab,null);
//        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
//        TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
//        tv_title.setText(tabTitle[pos]);
//        if(unreadCount[pos]>0)
//        {
//            tv_count.setVisibility(View.VISIBLE);
//            tv_count.setText(""+unreadCount[pos]);
//        }
//        else
//            tv_count.setVisibility(View.GONE);
//
//
//        return view;
//    }



    }

