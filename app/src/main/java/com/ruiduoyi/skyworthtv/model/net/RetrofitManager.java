package com.ruiduoyi.skyworthtv.model.net;


import android.util.Log;

import com.ruiduoyi.skyworthtv.model.bean.KPIFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.MainActivityBean;
import com.ruiduoyi.skyworthtv.model.bean.BLMXFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.BaseBean;
import com.ruiduoyi.skyworthtv.model.bean.LineFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.NotificationBean;
import com.ruiduoyi.skyworthtv.model.bean.PDFFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.StaffInfo;
import com.ruiduoyi.skyworthtv.util.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


import static com.ruiduoyi.skyworthtv.util.Config.BASE_URL;



/**
 * Created by Chen on 2018/level4/24.
 */

public class RetrofitManager {

    static {
        init();
    }


    static Retrofit retrofit;
    public static void init(){
        Interceptor logInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
               HttpUrl.Builder builder = request.url().newBuilder()
                       .scheme(request.url().scheme())
                       .host(request.url().host());
               /*if (!"".equals(token)){
                   builder.addQueryParameter("key_tokenid",token);
               }
               if (!"".equals(companyName)){
                   builder.addQueryParameter("key_srvid",companyName);
               }*/
                Request request1 = request.newBuilder()
                        .url(builder.build())
                        .method(request.method(),request.body())
                        .build();
                //在这里获取到request后就可以做任何事情了
                Log.d("Net",request1.toString());
                Response response = chain.proceed(request1);
                MediaType mediaType = response.body().contentType();
                String content= response.body().string();
                Log.d("response",content);
                return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MyGsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static Observable<BaseBean<StaffInfo>> getWorkList(String key_page){
        return retrofit.create(Api.class)
                .getWorkListgin(Config.TYPE_INTERFACE_GET_WORKLIST,Config.KEY_SERID,key_page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }
    /*public static Observable<BaseBean<ManagerBean>> getManager(String key_xbdm){
        return retrofit.create(Api.class)
                .getManager(Config.PRGID,Config.KEY_SERID,Config.TYPE_FUNC_NAME_LINE_MANAGER,key_xbdm)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<BaseBean<PlanListBean>> getPlanList(String key_xbdm){
        return retrofit.create(Api.class)
                .getPlanList(Config.PRGID,Config.KEY_SERID,Config.TYPE_FUNC_NAME_PLAN_LIST,key_xbdm)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<BaseBean<LineFinishRateBean>> getLineFinishRate(String key_xbdm){
        return retrofit.create(Api.class)
                .getLineFinishRate(Config.PRGID,Config.KEY_SERID,Config.TYPE_FUNC_NAME_LINE_FINISH_RATE,key_xbdm)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<BaseBean<CurrPlanBean>> getCurrPlan(String key_xbdm){
        return retrofit.create(Api.class)
                .getCurrPlan(Config.PRGID,Config.KEY_SERID,Config.TYPE_FUNC_NAME_LINE_CURR_PLAN,key_xbdm)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<BaseBean<LineSDQtyBean>> getLineSDQty(String key_xbdm){
        return retrofit.create(Api.class)
                .getLineSDQty(Config.PRGID,Config.KEY_SERID,Config.TYPE_FUNC_NAME_LINE_SDQTY,key_xbdm)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<BaseBean<Last7DayCompleteBean>> getLast7DayComplete(String key_xbdm){
        return retrofit.create(Api.class)
                .getLast7DayComplete(Config.PRGID,Config.KEY_SERID,Config.TYPE_FUNC_NAME_LINE_LAST7DAY_COMPLETE,key_xbdm)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<BaseBean<LineErrorCountBean>> getLineErrorCount(String key_xbdm){
        return retrofit.create(Api.class)
                .getLineErrorCount(Config.PRGID,Config.KEY_SERID,Config.TYPE_FUNC_NAME_LINE_ERROR_COUNT,key_xbdm)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }*/
    public static Observable<LineFragmentBean> getLineData(String key_DevId,String funcId){
        return retrofit.create(Api.class)
                .getLineData(Config.PRGID,Config.KEY_SERID,key_DevId,funcId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<PDFFragmentBean> getPdfData(String key_DevId,String funcId){
        return retrofit.create(Api.class)
                .getPdfData(Config.PRGID_PDF,Config.KEY_SERID,key_DevId,funcId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<BLMXFragmentBean> getBlmxData(String key_DevId, String funcId){
        return retrofit.create(Api.class)
                .getBlmxData(Config.PRGID_BLMX,Config.KEY_SERID,key_DevId,funcId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<KPIFragmentBean> getKpiData(String key_DevId, String funcId){
        return retrofit.create(Api.class)
                .getKpiData(Config.PRGID_BLMX,Config.KEY_SERID,key_DevId,funcId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<ProductFragmentBean> getProductData(String key_DevId, String funcId){
        return retrofit.create(Api.class)
                .getProductData(Config.PRGID_BLMX,Config.KEY_SERID,key_DevId,funcId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<NotificationBean> getNotification(){
            return retrofit.create(Api.class)
                    .getNotification(Config.PRGID_BLMX,Config.KEY_SERID,Config.NOTIFICATION_DEVID,Config.NOTIFICATION_FUNCID)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

    public static Observable<MainActivityBean> getAllBoardData(){
        return retrofit.create(Api.class)
                .getAllBoardData(Config.PRGID,Config.KEY_SERID,Config.TYPE_FUNC_NAME_ALL)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }



    interface Api{
        @GET("SmtPDADataDeal")
        Observable<BaseBean<StaffInfo>> getWorkListgin(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("key_page") String key_page);

        @GET("SmtPDADataDeal")
        Observable<LineFragmentBean> getLineData(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("Key_DevId") String Key_DevId, @Query("key_FuncId")String key_FuncId);

        @GET("SmtPDADataDeal")
        Observable<PDFFragmentBean> getPdfData(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("Key_DevId") String Key_DevId, @Query("key_FuncId")String key_FuncId);

        @GET("SmtPDADataDeal")
        Observable<BLMXFragmentBean> getBlmxData(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("Key_DevId") String Key_DevId, @Query("key_FuncId")String key_FuncId);

        @GET("SmtPDADataDeal")
        Observable<KPIFragmentBean> getKpiData(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("Key_DevId") String Key_DevId, @Query("key_FuncId")String key_FuncId);

        @GET("SmtPDADataDeal")
        Observable<ProductFragmentBean> getProductData(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("Key_DevId") String Key_DevId, @Query("key_FuncId")String key_FuncId);

        @GET("SmtPDADataDeal")
        Observable<NotificationBean> getNotification(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("Key_DevId") String Key_DevId, @Query("key_FuncId")String key_FuncId);

        @GET("SmtPDADataDeal")
        Observable<MainActivityBean> getAllBoardData(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("key_FuncId") String key_FuncId);

    }
}
