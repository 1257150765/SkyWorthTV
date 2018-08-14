package com.ruiduoyi.skyworthtv.presentor;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;

import com.ruiduoyi.skyworthtv.contact.MainActivityContact;
import com.ruiduoyi.skyworthtv.util.DownloadUtils;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.os.Environment.DIRECTORY_MUSIC;

/**
 * Created by Chen on 2018-08-10.
 */

public class MainActivityPresentor implements MainActivityContact.Presentor {
    private Context mContext;
    private MainActivityContact.View view;

    public MainActivityPresentor(Context mContext, MainActivityContact.View view) {
        this.mContext = mContext;
        this.view = view;
        //checkUpdate();
    }

    @Deprecated
    @Override
    public void checkUpdate() {
         //view.onCheckUpdateSucceed(true,"http://192.168.49.1:8082/Ruiduoyi/a.apk");
    }

    @Deprecated
    @Override
    public void update(String url) {
        final DownloadUtils downloadUtils = new DownloadUtils(mContext);
        /*File dir = mContext.getDir("aaa",  Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);

        Log.d("MainActivityPresentor", "update: "+mContext.getCacheDir().getPath());
        Log.d("MainActivityPresentor", "update: "+dir.getPath());*/
        final String s = mContext.getCacheDir().getPath();
       //final String s = Environment.getExternalStorageDirectory().getPath();
        downloadUtils.changePermission(s);

        //String s3 = "/data/local/tmp";
        downloadUtils.downloadAPK(url, s,"App.apk")
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        view.onUpdate(value);
                        Log.d("MainActivityPresentor", "MainActivityPresentor onUpdate: "+value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        view.onUpdateComplete();
                        downloadUtils.changePermission(s+"/App.apk");
                    }
                });

    }
}
