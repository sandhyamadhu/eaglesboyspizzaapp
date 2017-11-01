package com.appcare.eaglesboys.constants;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.appcare.eaglesboys.utils.EagleResponseListener;
import com.appcare.eaglesboys.utils.ResponseHandler;

import java.io.InputStream;


public class CommonFragment extends Fragment implements EagleResponseListener {

    protected AppCompatActivity mContext;
    public ResponseHandler mResponseHandler;

    public FragmentActivity getMainActivity() {
        if (mContext != null) {
            return mContext;
        } else {
            return super.getActivity();
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = (AppCompatActivity) activity;
        mResponseHandler = new ResponseHandler(mContext, this);

    }

    public void addFragment(int fragmentContent, Fragment fragment, boolean addStack, boolean isReplace){
        ((CommonActivity)mContext).addFragment(R.id.fragmentContent,fragment,addStack,isReplace);
    }


    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private boolean isThisLastFragmentInStack() {
        FragmentManager fragmentManager = getMainActivity().getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() != 0) {
            FragmentManager.BackStackEntry backEntry = fragmentManager
                    .getBackStackEntryAt(fragmentManager
                            .getBackStackEntryCount() - 1);

            // Pause the current fragment before adding new fragment
            if (backEntry.getName().equalsIgnoreCase(getMainActivity().getClass().toString())) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        if (isThisLastFragmentInStack()){
            onFragmentResume();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (isThisLastFragmentInStack()){
            onFragmentPause();
        }

        super.onPause();
    }

    public void onFragmentResume() {

        System.out.println("1111 onFragmentResume");
    }

    public void onFragmentPause() {
    }

    public void hideHeaderImageVisibility(int mVisibility){
        ((MenuActivity)mContext).hideImageLayout(mVisibility);
    }

    public void handleAppBarVisibility(int mVisibility){
        ((MenuActivity)mContext).hideAppBarLayout(mVisibility);
    }

    public void hideHeaderSearchVisibility(int mVisibility){
        ((MenuActivity)mContext).hideSearchLayout(mVisibility);
    }

    public void onCartItemSelected(){
        System.out.println("33333");
        ((MenuActivity)mContext).onCartSelected();
    }


    @Override
    public void handleResponse(Object response, String tag) {

    }

    @Override
    public void handleError(int errorCode, String volleyError) {

    }
}
