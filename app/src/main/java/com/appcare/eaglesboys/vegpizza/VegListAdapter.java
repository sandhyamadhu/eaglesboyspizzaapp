package com.appcare.eaglesboys.vegpizza;

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
import com.appcare.eaglesboys.constants.Calculations;
import com.appcare.eaglesboys.menu.MenuActivity;
import com.appcare.eaglesboys.topins.ToppinsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VegListAdapter extends BaseExpandableListAdapter {

   public String currentvalue,newvalue;

    private Context mContext;
    private int mColorCode = 0XFFC72F82;

    int[][] states = new int[][] {new int[] { mColorCode}};
    int[] colors = new int[] {mColorCode};
    ColorStateList mColorStateList = new ColorStateList(states, colors);
    private ArrayList<ClassicVeg> mListDataHeader; // header titles
    private Map<Integer, List<Child>> mListDataChild;



    public VegListAdapter(Context context, ArrayList<ClassicVeg> mVegPizzas, Map<Integer, List<Child>>  mListDataChild) {

        this.mContext = context;
        this.mListDataHeader = mVegPizzas;
        this.mListDataChild = mListDataChild;

    }

    @Override
    public List<Child> getChild(int groupPosition, int childPosititon) {
        return this.mListDataChild.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    private String mCheesePrice;
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater mLayoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mLayoutInflater.inflate(R.layout.non_veg_child, null);

        }

         Button plusBtn=(Button)convertView.findViewById (R.id.imgSidePlusPrice) ;
         Button minusBtn=(Button)convertView.findViewById (R.id.imgSideMinusPrice);
        final TextView txtBtn=(TextView)convertView.findViewById (R.id.edtStartPrice) ;
      


        plusBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                currentvalue=txtBtn.getText ().toString ();
                newvalue= Calculations.plus(currentvalue);
                txtBtn.setText (newvalue);
                notifyDataSetChanged();
            }

        });

        minusBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                currentvalue=txtBtn.getText ().toString ();
                    newvalue= Calculations.minus (currentvalue);
                    txtBtn.setText (newvalue);
                notifyDataSetChanged();


            }
        });




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

        List<Child> rowItem = getChild(groupPosition,childPosition);
        int nRow = 0;
        LinearLayout myLayout = new LinearLayout(mContext);

        List<Size> mPizzaPrices = rowItem.get(0).getSize();

        for (int i = 0; i < mPizzaPrices.size() ; i++) {
            if(nRow % 2 == 0) {
              //  mChildLayout.removeAllViews();
                mCheesePrice =  "\u20B9 "+mPizzaPrices.get(i).getExtracheese();
                mChildLayout.addView(myLayout);
                myLayout = new LinearLayout(mContext);
                myLayout.setGravity(Gravity.CENTER);

            }

            myLayout.addView(createPizzaItemsRow(mPizzaPrices.get(i).getName(),mPizzaPrices.get(i).getCrust(),mCrustLayout));
            nRow++;

        }
       mChildLayout.addView(myLayout);

        final TextView mTxtNonVegPizzaPrice = (TextView) parent.findViewById(R.id.txtNonVegPizzaPrice);

        CheckBox mChkExtraCheese = (CheckBox)convertView.findViewById(R.id.chkExtraCheese);
        mChkExtraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTxtNonVegPizzaPrice.setText(mCheesePrice);
                }else{
                    mTxtNonVegPizzaPrice.setText(mHeaderPrice);
                }
            }
        });
        return convertView;
    }

    ArrayList<CheckBox> mCheckBoxes = new ArrayList<CheckBox>();
    private int mSelectedPosition = -1;
    LinearLayout mPreviousCrustLayout;
    CheckBox previousCheckBoxl;
    private LinearLayout createPizzaItemsRow(final String key, final List<Crust> crust,final LinearLayout mCrustLayout) {

        LinearLayout.LayoutParams mGreeksLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mGreeksLayoutParams.weight = 1f;

        LinearLayout mainLayout = new LinearLayout(mContext);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.CENTER);
        mainLayout.setClickable(true);

        final CheckBox mCheckBox = new CheckBox(mContext);
        mCheckBox.setTextColor(mColorCode);
        mCheckBox.setPadding(3,0,3,3);
        mCheckBox.setText(key);
        mCheckBox.setTag("VegPizza");
        mCheckBoxes.add(mCheckBox);
        mCheckBox.setSingleLine(true);
        setButtonTint(mCheckBox,mColorStateList);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(isChecked) {
                    if(mPreviousCrustLayout==null)
                    {
                        for (int i = 0; i < crust.size(); i++) {
                            createCrustItemsRow(crust.get(i).getName(), mCrustLayout);
                        }
                    }

                    if(mPreviousCrustLayout != null) {


                        //TODO: Handle same click

                        previousCheckBoxl.setChecked(false);
                    }


                }else {
                    mCrustLayout.removeAllViews();
                }
                mPreviousCrustLayout = mCrustLayout;
                previousCheckBoxl = (CheckBox) buttonView;
            }
        });



        mainLayout.addView(mCheckBox);
        mainLayout.setLayoutParams(mGreeksLayoutParams);

        return mainLayout;
    }

    private LinearLayout createCrustItemsRow(String key, final LinearLayout mCrustLayout) {
        final CheckBox mCheckBox = new CheckBox(mContext);
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
        return mListDataChild.get(groupPosition).size();
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

    private String mHeaderPrice;
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {

        VegListAdapter.ViewHolder holder ;
        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.non_veg_header, null);
            holder = new VegListAdapter.ViewHolder();
            holder.mImgNVOpenChild = (ImageView) convertView.findViewById(R.id.imgNVOpenChild);
            holder.mImgNonVegPizza = (ImageView) convertView.findViewById(R.id.imgNonVegPizza);
            holder.mTxtNonVegPizzaName = (TextView) convertView.findViewById(R.id.txtNonVegPizzaName);
            holder.mTxtNonVegPizzaPrice = (TextView) convertView.findViewById(R.id.txtNonVegPizzaPrice);
            holder.mTxtNonVegPizzaDesc = (TextView) convertView.findViewById(R.id.txtNonVegPizzaDesc);
            convertView.setTag(holder);
        }
        else {
            holder = (VegListAdapter.ViewHolder) convertView.getTag();
        }

        ClassicVeg rowItem = (ClassicVeg) getGroup(groupPosition);
        holder.mTxtNonVegPizzaName.setText(rowItem.getName());

        mHeaderPrice =  "\u20B9 "+rowItem.getPrice();
        holder.mTxtNonVegPizzaPrice.setText(mHeaderPrice);
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
        } else{
            ViewCompat.setBackgroundTintList(button, tint);
        }
    }
}
