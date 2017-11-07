package com.appcare.eaglesboys.OrderHistory;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appcare.eaglesboys.R;

import java.util.List;

/**
 * Created by Appcare on 01-11-2017.
 */

public class OrderHistoryListAdapter extends BaseAdapter {
    Context mcontext;
    List<OrderHistoryDetails> rowItems;

    public OrderHistoryListAdapter(Context mcontext, List<OrderHistoryDetails> rowItems) {
        this.mcontext = mcontext;
        this.rowItems = rowItems;
    }
    public  class ViewHolder
    {
        TextView mTxtOderTime;
        TextView mTxtOrderCost;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderHistoryListAdapter.ViewHolder holder=null;
        LayoutInflater layoutInflater=(LayoutInflater)mcontext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
            convertView = layoutInflater.inflate(R.layout.orderhistory_row, null);
            holder=new OrderHistoryListAdapter.ViewHolder();
            holder.mTxtOderTime=(TextView) convertView.findViewById(R.id.txttimings);
            holder.mTxtOrderCost=(TextView) convertView.findViewById(R.id.txtcost);

            convertView.setTag(holder);
        }
        else {
            holder=(OrderHistoryListAdapter.ViewHolder) convertView.getTag();

        }
        OrderHistoryDetails details=(OrderHistoryDetails) getItem(position);
        holder.mTxtOderTime.setText(details.getMtiming());
        holder.mTxtOrderCost.setText(details.getMcost());


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
