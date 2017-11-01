package com.appcare.eaglesboys.OrderHistory;

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

public class OHListAdapter extends BaseAdapter{
 Context mcontext;
    List<OHDetails> rowItems;

    public OHListAdapter(Context mcontext, List<OHDetails> rowItems) {
        this.mcontext = mcontext;
        this.rowItems = rowItems;
    }

    public class ViewHolder
    {
        TextView mTxtSummary;
        TextView mTxtQty;
        TextView mTxtPrice;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OHListAdapter.ViewHolder holder=null;
        LayoutInflater layoutInflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.orderhistorydetails_row,null);
            holder=new OHListAdapter.ViewHolder();
            holder.mTxtSummary=(TextView) convertView.findViewById(R.id.txtsummary);
            holder.mTxtQty=(TextView) convertView.findViewById(R.id.txtqty);
            holder.mTxtPrice=(TextView) convertView.findViewById(R.id.txtprice);

            convertView.setTag(holder);
        }
        else
        {
            holder=(OHListAdapter.ViewHolder) convertView.getTag();
        }
        OHDetails details=(OHDetails) getItem(position);
        holder.mTxtSummary.setText(details.getMsummary());
        holder.mTxtQty.setText(details.getMqty());
        holder.mTxtPrice.setText(details.getMprice());

        return convertView;
    }

    @Override
    public int getCount()  {
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
