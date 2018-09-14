package com.ruiduoyi.skyworthtv.view.activity;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Chen on 2018-09-13.
 */

public class BaseFragment extends Fragment {
    protected static final String DEV_ID = "devId";
    protected static final java.lang.String FUNC_ID = "funcId";
    FragmentShowSucceedListener listener;
    protected static final String CHANGETIME = "changeTime";
    protected static final String REFLUSHTIME = "reflushTime";
    protected long changeTime = 600L;
    protected long reflushTime = 30L;
    protected String devId = "";
    protected boolean isHidden;
    private long time = 0L;
    protected ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
    protected String funcId = "";
    private boolean isRun = false;
    private long initalDalayTime = 1L;//延时3秒加载数据

    public static BaseFragment newInstance(String devId,String funcId,String changeTime, String reflushTime) {
        BaseFragment fragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DEV_ID,devId);
        bundle.putString(FUNC_ID,funcId);
        bundle.putString(CHANGETIME,changeTime);
        bundle.putString(REFLUSHTIME,reflushTime);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            devId = getArguments().getString(DEV_ID);
            funcId = getArguments().getString(FUNC_ID);
            /*changeTime = Long.parseLong(getArguments().getString(CHANGETIME));
            reflushTime = Long.parseLong(getArguments().getString(REFLUSHTIME));*/
            changeTime = 30L;
            reflushTime = 10L;
        }

        /*executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!isHidden) {
                        load();
                    }
                }catch (Exception e){

                }
            }
        },0,reflushTime, TimeUnit.SECONDS);*/
    }

    /**
     * 子Fragment必须重写此方法来加载数据
     */
     protected void load(){
         time += reflushTime;
         if (time >= changeTime && listener !=null){
             listener.onShowSecceed();
         }
     }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentShowSucceedListener){
            listener = (FragmentShowSucceedListener) activity;
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            time = 0;
            isHidden = true;
            executor.shutdown();
        }else {
                if (executor.isShutdown()){
                    executor =  new ScheduledThreadPoolExecutor(2);
                }
                executor.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //如果Fragment已准备好，则开始加载数据
                            if (isResumed()){
                                load();
                            }
                        }catch (Exception e){}
                    }

                },initalDalayTime,reflushTime, TimeUnit.SECONDS);

        }
    }


    public interface FragmentShowSucceedListener{
        void onShowSecceed();
    }

}
