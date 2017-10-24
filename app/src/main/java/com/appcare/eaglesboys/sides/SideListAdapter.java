package com.appcare.eaglesboys.sides;

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

public class SideListAdapter extends BaseAdapter {
    Context context;
    List<SideDetails> rowItems;

    public SideListAdapter(Context context, List<SideDetails> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView mImgSide;
        TextView mTxtSideName;
        TextView mTxtSidePrice;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.sides_row, null);
            holder = new ViewHolder();
            holder.mImgSide = (ImageView) convertView.findViewById(R.id.imgSide);
            holder.mTxtSideName = (TextView) convertView.findViewById(R.id.txtSideName);
            holder.mTxtSidePrice = (TextView) convertView.findViewById(R.id.txtSidePrice);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        SideDetails rowItem = (SideDetails) getItem(position);

        holder.mTxtSideName.setText(rowItem.getSideName());
        holder.mTxtSidePrice.setText("\u20B9 "+rowItem.getSidePrice());
        Picasso.with(context).load(rowItem.getSideImage()).into(holder.mImgSide);
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

