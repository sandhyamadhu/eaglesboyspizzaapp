package com.appcare.eaglesboys.MealDeal;

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

/**
 * Created by Appcare on 14-11-2017.
 */

public class VegPizzaDealAdapter  extends BaseAdapter{
    Context context;
    List<VegpizzaDealDetails> rowItems;

    public VegPizzaDealAdapter(Context context, List<VegpizzaDealDetails> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }
 public class  ViewHolder
 {
     TextView  mTxtVegPizzaDealName;
     ImageView mImgVegPizzaDeal;
 }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VegPizzaDealAdapter.ViewHolder holder=null;
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
            convertView=layoutInflater.inflate (R.layout.row_vegpizzadeal,null);
            holder=new VegPizzaDealAdapter.ViewHolder ();
            holder.mTxtVegPizzaDealName=(TextView) convertView.findViewById (R.id.txtvegpizzadealName);
            holder.mImgVegPizzaDeal=(ImageView) convertView.findViewById (R.id.imgvegpizzadeal);
            convertView.setTag(holder);
        }
        else
        {
            holder=(VegPizzaDealAdapter.ViewHolder)convertView.getTag ();
        }

        VegpizzaDealDetails mDetails= (VegpizzaDealDetails) getItem (position);
        holder.mTxtVegPizzaDealName.setText (mDetails.getTxtVegPizzaDealName  ());
        Picasso.with(context).load(mDetails.getImgVegPizzaDeal ()).into(holder.mImgVegPizzaDeal);
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
