package com.yoeki.kalpnay.hrporatal.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class preferance {
    private static final String SHARED_PREF_NAME = "hrpayroll";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static preferance mInstance;
    private static Context mCtx;
    public preferance(Context context) {
        mCtx = context;
    }

    public static synchronized preferance getInstance(Context context) {
        if (mInstance == null)
        {
            mInstance = new preferance(context);
        }
        return mInstance;
    }

    public boolean saveuserLogin(String user_id)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, user_id);
        editor.apply();
        return true;
    }

    public boolean saveuserName(String username)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, username);
        editor.apply();
        return true;
    }

    public String getUserId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(USER_ID, null);
    }

    public String getUserName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(USER_NAME, null);
    }

    public void clearuserSession()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(USER_ID).commit();

        Log.e("SharedPrefManager", "session cleared...");
    }
}
