package com.example.arsalankhan.malakandlevies;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Arsalan khan on 2/10/2017.
 */

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private MySingleton(Context context){
        mContext=context;
        mRequestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }


    public static synchronized MySingleton getInstance(Context context){
        if(mInstance==null){
            mInstance=new MySingleton(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> request){
        mRequestQueue.add(request);
    }
}
