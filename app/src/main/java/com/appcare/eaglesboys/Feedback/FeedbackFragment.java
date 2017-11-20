package com.appcare.eaglesboys.Feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

/**
 * Created by Appcare on 01-11-2017.
 */

public class FeedbackFragment extends CommonFragment{
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
   {
       View mFeed=inflater.inflate(R.layout.fragment_feedback,null);
       initMenuVisibility();
       return mFeed;
   }

    private void initMenuVisibility() {
        hideHeaderImageVisibility(View.VISIBLE);
        hideHeaderSearchVisibility(View.GONE);
    }

}
