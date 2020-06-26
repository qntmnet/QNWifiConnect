package com.qntm.qnwificonnection;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by admin on 5/21/2016.
 */
public class Httpclientclass {
    // private static final String BASE_URL = "http://api.androidhive.info/";
    // private static final String BASE_URL = "https://yupwifi.com/web-api/";
   // public static final String BASE_URL = "https://cc.qntmnet.com/webservice/";
    public static final String BASE_URL = "";
    public static final String BASE_SERVER_URL = "cc.qntmnet.com";
    public static final String VERIFY_SITE="https://zen.qntmnet.com/new-web-api/APQuickSetup/";
    final static int DEFAULT_TIMEOUT = 20 * 2000;
    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
        client.setTimeout(DEFAULT_TIMEOUT);
        client.setConnectTimeout(20*2000);
        client.setResponseTimeout(20*2000);
        Log.i("get URL",getAbsoluteUrl(url));

    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
        Log.i("post URL", getAbsoluteUrl(url));


    }
    public static void post(Context applicationContext, String url, StringEntity entity, JsonHttpResponseHandler jsonHttpResponseHandler) {
        client.post(applicationContext,getAbsoluteUrl(url), entity, "application/json", jsonHttpResponseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
