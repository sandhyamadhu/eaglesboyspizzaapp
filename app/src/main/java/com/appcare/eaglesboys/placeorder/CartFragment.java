package com.appcare.eaglesboys.placeorder;


import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.appcare.eaglesboys.Payment.paymentFragment;
import com.appcare.eaglesboys.R;
import com.appcare.eaglesboys.constants.CommonFragment;
import com.appcare.eaglesboys.menu.MenuFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartFragment extends CommonFragment implements View.OnClickListener{

    String colors[] = {"10.30 AM","11.30 AM","12.30 pM","1.30 PM"};
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
    private Spinner mBtnDeliveryASAP;
    private ListView mLvCartItems;
    private Button mBtnDel;
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


        mBtnDeliveryASAP = (Spinner) mChartViews.findViewById(R.id.btnDeliveryASAP);
        initSetUpTime();


        mBtnMenuScreen = (Button)mChartViews.findViewById(R.id.btnMenuScreen);
        mBtnMenuScreen.setOnClickListener(this);
        mLvCartItems=(ListView)mChartViews.findViewById(R.id.cartitemslv);
        mBtnDel=(Button) mChartViews.findViewById(R.id.btnDelItem);

        init();
    }

    private void initSetUpTime() {

        ArrayAdapter<String> mSprFilterAdapter = new ArrayAdapter<String> (getContext(), android.R.layout.simple_spinner_item,colors);
        mSprFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         mBtnDeliveryASAP.setAdapter(mSprFilterAdapter);

    }

    ArrayList<CartDetails> mCartItems=new ArrayList<>();
    String cartList="{\"cartdetails\":[{\"item\":\"pizza\",\"qty\":\"2\",\"price\":\"250\"},{\"item\":\"pizza\",\"qty\":\"2\",\"price\":\"250\"},{\"item\":\"pizza\",\"qty\":\"2\",\"price\":\"250\"},{\"item\":\"pizza\",\"qty\":\"2\",\"price\":\"250\"},{\"item\":\"pizza\",\"qty\":\"2\",\"price\":\"250\"}]}";
    private void init() {
        try {
            JSONObject mJsonObject=new JSONObject(cartList);
            JSONArray mJsonArray=mJsonObject.getJSONArray("cartdetails") ;

            for(int i=0;i<mJsonArray.length();i++)
            {
                CartDetails mCDetails=new CartDetails();
                JSONObject mInnerJsonObject=mJsonArray.getJSONObject(i);

                mCDetails.setPizzaName(mInnerJsonObject.getString("item"));
                mCDetails.setPizzaQty(mInnerJsonObject.getString("qty"));
                mCDetails.setPizzaPrice(mInnerJsonObject.getString("price"));
                mCartItems.add(mCDetails);
            }
            initsetAdp();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void initsetAdp() {
      final CartListAdapter mCartListAdapter=new CartListAdapter(mContext,mCartItems);
        mLvCartItems.setAdapter(mCartListAdapter);
        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                mCartListAdapter.setButtonVisibility(false);
               mLvCartItems.setAdapter(mCartListAdapter);
            }
        });
        mCartListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnPlaceOrder:
//                Intent i=new Intent(getActivity(), Payment.class);
//                startActivity(i);
                addFragment (R.id.fragmentContent,new paymentFragment (),false,true);
                break;

            case R.id.btnApply:
                // do your code
                break;


            case R.id.txtHaveACoupon:
                mTxtHaveACoupon.setVisibility(View.GONE);
                mCouponLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.btnMenuScreen:
                addFragment(R.id.fragmentContent, new MenuFragment(), false, true);
                break;

            default:
                break;
        }

    }
}
