package com.ruiduoyi.skyworthtv.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.contact.StaffChartFragmentContact;
import com.ruiduoyi.skyworthtv.model.bean.BaseBean;
import com.ruiduoyi.skyworthtv.model.bean.StaffInfo;
import com.ruiduoyi.skyworthtv.presentor.StaffChartFragmentPresentor;
import com.ruiduoyi.skyworthtv.view.adapter.StaffChartFragmentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 员工评价表
 */
public class StaffChartFragment extends Fragment implements StaffChartFragmentContact.View {
    private static final long TIME = 10000L;
    View mRootView;
    @BindView(R.id.rv_recycler_staffChartFragment)
    RecyclerView rvRecycler;
    Unbinder unbinder;
    private StaffChartFragmentPresentor presentor;
    private StaffChartFragmentAdapter adapter;
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
    private int page = 1;
    public StaffChartFragment() {
        // Required empty public constructor
    }


    public static StaffChartFragment newInstance() {
        StaffChartFragment fragment = new StaffChartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_staff_chart, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        init();
        presentor = new StaffChartFragmentPresentor(getContext(),this);
        //presentor.getWorkList(""+page);
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                presentor.getWorkList(""+page);
            }
        },0,TIME, TimeUnit.MILLISECONDS);
        return mRootView;
    }

    private void init() {
        List<StaffInfo> data = new ArrayList<StaffInfo>();
        rvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StaffChartFragmentAdapter(getContext(), data);
        rvRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(executor != null){
            executor.shutdown();
        }
    }

    @Override
    public void onGetWorkListSucceed(BaseBean<StaffInfo> data) {
        if (data.isUtStatus()) {
            int pageCount = Integer.parseInt(data.getUcMsg());
            if (page < pageCount) {
                page++;
            } else if (page >= pageCount) {
                page = 1;
            }
            adapter.setData(data.getUcData());
        }
    }
}
