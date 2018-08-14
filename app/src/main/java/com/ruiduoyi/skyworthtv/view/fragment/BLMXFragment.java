package com.ruiduoyi.skyworthtv.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.BLMXBean;
import com.ruiduoyi.skyworthtv.view.adapter.BLMXFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 车间不良明细
 */
public class BLMXFragment extends Fragment {
    @BindView(R.id.rv_recycler_blmxFragment)
    RecyclerView rvRecycler;
    private View mRootView;
    private Unbinder unbinder;

    public BLMXFragment() {
        // Required empty public constructor
    }


    public static BLMXFragment newInstance() {
        BLMXFragment fragment = new BLMXFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_blmx, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        init();
        return mRootView;
    }

    private void init() {
        List<BLMXBean> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BLMXBean bean = new BLMXBean();
            bean.setA1("MIA");
            bean.setA2("700000520113" + i);
            bean.setA3("K50102054" + i);
            bean.setA4("主板 N623A3-C" + i);
            bean.setA5("70000053209801"+i);
            bean.setA6("导风板不关闭（R119撞件）导风板不关闭（R119撞件）导风板不关闭（R119撞件）导风板不关闭（R119撞件）");
            bean.setA7("非强相关");
            bean.setA8("MI外观检");
            data.add(bean);
        }
        rvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecycler.setAdapter(new BLMXFragmentAdapter(getContext(), data));
    }

}
