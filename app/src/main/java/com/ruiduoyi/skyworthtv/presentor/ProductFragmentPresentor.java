package com.ruiduoyi.skyworthtv.presentor;

import android.content.Context;

import com.ruiduoyi.skyworthtv.contact.BLMXFragmentContact;
import com.ruiduoyi.skyworthtv.contact.ProductFragmentContact;
import com.ruiduoyi.skyworthtv.model.bean.BLMXFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBean;
import com.ruiduoyi.skyworthtv.model.net.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Chen on 2018-09-13.
 */

public class ProductFragmentPresentor implements ProductFragmentContact.Presentor{
    private Context context;
    private ProductFragmentContact.View view;

    public ProductFragmentPresentor(Context context, ProductFragmentContact.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadData(String xb, String funcId) {
        RetrofitManager.getProductData(xb,funcId).subscribe(new Observer<ProductFragmentBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductFragmentBean value) {
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
