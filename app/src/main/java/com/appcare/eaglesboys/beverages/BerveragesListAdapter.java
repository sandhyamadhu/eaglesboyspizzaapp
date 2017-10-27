package com.appcare.eaglesboys.beverages;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.eaglesboys.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BerveragesListAdapter extends BaseAdapter {
    Context context;
    List<BerveragesDetails> rowItems;

    public BerveragesListAdapter(Context context, List<BerveragesDetails> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView mImgBeverages;
        TextView mTxtBeveragesName;
        TextView mTxtBeveragesPrice;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BerveragesListAdapter.ViewHolder holder;
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.beverages_row, null);
            holder = new BerveragesListAdapter.ViewHolder();
            holder.mImgBeverages = (ImageView) convertView.findViewById(R.id.imgBeverages);
            holder.mTxtBeveragesName = (TextView) convertView.findViewById(R.id.txtBeveragesName);
            holder.mTxtBeveragesPrice = (TextView) convertView.findViewById(R.id.txtBeveragesPrice);
            convertView.setTag(holder);
        }
        else {
            holder = (BerveragesListAdapter.ViewHolder) convertView.getTag();
        }

        BerveragesDetails rowItem = (BerveragesDetails) getItem(position);

        holder.mTxtBeveragesName.setText(rowItem.getBerveragesName());
        holder.mTxtBeveragesPrice.setText("\u20B9 "+rowItem.getBerveragesPrice());
        Picasso.with(context).load(rowItem.getBerveragesImage()).into(holder.mImgBeverages);
        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}
