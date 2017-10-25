package com.appcare.eaglesboys.nonvegpiza;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.sides.SideDetails;
import com.appcare.eaglesboys.sides.SideListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NonVegListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<NonVegPizza> mListDataHeader; // header titles
    private ArrayList<String> mListDataChild;

    public NonVegListAdapter(Context context, ArrayList<NonVegPizza> mNonVegPizzas, ArrayList<String>  mListDataChild) {

        this.mContext = context;
        this.mListDataHeader = mNonVegPizzas;
        this.mListDataChild = mListDataChild;

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.mListDataChild.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater mLayoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mLayoutInflater.inflate(R.layout.non_veg_child, null);
        }
/*
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);*/
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mListDataChild.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mListDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView mImgNVOpenChild;
        ImageView mImgNonVegPizza;
        TextView mTxtNonVegPizzaName;
        TextView mTxtNonVegPizzaPrice;
        TextView mTxtNonVegPizzaDesc;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {

        NonVegListAdapter.ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.non_veg_header, null);
            holder = new NonVegListAdapter.ViewHolder();
            holder.mImgNVOpenChild = (ImageView) convertView.findViewById(R.id.imgNVOpenChild);
            holder.mImgNonVegPizza = (ImageView) convertView.findViewById(R.id.imgNonVegPizza);
            holder.mTxtNonVegPizzaName = (TextView) convertView.findViewById(R.id.txtNonVegPizzaName);
            holder.mTxtNonVegPizzaPrice = (TextView) convertView.findViewById(R.id.txtNonVegPizzaPrice);
            holder.mTxtNonVegPizzaDesc = (TextView) convertView.findViewById(R.id.txtNonVegPizzaDesc);
            convertView.setTag(holder);
        }
        else {
            holder = (NonVegListAdapter.ViewHolder) convertView.getTag();
        }

        NonVegPizza rowItem = (NonVegPizza) getGroup(groupPosition);
        holder.mTxtNonVegPizzaName.setText(rowItem.getName());
        holder.mTxtNonVegPizzaPrice.setText("\u20B9 "+rowItem.getPrice());
        holder.mTxtNonVegPizzaDesc.setText(rowItem.getDescription());
        Picasso.with(mContext).load(rowItem.getImage()).into(holder.mImgNonVegPizza);
        holder.mImgNVOpenChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
