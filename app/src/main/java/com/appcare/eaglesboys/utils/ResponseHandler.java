package com.appcare.eaglesboys.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.appcare.eaglesboys.constants.AppConstants;
import com.appcare.eaglesboys.R;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;


public class ResponseHandler implements JSONResponseListener{

    private Context mContext;
    private EagleResponseListener mEagleResponseListener;

    public ResponseHandler(Context mContext, EagleResponseListener mEagleResponseListener) {
        this.mContext = mContext;
        this.mEagleResponseListener = mEagleResponseListener;

    }

    @Override
    public void handleResponse(Object response, String tag) {
        mEagleResponseListener.handleResponse(response,tag);
    }

    @Override
    public void handleError(VolleyError mVolleyError) {

        String message = "";
        if(null!=mVolleyError.networkResponse) {

            if (AppConstants.isNetworkEnabled(mContext)) {
                if (mVolleyError instanceof TimeoutError) {
                    message = mContext.getResources().getString(
                            R.string.ERROR_SOCKET_TIMEOUT);
                } else if (mVolleyError instanceof AuthFailureError) {
                    message = mContext.getResources().getString(
                            R.string.ERROR_SECURITY);
                } else if (mVolleyError instanceof NoConnectionError) {
                    message = mContext.getResources().getString(
                            R.string.ERROR_CONNECT);
                } else if (mVolleyError instanceof ParseError) {
                    message = mContext.getResources().getString(
                            R.string.ILLEGAL_ARGUMENT);
                } else {
                    message = mContext.getResources().getString(
                            R.string.ERROR_UNKNOWN);
                    mVolleyError.printStackTrace();
                }
                mEagleResponseListener.handleError(mVolleyError.networkResponse.statusCode, message);
            }else{
                mEagleResponseListener.handleError(404, mContext.getResources().getString(R.string.ERROR_CONNECT));
            }
        }
        else {
            if (mVolleyError instanceof NoConnectionError) {
                if(mVolleyError.getCause() instanceof SSLException || mVolleyError.getCause() instanceof SSLHandshakeException){

                    String[] certificaMsg = mVolleyError.getCause().getMessage().split("on:");
                    if(certificaMsg.length>1) {
                        mEagleResponseListener.handleError(404, certificaMsg[1]);
                    }else{
                        mEagleResponseListener.handleError(404,mVolleyError.getCause().getMessage());
                    }
                }else{
                    message = mContext.getResources().getString(R.string.ERROR_CONNECT);
                    mEagleResponseListener.handleError(404,message);
                }

            }else if (mVolleyError instanceof TimeoutError) {
                message = mContext.getResources().getString(
                        R.string.ERROR_SOCKET_TIMEOUT);
                mEagleResponseListener.handleError(404,message);
            }else {
                if (null != mVolleyError.getCause().getMessage()) {
                    mEagleResponseListener.handleError(404, mVolleyError.getCause().getMessage());
                } else{
                    message = mContext.getResources().getString(R.string.ERROR_UNKNOWN);
                    mEagleResponseListener.handleError(404, message);
                }
            }

        }


    }


}
