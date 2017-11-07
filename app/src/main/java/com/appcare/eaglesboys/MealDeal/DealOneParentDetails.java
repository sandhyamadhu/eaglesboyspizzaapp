package com.appcare.eaglesboys.MealDeal;

import java.util.List;

/**
 * Created by Appcare on 02-11-2017.
 */

public class DealOneParentDetails {
    private  String mDealImg;
    private String mDealName;
    private String mDealPrice;
    private List<DealOneChildDetails> childDetails=null;

    public String getmDealImg() {
        return mDealImg;
    }

    public void setmDealImg(String mDealImg) {
        this.mDealImg = mDealImg;
    }

    public String getmDealName() {
        return mDealName;
    }

    public void setmDealName(String mDealName) {
        this.mDealName = mDealName;
    }

    public String getmDealPrice() {
        return mDealPrice;
    }

    public void setmDealPrice(String mDealPrice) {
        this.mDealPrice = mDealPrice;
    }

    public List<DealOneChildDetails> getChildDetails() {
        return childDetails;
    }

    public void setChildDetails(List<DealOneChildDetails> childDetails) {
        this.childDetails = childDetails;
    }
}
