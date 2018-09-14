package com.ruiduoyi.skyworthtv.presentor;

import android.content.Context;

import com.ruiduoyi.skyworthtv.contact.PDFFragmentContact;
import com.ruiduoyi.skyworthtv.model.bean.PDFFragmentBean;
import com.ruiduoyi.skyworthtv.model.net.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Chen on 2018-09-13.
 */

public class PDFFragmentPresentor implements PDFFragmentContact.Presentor {
    private Context context;
    private PDFFragmentContact.View view;

    public PDFFragmentPresentor(Context context, PDFFragmentContact.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadData(String xb, String funcId) {
        RetrofitManager.getPdfData(xb,funcId).subscribe(new Observer<PDFFragmentBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PDFFragmentBean value) {
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
