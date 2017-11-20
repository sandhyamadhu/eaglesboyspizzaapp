package com.appcare.eaglesboys.placeorder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.appcare.eaglesboys.R;

import java.util.List;

/**
 * Created by Appcare on 07-11-2017.
 */

public class CartListAdapter extends BaseAdapter {
    Context mContext;
    List<CartDetails> mCartDetails;
    private boolean hideButton;


    public CartListAdapter(Context mContext, List<CartDetails> mCartDetails) {
        this.mContext = mContext;
        this.mCartDetails = mCartDetails;
    }

    @Override
    public int getCount() {
        return mCartDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return mCartDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCartDetails.indexOf(getItem(position));
    }
    public class ViewHolder{
        TextView mTxtPizzaName;
        TextView mTxtPizzaQty;
        TextView mTxtPizzaPrice;
        Button mBtnDeleteItem;
    }
    public void setButtonVisibility(boolean hideButton){
        this.hideButton = hideButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartListAdapter.ViewHolder holder=null;
        LayoutInflater layoutInflater=(LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.cartlist_row,null);
            holder=new CartListAdapter.ViewHolder();
            holder.mTxtPizzaName=(TextView) convertView.findViewById(R.id.txtPizzaName);
            holder.mTxtPizzaQty=(TextView)convertView.findViewById(R.id.edtStartPrice);
            holder.mTxtPizzaPrice=(TextView)convertView.findViewById(R.id.txtPizzaPrice);
            holder.mBtnDeleteItem=(Button)convertView.findViewById(R.id.btnDelItem);

            convertView.setTag(holder);
        }
        else
        {
            holder=(CartListAdapter.ViewHolder)convertView.getTag();
        }
        if(!hideButton){
            holder.mBtnDeleteItem.setVisibility(View.VISIBLE);
            hideButton=true;

        }
        CartDetails mDetails=(CartDetails)getItem(position);
        holder.mTxtPizzaName.setText(mDetails.getPizzaName());
        holder.mTxtPizzaQty.setText(mDetails.getPizzaQty());
        holder.mTxtPizzaPrice.setText(mDetails.getPizzaPrice());

        holder.mBtnDeleteItem.setTag(position);


        holder.mBtnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer index = (Integer) v.getTag();
                mCartDetails.remove(index.intValue());
                notifyDataSetChanged();

            }
        });



        return convertView;
    }






}
