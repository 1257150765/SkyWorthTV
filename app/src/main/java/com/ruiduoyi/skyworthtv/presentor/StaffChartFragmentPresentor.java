package com.ruiduoyi.skyworthtv.presentor;

import android.content.Context;

import com.ruiduoyi.skyworthtv.contact.StaffChartFragmentContact;
import com.ruiduoyi.skyworthtv.model.bean.BaseBean;
import com.ruiduoyi.skyworthtv.model.bean.StaffInfo;
import com.ruiduoyi.skyworthtv.model.net.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Chen on 2018-08-07.
 */

public class StaffChartFragmentPresentor implements StaffChartFragmentContact.Presentor {
    private Context context;
    private StaffChartFragmentContact.View view;

    public StaffChartFragmentPresentor(Context context, StaffChartFragmentContact.View view) {
        this.context = context;
        this.view = view;
    }



    @Override
    public void getWorkList(String key_page) {
        RetrofitManager.getWorkList(key_page).subscribe(new Observer<BaseBean<StaffInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<StaffInfo> value) {
                view.onGetWorkListSucceed(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
