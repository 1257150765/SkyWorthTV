package com.ruiduoyi.skyworthtv.model.net;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Chen on 2018-09-13.
 */

public class PDFDownLoad {


    private static final String TAG = PDFDownLoad.class.getSimpleName();

    /**
     * 下载pdf到内存
     * @param url
     * @return
     */
    public static byte[] downLoadPDF2Memory(String url){
        long s = System.currentTimeMillis();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        byte[] file = null;
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            int contentLength = connection.getContentLength();
            byte[] b = new byte[1024];
            InputStream inputStream = connection.getInputStream();
            int len = -1;
            while((len = inputStream.read(b)) != -1){
                byteOut.write(b,0,len);
            }
            file  = byteOut.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        long e = System.currentTimeMillis();
        Log.d(TAG, "downLoadPDF2Memory: 下载pdf费时:"+(e-s)+"ms");
        return file;
    }

}
