package com.ruiduoyi.skyworthtv.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.MainActivityBean;
import com.ruiduoyi.skyworthtv.view.fragment.BLMXFragment;
import com.ruiduoyi.skyworthtv.view.fragment.KPIFragment;
import com.ruiduoyi.skyworthtv.view.fragment.LineFragment;
import com.ruiduoyi.skyworthtv.view.fragment.LineFragment2;
import com.ruiduoyi.skyworthtv.view.fragment.PDFFragment;
import com.ruiduoyi.skyworthtv.view.fragment.ProductFragment;
import com.ruiduoyi.skyworthtv.view.fragment.StaffChartFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 控制器车间，
 * 这个Activity仅仅是一个壳，主要内容是Fragment，
 *
 */
public class ControlWorkShopStateBoardActivity extends AppCompatActivity implements BaseFragment.FragmentShowSucceedListener {
    public static final String TYPE_BLMXFRAGMENT = "KB05";
    public static final String TYPE_PRODUCTFRAGMENT = "KB06";
    public static final String TYPE_PDFFRAGMENT = "KB02";
    public static final String TYPE_LINEFRAGMENT = "KB03";
    public static final String TYPE_KPIFRAGMENT = "KB04";
    public static final String TYPE_STAFFCHARTFRAGMENT = "KB01";
    public static final String TYPE = "startType";
    public static final String DATA = "data";
    @BindView(R.id.ll_fragmentContainer_controlStateBoardBoard)
    LinearLayout llFragmentContainer;

    private String startType = "";
    private ArrayList<MainActivityBean.UcDataBean.TableBean> mBoardData;
    private List<Fragment> listAllFragment = new ArrayList<>();
    private int nextFragmentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_work_shop_state_board);
        ButterKnife.bind(this);
        mBoardData = (ArrayList<MainActivityBean.UcDataBean.TableBean>) getIntent().getSerializableExtra(DATA);
        for (MainActivityBean.UcDataBean.TableBean tableBean: mBoardData){
            BaseFragment baseFragment = null;
            switch (tableBean.getBrd_mkdm()) {
                case TYPE_BLMXFRAGMENT:
                    baseFragment = BLMXFragment.newInstance(tableBean.getBrd_devid(),tableBean.getBrd_mkdm(),tableBean.getBrd_kb_chg_time(),tableBean.getBrd_kb_refresh_time());
                    break;
                case TYPE_PRODUCTFRAGMENT:
                    baseFragment = ProductFragment.newInstance(tableBean.getBrd_devid(),tableBean.getBrd_mkdm(),tableBean.getBrd_kb_chg_time(),tableBean.getBrd_kb_refresh_time());
                    break;
                case TYPE_PDFFRAGMENT:
                    baseFragment= PDFFragment.newInstance(tableBean.getBrd_devid(),tableBean.getBrd_mkdm(),tableBean.getBrd_kb_chg_time(),tableBean.getBrd_kb_refresh_time());
                    break;
                case TYPE_LINEFRAGMENT:
                    baseFragment = (LineFragment.newInstance(tableBean.getBrd_devid(),tableBean.getBrd_mkdm(),tableBean.getBrd_kb_chg_time(),tableBean.getBrd_kb_refresh_time()));
                    break;
                case TYPE_KPIFRAGMENT:
                    baseFragment = (KPIFragment.newInstance(tableBean.getBrd_devid(),tableBean.getBrd_mkdm(),tableBean.getBrd_kb_chg_time(),tableBean.getBrd_kb_refresh_time()));
                    break;
                case TYPE_STAFFCHARTFRAGMENT:
                    baseFragment = (StaffChartFragment.newInstance(tableBean.getBrd_devid(),tableBean.getBrd_mkdm(),tableBean.getBrd_kb_chg_time(),tableBean.getBrd_kb_refresh_time()));
                    break;
            }
            listAllFragment.add(baseFragment);
            addFragment(baseFragment);
        }
        showFragment();//显示第一个Fragment
    }

    private void addFragment(Fragment target) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ll_fragmentContainer_controlStateBoardBoard, target)
                .commit();
    }
    private void showFragment() {
        if (nextFragmentIndex >= listAllFragment.size()){
            nextFragmentIndex = 0;
        }
        for (Fragment fragment:listAllFragment){
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(fragment)
                    .commit();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .show(listAllFragment.get(nextFragmentIndex))
                .commit();

        nextFragmentIndex ++;
    }


    @Override
    public void onShowSecceed() {
        showFragment();
    }
}
