package com.ruiduoyi.skyworthtv.model.net;


import android.util.Log;

import com.ruiduoyi.skyworthtv.model.bean.BaseBean;
import com.ruiduoyi.skyworthtv.model.bean.StaffInfo;
import com.ruiduoyi.skyworthtv.util.Config;

import java.io.IOException;

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



    interface Api{
        @GET("SmtPDADataDeal")
        Observable<BaseBean<StaffInfo>> getWorkListgin(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("key_page") String key_page);
    }
}
