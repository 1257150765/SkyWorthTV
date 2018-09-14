/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.ruiduoyi.skyworthtv.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.MainActivityBean;
import com.ruiduoyi.skyworthtv.contact.MainActivityContact;
import com.ruiduoyi.skyworthtv.model.cache.PreferencesUtil;
import com.ruiduoyi.skyworthtv.presentor.MainActivityPresentor;
import com.ruiduoyi.skyworthtv.util.Constant;
import com.ruiduoyi.skyworthtv.view.adapter.MainActivityAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * MainActivity class that loads {@link MainFragment}.
 */
public class MainActivity extends BaseActivity implements MainActivityContact.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_OVERLAY_WINDOW = 1001;
    @BindView(R.id.rv_recycler_mainActivity)
    RecyclerView rvRecycler;
    @BindView(R.id.ctv_isLauncher)
    CheckBox ctvIsLauncher;
    private ProgressDialog downloadProgressDialog;
    private MainActivityPresentor presentor;
    private boolean isLauncher = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initOverlayWindow();
        presentor = new MainActivityPresentor(this, this);

    }

    /**
     * 初始化悬浮窗（显示横幅）
     */
    private void initOverlayWindow() {
        //startService(new Intent(MainActivity.this, OverlayWindowService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void init() {
        final PreferencesUtil preferencesUtil = new PreferencesUtil(this);
        ctvIsLauncher.setChecked(preferencesUtil.getBoolean(Constant.CACHE_DATA_NAME_ISLAUNCHER));
        ctvIsLauncher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isLauncher = isChecked;
                preferencesUtil.setBoolean(Constant.CACHE_DATA_NAME_ISLAUNCHER,isLauncher);
            }
        });
        downloadProgressDialog = new ProgressDialog(this);
        downloadProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        downloadProgressDialog.setCancelable(false);
        downloadProgressDialog.setCanceledOnTouchOutside(false);
        downloadProgressDialog.setTitle("下载中");
        downloadProgressDialog.setMax(100);


        /*Display display = getWindowManager().getDefaultDisplay(); //Activity#getWindowManager()
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.d(TAG, "init: 屏幕宽度"+width);
        Log.d(TAG, "init: 屏幕高度"+height);*/

    }

    @Deprecated
    @Override
    public void onCheckUpdateSucceed(boolean hasUpdate, final String url) {
        if (hasUpdate) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true)
                    .setMessage("发现新版本，是否更新")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presentor.update(url);

                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.create().show();
        } else {

        }

    }

    @Deprecated
    @Override
    public void onUpdate(Integer value) {
        Log.d(TAG, "MainActivity onUpdate: " + value);
        downloadProgressDialog.setProgress(value);
        downloadProgressDialog.show();
    }

    @Deprecated
    @Override
    public void onUpdateComplete() {
        if (downloadProgressDialog != null && downloadProgressDialog.isShowing()) {
            downloadProgressDialog.dismiss();
        }
        //Settings.Global.putInt(getContentResolver(), Settings.Global.INSTALL_NON_MARKET_APPS,true?1:0);
    }

    @Override
    public void onLoadDataSucceed(MainActivityBean bean) {
        if (!bean.isUtStatus()){
            return;
        }
        rvRecycler.setAdapter(new MainActivityAdapter(MainActivity.this, bean.getUcData().getTable()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false);
        rvRecycler.setLayoutManager(gridLayoutManager);
    }
}
