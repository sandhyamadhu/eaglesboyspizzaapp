package com.appcare.eaglesboys.constants;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.utils.EagleResponseListener;
import com.appcare.eaglesboys.utils.ResponseHandler;

public class CommonActivity extends AppCompatActivity implements EagleResponseListener{

    public ResponseHandler mResponseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResponseHandler = new ResponseHandler(CommonActivity.this, this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    @Override
    public void handleResponse(Object response, String tag) {

    }

    @Override
    public void handleError(int errorCode, String volleyError) {

    }


    public void addFragment(int containerViewId, Fragment fragment, boolean addStack, boolean isReplace) {

        if (fragment == null) {
            return;
        }

        // if (fragmentManager == null)
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        String tag = fragment.getClass().toString();

        if (fragmentManager.getBackStackEntryCount() != 0) {
            FragmentManager.BackStackEntry backEntry = fragmentManager
                    .getBackStackEntryAt(fragmentManager
                            .getBackStackEntryCount() - 1);

            // Pause the current fragment before adding new fragment
            CommonFragment currentFragment = (CommonFragment) fragmentManager
                    .findFragmentByTag(backEntry.getName());
            currentFragment.onFragmentPause();
        } else {

            // Last fragment is not attached to back stack. So get
            // the fragment by id and pause the current fragment.
            CommonFragment currentFragment = (CommonFragment) fragmentManager
                    .findFragmentById(containerViewId);
            if (currentFragment != null){
                currentFragment.onFragmentPause();
            }
        }

        if (isReplace) {

            fragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.executePendingTransactions();
        }

        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            if (!isReplace)
                fragmentTransaction.add(containerViewId, fragment, tag);
            else {
                fragmentTransaction.replace(containerViewId, fragment, tag);
            }
        }

        if (addStack) {
            fragmentTransaction.addToBackStack(tag);
        } else {
            fragmentManager.popBackStack(tag,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }


    public void removeFragment(Fragment fragment,FragmentManager fragmentManager) {

        if (fragment == null) {
            return;
        }
        try {
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();

            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();

            fragmentManager.popBackStackImmediate();

            if (fragmentManager.getBackStackEntryCount() != 0) {

                FragmentManager.BackStackEntry backEntry = fragmentManager
                        .getBackStackEntryAt(fragmentManager
                                .getBackStackEntryCount() - 1);

                // Resume the previous fragment
                CommonFragment currentFragment = (CommonFragment) fragmentManager
                        .findFragmentByTag(backEntry.getName());
                currentFragment.onFragmentResume();

            } else {
                CommonFragment currentFragment = (CommonFragment) fragmentManager
                        .findFragmentById(R.id.fragmentContent);
                currentFragment.onFragmentResume();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
