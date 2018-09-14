package com.ruiduoyi.skyworthtv.model.cache;

import android.content.Context;

/**
 * Created by Chen on 2018-08-21.
 */

public class PreferencesUtil {
    private Context mContext;
    private static final String  DATA_NAME = "data";

    public PreferencesUtil(Context mContext) {
        this.mContext = mContext;
    }
    public boolean getBoolean(String name){
        return mContext.getSharedPreferences(DATA_NAME,Context.MODE_PRIVATE).getBoolean(name,false);
    }
    public void setBoolean(String name,boolean value){
         mContext.getSharedPreferences(DATA_NAME,Context.MODE_PRIVATE).edit().putBoolean(name,value).commit();
    }
}
