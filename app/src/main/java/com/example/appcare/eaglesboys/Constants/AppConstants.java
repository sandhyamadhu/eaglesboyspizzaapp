package com.example.appcare.eaglesboys.Constants;

import android.content.Context;
import android.net.ConnectivityManager;


public class AppConstants {

    public static boolean isNetworkEnabled(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() == null)
            return false;

        return cm.getActiveNetworkInfo().isConnectedOrConnecting();

    }

}
