package com.ruiduoyi.skyworthtv.presentor;

import android.content.Context;

import com.ruiduoyi.skyworthtv.contact.KPIFragmentContact;
import com.ruiduoyi.skyworthtv.model.bean.KPIFragmentBean;
import com.ruiduoyi.skyworthtv.model.net.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Chen on 2018-09-18.
 */

public class KPIFragmentPresentor implements KPIFragmentContact.Presentor {

    private Context context;
    private KPIFragmentContact.View view;

    public KPIFragmentPresentor(Context context, KPIFragmentContact.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadData(String xb, String funcId) {
        RetrofitManager.getKpiData(xb,funcId).subscribe(new Observer<KPIFragmentBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(KPIFragmentBean value) {
                view.onLoadDataSucceed(value);

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
