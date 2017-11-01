package com.appcare.eaglesboys.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;

/**
 * Created by Appcare on 01-11-2017.
 */

public class ProfileFragment extends CommonFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
     View mProfile=inflater.inflate(R.layout.fragment_profile,null);
        initCreateViews(mProfile);
        return mProfile;
    }

    private void initCreateViews(View mProfile) {
        Button edtBtn=(Button) mProfile.findViewById(R.id.btnEdit);
        final EditText ename=(EditText)mProfile.findViewById(R.id.txtname);
        EditText enumber=(EditText)mProfile.findViewById(R.id.txtnumber);
        final EditText eaddress=(EditText)mProfile.findViewById(R.id.txtaddress);
        edtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ename.setFocusableInTouchMode(true);
                eaddress.setFocusableInTouchMode(true);
            }
        });
    }
}
