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
    protected long changeTime = 600L;//Fragment间，切换的时间
    protected long reflushTime = 30L;//Fragment内数据刷新的时间（PDF是切换页数时间，员工评价同理但每一页都会请求数据）
    protected String devId = "";//设备id，后台会
    protected boolean isHidden;
    private long time = 0L;
    protected ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
    protected String funcId = "";
    private boolean isRun = false;
    private long initalDalayTime = 1L;//延时1秒加载数据

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
            //changeTime = Long.parseLong(getArguments().getString(CHANGETIME));
            //reflushTime = Long.parseLong(getArguments().getString(REFLUSHTIME));
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

    // TODO: 2018-09-19 bug
    //如果仅有一个看板，切换看板时会隐藏自己，再显示自己，这样会导致请求两次数据
    //多个看板切换时，第二次切换开始，有闪烁情况
    // （目测是请求数据刷新界面-》隐藏—》切换看板->显示之前的数据页面-》刷新数据页面）
    //因为切换时间较长，影响较小，暂时不处理
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
