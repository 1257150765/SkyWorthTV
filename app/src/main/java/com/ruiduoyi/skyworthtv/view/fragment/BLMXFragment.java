package com.ruiduoyi.skyworthtv.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.contact.BLMXFragmentContact;
import com.ruiduoyi.skyworthtv.model.bean.BLMXBean;
import com.ruiduoyi.skyworthtv.model.bean.BLMXFragmentBean;
import com.ruiduoyi.skyworthtv.presentor.BLMXFragmentPresentor;
import com.ruiduoyi.skyworthtv.view.activity.BaseFragment;
import com.ruiduoyi.skyworthtv.view.adapter.BLMXFragmentAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 车间不良明细
 */
public class BLMXFragment extends BaseFragment implements BLMXFragmentContact.View{
    private static final String TAG = BLMXFragment.class.getSimpleName();
    @BindView(R.id.rv_recycler_blmxFragment)
    RecyclerView rvRecycler;
    @BindView(R.id.tv_title_blmxFragment)
    TextView tvTitle;
    private View mRootView;
    private Unbinder unbinder;
    private BLMXFragmentPresentor presentor;

    public BLMXFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView:BLMXFragment ");
        mRootView = inflater.inflate(R.layout.fragment_blmx, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        presentor = new BLMXFragmentPresentor(getContext(),this);

        init();
        return mRootView;
    }
    public static BaseFragment newInstance(String devId,String funcId,String changeTime, String reflushTime) {
        BLMXFragment fragment = new BLMXFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.DEV_ID,devId);
        bundle.putString(BaseFragment.FUNC_ID,funcId);
        bundle.putString(BaseFragment.CHANGETIME,changeTime);
        bundle.putString(BaseFragment.REFLUSHTIME,reflushTime);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void init() {

    }

    @Override
    protected void load() {
        presentor.loadData(devId,funcId);
        super.load();
    }

    @Override
    public void onLoadDataSucceed(BLMXFragmentBean bean) {
        //SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        if (!bean.isUtStatus() || bean.getUcData().getTable().size() <=0){
            return;
        }
        List<BLMXFragmentBean.UcDataBean.TableBean> beanList = bean.getUcData().getTable();
        tvTitle.setText(beanList.get(0).getErr_rq()+"不良明细");
        rvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecycler.setAdapter(new BLMXFragmentAdapter(getContext(), beanList));
    }
}
