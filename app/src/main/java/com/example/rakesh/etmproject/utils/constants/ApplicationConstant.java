package com.example.rakesh.etmproject.utils.constants;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.rakesh.etmproject.utils.db.Database;

/*
USER        Date            Version             Changes
Rakesh      13-06-2018      Initial Draft       No changes.
*/


public class ApplicationConstant extends Application {


    /*TAG for logcat*/
    public static final String TAG = ApplicationConstant.class.getSimpleName();
    /*Instance request queue*/
    private RequestQueue mRequestQueue;
    /*Making application class singleton*/
    private static ApplicationConstant mInstance;
    /*Database name*/
    public static final String DATABASE_NAME = "etm_db";
    /*Creating instance of DB*/
    public static Database db;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        db = Database.getInstance(getApplicationContext(), DATABASE_NAME);
    }

    /*
    MethodId : getInstance
    Input: Nothing
    Output: Nothing
    scope: project
    Description: Instance of application classs
    Version: 1.0
    */
    public static synchronized ApplicationConstant getInstance() {
        return mInstance;
    }

    /*
   MethodId : getRequestQueue
   Input: Nothing
   Output: RequestQueue
   scope: project
   Description: Instance of request queue
   Version: 1.0
   */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    /*
   MethodId : addToRequestQueue
   Input: Request, String
   Output: Nothing
   scope: project
   Description: Adding http request to the queue
   Version: 1.0
   */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }


    /*
   MethodId : addToRequestQueue
   Input: Request
   Output: Nothing
   scope: project
   Description: Adding http request to the queue
   Version: 1.0
   */
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


    /*
   MethodId : cancelPendingRequests
   Input: Object
   Output: Nothing
   scope: project
   Description: cancelling http request from the queue
   Version: 1.0
   */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        /*closing db connection*/
        db.close();
    }
}
