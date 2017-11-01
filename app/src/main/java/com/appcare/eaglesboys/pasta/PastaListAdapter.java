package com.appcare.eaglesboys.pasta;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.eaglesboys.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Appcare on 27-10-2017.
 */

public class PastaListAdapter extends BaseAdapter {
    Context context;
    List<PastaDetails> rowItems;
    String currentitem,newitem;
    public PastaListAdapter(Context context, List<PastaDetails> Items) {
        this.context = context;
        this.rowItems = Items;
    }
    public  class ViewHolder{
        ImageView mImgPasta;
        TextView mTxtPastaName;
        TextView mTxtPastaPrice;
        TextView mTxtPastaDesc;
        Button plus,minus;
        TextView price;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PastaListAdapter.ViewHolder holder=null;
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
            convertView = layoutInflater.inflate(R.layout.pasta_row, null);
            holder=new PastaListAdapter.ViewHolder();
            holder.mImgPasta = (ImageView) convertView.findViewById(R.id.imgpasta);
            holder.mTxtPastaName = (TextView) convertView.findViewById(R.id.txtPastaName);
            holder.mTxtPastaPrice = (TextView) convertView.findViewById(R.id.txtPastaPrice);
            holder.mTxtPastaDesc=(TextView) convertView.findViewById(R.id.txtPastaDesc);
            holder.plus=(Button) convertView.findViewById(R.id.imgBeveragesPlusPrice);
            holder.minus=(Button) convertView.findViewById(R.id.imgBeveragesMinusPrice);
            holder.price=(TextView) convertView.findViewById(R.id.edtStartPrice);


            convertView.setTag(holder);

        }
        else {
            holder = (PastaListAdapter.ViewHolder) convertView.getTag();
        }

        PastaDetails rowItem = (PastaDetails) getItem(position);
        holder.mTxtPastaName.setText(rowItem.getmPastaName());
        holder.mTxtPastaPrice.setText("\u20B9 "+rowItem.getmPastaPrice());
        holder.mTxtPastaDesc.setText(rowItem.getmPastaDesc());
        Picasso.with(context).load(rowItem.getmPastaImage()).into(holder.mImgPasta);





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
