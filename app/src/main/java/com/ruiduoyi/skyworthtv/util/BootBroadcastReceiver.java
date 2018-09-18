package com.ruiduoyi.skyworthtv.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ruiduoyi.skyworthtv.view.activity.MainActivity;


/**
 * Created by Chen on 2018/4/24.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mBootIntent = new Intent(context, MainActivity.class);
        //下面这句话必须加上才能开机自动运行app的界面
        mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mBootIntent);
    }
}
