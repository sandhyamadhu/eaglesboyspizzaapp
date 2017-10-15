package com.appcare.eaglesboys.topins;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.eaglesboys.R;

import java.util.List;

public class ToppinAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mTopinsName;

    public ToppinAdapter(Context c, List<String> topinsName) {
        mContext = c;
        this.mTopinsName = topinsName;
    }

    @Override
    public int getCount() {
        return mTopinsName.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View mRowGridView;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            mRowGridView = inflater.inflate(R.layout.toping_row, null);
            TextView txtToppins = (TextView) mRowGridView.findViewById(R.id.txtToppins);
            ImageView imgTopinsImage = (ImageView)mRowGridView.findViewById(R.id.imgTopinsImage);
            txtToppins.setText(mTopinsName.get(position));
            imgTopinsImage.setImageResource(R.drawable.onion);
        } else {
            mRowGridView = (View) convertView;
        }
        return mRowGridView;
    }
}
