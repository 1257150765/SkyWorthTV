package com.ruiduoyi.skyworthtv.util;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.NotificationBean;
import com.ruiduoyi.skyworthtv.model.net.RetrofitManager;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OverlayWindowService extends Service {
    private Handler handler = new Handler();
    private static final String TAG = OverlayWindowService.class.getSimpleName();
    private boolean flag = true;
    private TextView textView;
    private long refreshTime = 10L;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (flag){
                RetrofitManager.getNotification().retry(3L).subscribe(new Observer<NotificationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final NotificationBean value) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                showFloatingWindow(value);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
                try {
                    Thread.sleep(refreshTime * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    flag = false;
                }
            }
        }
    });
    public OverlayWindowService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    String display_pos = "";
    String font_color = "";
    String back_color = "";
    private void showFloatingWindow(NotificationBean value) {
        try {
            if (!value.isUtStatus() || value.getUcData().getTable().size() <=0){
                return;
            }
            NotificationBean.UcDataBean.TableBean bean = value.getUcData().getTable().get(0);
            // 获取WindowManager服务
            final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            if ("".equals(bean.getNotice_msg())){
                if (textView != null){
                    windowManager.removeView(textView);
                    textView = null;
                }
            }else {
                //仅有第一次或者内容不一样时，才改变，否则会有闪烁效果
                if (textView == null
                        || !textView.getText().toString().equals(bean.getNotice_msg())
                        || !display_pos.equals(bean.getDisplay_pos())
                        || !font_color.equals(bean.getFont_color())
                        || !back_color.equals(bean.getBack_color())
                        || refreshTime != Long.parseLong(bean.getRefresh_time())){
                    if (textView != null){
                        windowManager.removeView(textView);
                    }
                    refreshTime = Long.parseLong(bean.getRefresh_time());
                    textView = new TextView(getApplicationContext());
                    textView.setText(bean.getNotice_msg());
                    font_color = bean.getFont_color();
                    textView.setTextColor(Color.parseColor(font_color));

                    back_color = bean.getBack_color();
                    textView.setBackgroundColor(Color.parseColor(back_color));
                    textView.setMarqueeRepeatLimit(-1);
                    textView .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textView.setSingleLine();
                    textView.setTextSize(Float.parseFloat(bean.getFont_size()));
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
                    layoutParams.gravity  = Gravity.CENTER_HORIZONTAL|Gravity.TOP;
                    //设置无焦点，否则遥控器无法使用
                    layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                    layoutParams.x = 0;

                    display_pos = bean.getDisplay_pos();
                    layoutParams.y = (int) (DensityUtil.getPhoneHeight(OverlayWindowService.this) * Float.parseFloat(display_pos) /100);
                    // 将悬浮窗控件添加到WindowManager
            /*textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    windowManager.removeView(v);
                }
            });*/
                    // 新建悬浮窗控件
                    windowManager.addView(textView, layoutParams);
                }

            }
        }catch (Exception e){

        }
        //Log.d(TAG, "showFloatingWindow: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
