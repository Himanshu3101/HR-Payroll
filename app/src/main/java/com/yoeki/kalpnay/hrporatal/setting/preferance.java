package com.yoeki.kalpnay.hrporatal.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yoeki.kalpnay.hrporatal.Request.GetMasterInfo;

import java.lang.reflect.Type;
import java.util.List;

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

    public void saveadvancelist(List<GetMasterInfo.ListAdvanceMaster_> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public List<GetMasterInfo.ListAdvanceMaster_> getadvancelist(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<GetMasterInfo.ListAdvanceMaster_>>() {}.getType();
        return gson.fromJson(json, type);
    }


    public void saveemploye(List<GetMasterInfo.ListEmployee> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public List<GetMasterInfo.ListEmployee> getemployelist(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<GetMasterInfo.ListEmployee>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveshift(List<GetMasterInfo.ListShiftMaster> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public List<GetMasterInfo.ListShiftMaster> getshiftmaster(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<GetMasterInfo.ListShiftMaster>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void savetrainingcourseArrayList(List<GetMasterInfo.ListTraningServiceType> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public List<GetMasterInfo.ListTraningServiceType> gettrainingcourseArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<GetMasterInfo.ListTraningServiceType>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
