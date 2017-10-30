package com.appcare.eaglesboys.placeorder;


import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.menu.MenuFragment;

public class CartFragment extends CommonFragment implements View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mCartView = inflater.inflate(R.layout.fragment_cart,null);
        initMenuVisibility();
        initViews(mCartView);
        return mCartView;
    }


    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        initMenuVisibility();
    }

    private void initMenuVisibility() {
        handleAppBarVisibility(View.GONE);
        hideHeaderImageVisibility(View.VISIBLE);
        hideHeaderSearchVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handleAppBarVisibility(View.VISIBLE);
        hideHeaderImageVisibility(View.GONE);
        hideHeaderSearchVisibility(View.VISIBLE);
    }

    private TextView mTxtGrandTotal;
    private TextView mTxtDiscount;
    private LinearLayout mCouponLayout;
    private EditText mEdtCoupon;
    private TextView mTxtHaveACoupon;
    private TextView mTxtNetPrice;
    private TextView mTxtGSTPrice;

    private Button mBtnApply;
    private Button mBtnEdit;
    private Button mBtnMenuScreen;
    private Button mBtnPlaceOrder;
    private Button mBtnDeliveryASAP;
    private void initViews(View mChartViews){
        mEdtCoupon = (EditText)mChartViews.findViewById(R.id.edtCoupon);
        mTxtDiscount = (TextView)mChartViews.findViewById(R.id.txtDiscount);
        mTxtGrandTotal = (TextView)mChartViews.findViewById(R.id.txtGrandTotal);
        mTxtHaveACoupon = (TextView)mChartViews.findViewById(R.id.txtHaveACoupon);
        mTxtNetPrice = (TextView)mChartViews.findViewById(R.id.txtNetPrice);
        mTxtGSTPrice = (TextView)mChartViews.findViewById(R.id.txtGSTPrice);
        mCouponLayout = (LinearLayout)mChartViews.findViewById(R.id.couponLayout);

        mTxtHaveACoupon.setPaintFlags(mTxtHaveACoupon.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTxtHaveACoupon.setOnClickListener(this);

        mBtnPlaceOrder = (Button)mChartViews.findViewById(R.id.btnPlaceOrder);
        mBtnPlaceOrder.setOnClickListener(this);

        mBtnApply = (Button)mChartViews.findViewById(R.id.btnApply);
        mBtnApply.setOnClickListener(this);

        mBtnEdit = (Button)mChartViews.findViewById(R.id.btnEdit);
        mBtnEdit.setOnClickListener(this);

        mBtnDeliveryASAP = (Button)mChartViews.findViewById(R.id.btnDeliveryASAP);
        mBtnDeliveryASAP.setOnClickListener(this);

        mBtnMenuScreen = (Button)mChartViews.findViewById(R.id.btnMenuScreen);
        mBtnMenuScreen.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnPlaceOrder:
                // do your code
                break;

            case R.id.btnApply:
                // do your code
                break;

            case R.id.btnDeliveryASAP:
                // do your code
                break;
            case R.id.btnEdit:
                // do your code
                break;
            case R.id.txtHaveACoupon:
                mTxtHaveACoupon.setVisibility(View.GONE);
                mCouponLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.btnMenuScreen:
                addFragment(new MenuFragment(),false,true);
                break;

            default:
                break;
        }

    }
}
