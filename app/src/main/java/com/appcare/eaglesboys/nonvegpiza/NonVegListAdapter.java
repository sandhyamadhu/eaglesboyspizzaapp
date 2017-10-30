package com.appcare.eaglesboys.nonvegpiza;


import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.appcare.eaglesboys.topins.ToppinsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NonVegListAdapter extends BaseExpandableListAdapter {





    private Context mContext;
    private int mColorCode = 0XFFC72F82;

    int[][] states = new int[][] {new int[] { mColorCode}};
    int[] colors = new int[] {mColorCode};
    ColorStateList mColorStateList = new ColorStateList(states, colors);
    private List<NonVegPizza> mListDataHeader; // header titles
    private ArrayList<Child> mListDataChild;

    public NonVegListAdapter(Context context, ArrayList<NonVegPizza> mNonVegPizzas, ArrayList<Child>  mListDataChild) {

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

        TextView mTxtSelectCrust = (TextView)convertView.findViewById(R.id.txtSelectCrust);
        mTxtSelectCrust.setPaintFlags(mTxtSelectCrust.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button mBtnCustomizeTopping = (Button)convertView.findViewById(R.id.btnCustomizeTopping);
        mBtnCustomizeTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuActivity)mContext).addFragment(R.id.fragmentContent,new ToppinsFragment(),true,false);
            }
        });

        LinearLayout mChildLayout = (LinearLayout) convertView.findViewById(R.id.layout1);
        LinearLayout mCrustLayout = (LinearLayout) convertView.findViewById(R.id.crustLayout);
        mChildLayout.removeAllViews();
        mCrustLayout.removeAllViews();

        Child rowItem = (Child) getChild(groupPosition,childPosition);
        int nRow = 0;
        LinearLayout myLayout = new LinearLayout(mContext);

        List<PizzaPrice> mPizzaPrices = rowItem.getPizzaPrice();
        for (PizzaPrice content : mPizzaPrices) {
            if(nRow % 2 == 0) {
                mChildLayout.addView(myLayout);
                myLayout = new LinearLayout(mContext);
                myLayout.setGravity(Gravity.CENTER);
            }

            myLayout.addView(createPizzaItemsRow(content.getCrustName()));
            nRow++;
        }

        List<SelectCrust> mCrust = rowItem.getSelectCrust();
        for (SelectCrust content : mCrust) {
            createCrustItemsRow(content.getCrustName(),mCrustLayout);
        }

        mChildLayout.addView(myLayout);

        return convertView;
    }

    private LinearLayout createPizzaItemsRow(String key) {

        LinearLayout.LayoutParams mGreeksLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mGreeksLayoutParams.weight = 1f;

        LinearLayout mainLayout = new LinearLayout(mContext);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.CENTER);
        mainLayout.setClickable(true);

        CheckBox mCheckBox = new CheckBox(mContext);
        mCheckBox.setTextColor(mColorCode);
        mCheckBox.setPadding(3,0,3,3);
        mCheckBox.setText(key);
        mCheckBox.setSingleLine(true);
        setButtonTint(mCheckBox,mColorStateList);

        mainLayout.addView(mCheckBox);
        mainLayout.setLayoutParams(mGreeksLayoutParams);

        return mainLayout;
    }

    private LinearLayout createCrustItemsRow(String key, LinearLayout mCrustLayout) {

        CheckBox mCheckBox = new CheckBox(mContext);
        mCheckBox.setTextColor(mColorCode);
        mCheckBox.setText(key);
        mCheckBox.setPadding(5,0,0,5);
        mCheckBox.setSingleLine(true);
        setButtonTint(mCheckBox,mColorStateList);

        mCrustLayout.addView(mCheckBox);
        return mCrustLayout;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mListDataHeader.get(groupPosition).getChild().size();
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

        NonVegListAdapter.ViewHolder holder ;
        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.non_veg_header, null);
            holder = new NonVegListAdapter.ViewHolder();
            holder.mImgNVOpenChild = (ImageView) convertView.findViewById(R.id.imgNVOpenChild);
            holder.mImgNonVegPizza = (ImageView) convertView.findViewById(R.id.imgpasta);
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


    public static void setButtonTint(CheckBox button, ColorStateList tint) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP && button instanceof AppCompatCheckBox) {
            ((AppCompatCheckBox) button).setBackgroundTintList(tint);
        } else {
            ViewCompat.setBackgroundTintList(button, tint);
        }
    }
}
