package com.example.android.moviewappsingelton;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingelton {

    private RequestQueue requestQueue;
    private  static VolleySingelton mInstance;

    private VolleySingelton(Context context){
        requestQueue= Volley.newRequestQueue(context.getApplicationContext());
    }

    //only thread access at a time
    public  static synchronized VolleySingelton getmInstance(Context context){
        if (mInstance==null){
            mInstance=new VolleySingelton(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }



}
