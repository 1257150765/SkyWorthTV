package com.ruiduoyi.skyworthtv.util;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ruiduoyi.skyworthtv.R;

public class OverlayWindowService extends Service {
    private static final String TAG = OverlayWindowService.class.getSimpleName();

    public OverlayWindowService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showFloatingWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showFloatingWindow() {
        Log.d(TAG, "showFloatingWindow: ");
        // 获取WindowManager服务
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // 新建悬浮窗控件
        TextView textView = new TextView(getApplicationContext());
        textView.setText("欢迎领导莅临检查指导工作");
        textView.setTextColor(Color.RED);
        textView.setBackgroundColor(Color.parseColor("#88cccccc"));
        textView.setMarqueeRepeatLimit(-1);
        textView .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSingleLine();
        textView.setTextSize(getResources().getDimension(R.dimen.y50));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.requestFocus();
        // 设置LayoutParam
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity  = Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM;
        //设置无焦点，否则遥控器无法使用
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.x = 0;
        layoutParams.y = 0;
        // 将悬浮窗控件添加到WindowManager
        windowManager.addView(textView, layoutParams);
    }



}
