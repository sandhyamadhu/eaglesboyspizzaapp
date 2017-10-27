package com.appcare.eaglesboys.deserts;

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

public class DesertsAdapter extends BaseAdapter{

    Context context;
    List<DesertsDetails> rowItems;

    public DesertsAdapter(Context context, List<DesertsDetails> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView mImgDeserts;
        TextView mTxtDesertsName;
        TextView mTxtDesertsPrice;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DesertsAdapter.ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.deserts_row, null);
            holder = new DesertsAdapter.ViewHolder();
            holder.mImgDeserts = (ImageView) convertView.findViewById(R.id.imgDeserts);
            holder.mTxtDesertsName = (TextView) convertView.findViewById(R.id.txtDesertsName);
            holder.mTxtDesertsPrice = (TextView) convertView.findViewById(R.id.txtDesertsPrice);
            convertView.setTag(holder);
        }
        else {
            holder = (DesertsAdapter.ViewHolder) convertView.getTag();
        }

        DesertsDetails rowItem = (DesertsDetails) getItem(position);

        holder.mTxtDesertsName.setText(rowItem.getDesertsName());
        holder.mTxtDesertsPrice.setText("\u20B9 "+rowItem.getDesertsPrice());
        Picasso.with(context).load(rowItem.getDesertsImage()).into(holder.mImgDeserts);
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
