package com.appcare.eaglesboys.MealDeal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Appcare on 02-11-2017.
 */

public class DealOneAdapter extends BaseExpandableListAdapter {
    Context mContext;
    List<DealOneParentDetails> mParent;
    ArrayList<DealOneChildDetails> mChild;

    public DealOneAdapter(Context mContext, List<DealOneParentDetails> mParent, ArrayList<DealOneChildDetails> mChild) {
        this.mContext = mContext;
        this.mParent = mParent;
        this.mChild = mChild;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mChild.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    private Button pizzaclick;
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        DealOneAdapter.ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.deals_row_child, null);
        }
        pizzaclick=(Button) convertView.findViewById (R.id.pizzaDealClick);
        pizzaclick.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ( (MenuActivity)mContext).addFragment (R.id.fragmentContent,new InnerMealDealFragment (),false,true);
            }
        });
//            holder = new DealOneAdapter.ViewHolder();
//            Button pizzaBtn=(Button) convertView.findViewById(R.id.pizzaDeal);
//            Button drinkBtn=(Button) convertView.findViewById(R.id.drinkDeal);
//            Button cartBtn=(Button) convertView.findViewById(R.id.btnMealDealAddCart);
//            holder.mTxtpizzaCount = (TextView) convertView.findViewById(R.id.pizzaCount);
//            convertView.setTag(holder);
//        } else {
//            holder = (DealOneAdapter.ViewHolder) convertView.getTag();
//        }
//       DealOneChildDetails mCDetails=new DealOneChildDetails();
//        holder.mTxtpizzaCount.setText(mCDetails.getTxtPizzaCount());



        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mParent.get(groupPosition).getChildDetails().size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mParent.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;

    }

    public class ViewHolder {
        ImageView mImgDealImage;
        TextView mTxtDealName;
        TextView mTxtDealPrice;
        TextView mTxtpizzaCount;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        DealOneAdapter.ViewHolder holder = null;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.deals_row_parent, null);
            holder = new DealOneAdapter.ViewHolder();
            holder.mImgDealImage = (ImageView) convertView.findViewById(R.id.imgDeal);
            holder.mTxtDealName = (TextView) convertView.findViewById(R.id.txtDealName);
            holder.mTxtDealPrice = (TextView) convertView.findViewById(R.id.txtDealPrice);
            convertView.setTag(holder);
        } else {
            holder = (DealOneAdapter.ViewHolder) convertView.getTag();
        }
        DealOneParentDetails details = (DealOneParentDetails) getGroup(groupPosition);
        holder.mTxtDealName.setText(details.getmDealName());
        holder.mTxtDealPrice.setText(details.getmDealPrice());
        Picasso.with(mContext).load(details.getmDealImg()).into(holder.mImgDealImage);

        return convertView;
    }


    @Override
    public int getGroupCount() {
        return this.mParent.size();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
