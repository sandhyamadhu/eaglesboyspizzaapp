package com.appcare.eaglesboys.vegpizza;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.eaglesboys.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Appcare on 20-10-2017.
 */

public class CustomListAdapter extends ArrayAdapter<Product> {
    ArrayList<Product> products;
    Context context;
    int resource;
    Button up,down;
    CardView one,two;
    private CardView cardView;

    public CustomListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Product> products) {
        super(context, resource, products);
        this.context=context;
        this.resource=resource;
        this.products=products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.nonveglist,null,true);
            Product product=getItem(position);
            ImageView imageView=(ImageView) convertView.findViewById(R.id.nonvegimg1);
            ImageView imageView1=(ImageView) convertView.findViewById(R.id.nonvegimg);
            Picasso.with(context).load(product.getImage()).into(imageView);
            Picasso.with(context).load(product.getImage()).into(imageView1);
            TextView textView1=(TextView) convertView.findViewById(R.id.nonvegname1);
            TextView textView2=(TextView) convertView.findViewById(R.id.nonvegname2);
            textView1.setText(product.getName());
            textView1.setText(product.getName());
            TextView textView3=(TextView) convertView.findViewById(R.id.nonvegcost1);
            TextView textView4=(TextView) convertView.findViewById(R.id.nonvegcost2);
            textView3.setText(product.getPrice());
            textView4.setText(product.getPrice());
        }
        one=(CardView) convertView.findViewById(R.id.card1);
        two=(CardView) convertView.findViewById(R.id.card2);
        up=(Button) convertView.findViewById(R.id.up);
        down=(Button) convertView.findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setVisibility(View.INVISIBLE);
                one.setVisibility(View.VISIBLE);
            }
        });
        return convertView;
    }
}

