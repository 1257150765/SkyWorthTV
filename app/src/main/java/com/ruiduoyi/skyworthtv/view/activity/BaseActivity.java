package com.ruiduoyi.skyworthtv.view.activity;

import android.support.v7.app.AppCompatActivity;

import com.ruiduoyi.skyworthtv.contact.BaseContact;

/**
 * Created by Chen on 2018-08-10.
 */

public class BaseActivity extends AppCompatActivity implements BaseContact.View{
    @Override
    public void onLoading(boolean isLoading) {
        
    }

    @Override
    public void onShowTipsDailog(String tips) {

    }
}
