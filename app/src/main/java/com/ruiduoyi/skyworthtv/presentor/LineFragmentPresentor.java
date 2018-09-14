package com.ruiduoyi.skyworthtv.presentor;

import android.content.Context;

import com.ruiduoyi.skyworthtv.contact.LineFramentContact;
import com.ruiduoyi.skyworthtv.model.bean.LineFragmentBean;
import com.ruiduoyi.skyworthtv.model.net.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Chen on 2018-09-11.
 */

public class LineFragmentPresentor implements LineFramentContact.Presentor {
    private Context context;

    public LineFragmentPresentor(Context context, LineFramentContact.View view) {
        this.context = context;
        this.view = view;
        init();
    }

    private void init() {

    }

    private LineFramentContact.View view;

    /*@Override
    public void loadManager(String xb) {
        RetrofitManager.getManager(xb).subscribe(new Observer<BaseBean<ManagerBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<ManagerBean> value) {
                view.onLoadManagerSucceed(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadPlanList(String xb) {
        RetrofitManager.getPlanList(xb).subscribe(new Observer<BaseBean<PlanListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<PlanListBean> value) {
                view.onLoadPlanSucceed(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadCurrPlan(String xb) {
        RetrofitManager.getCurrPlan(xb).subscribe(new Observer<BaseBean<CurrPlanBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<CurrPlanBean> value) {
                view.onLoadCurrPlan(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadLineFinishRate(String xb) {
        RetrofitManager.getLineFinishRate(xb).subscribe(new Observer<BaseBean<LineFinishRateBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<LineFinishRateBean> value) {
                view.onLoadLineFinishRate(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadLineSDQty(String xb) {
        RetrofitManager.getLineSDQty(xb).subscribe(new Observer<BaseBean<LineSDQtyBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<LineSDQtyBean> value) {
                view.onLoadLineSDQty(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadLast7DayComplete(String xb) {
        RetrofitManager.getLast7DayComplete(xb).subscribe(new Observer<BaseBean<Last7DayCompleteBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<Last7DayCompleteBean> value) {
                view.onLoadLast7DayComplete(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadLineErrorCount(String xb) {
        RetrofitManager.getLineErrorCount(xb).subscribe(new Observer<BaseBean<LineErrorCountBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<LineErrorCountBean> value) {
                view.onLoadLineErrorCount(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }*/

    @Override
    public void loadData(String xb, String funcId) {
        RetrofitManager.getLineData(xb,funcId).subscribe(new Observer<LineFragmentBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LineFragmentBean value) {
                if (value.isUtStatus()){
                    view.onLoadDataSucceed(value);
                }
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
