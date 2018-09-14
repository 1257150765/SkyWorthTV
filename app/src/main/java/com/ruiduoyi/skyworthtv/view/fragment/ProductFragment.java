package com.ruiduoyi.skyworthtv.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBarChartBean;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBean;
import com.ruiduoyi.skyworthtv.view.activity.BaseFragment;
import com.ruiduoyi.skyworthtv.view.adapter.ProductFragmentAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *控制器生产看板
 */
public class ProductFragment extends BaseFragment {
    private static final String TAG = ProductFragment.class.getSimpleName();
    View mRootView;
    @BindView(R.id.rv_recycler_productFragment)
    RecyclerView rvRecycler;
    Unbinder unbinder;
    @BindView(R.id.bc_barChart_productFragment)
    BarChart bcBarChart;
    @BindView(R.id.tv_aoi_productFragment)
    TextView tvAoi;
    @BindView(R.id.tv_ict_productFragment)
    TextView tvIct;
    @BindView(R.id.tv_fct_productFragment)
    TextView tvFct;
    @BindView(R.id.tv_mi_productFragment)
    TextView tvMi;
    @BindView(R.id.tv_dct_productFragment)
    TextView tvDct;
    @BindView(R.id.tv_zhj_productFragment)
    TextView tvZhj;
    @BindView(R.id.tv_title_productFragment)
    TextView tvTitle;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance(String devId,String funcId,String changeTime, String reflushTime) {
        ProductFragment fragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.DEV_ID,devId);
        bundle.putString(BaseFragment.FUNC_ID,funcId);
        bundle.putString(BaseFragment.CHANGETIME,changeTime);
        bundle.putString(BaseFragment.REFLUSHTIME,reflushTime);
        fragment.setArguments(bundle);
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
        mRootView = inflater.inflate(R.layout.fragment_product, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        init();
        initBarChart();
        return mRootView;
    }

    private void initBarChart() {
        //
        bcBarChart.setDrawGridBackground(false);//取消网格线
        bcBarChart.getAxisRight().setEnabled(false);//取消右边Y轴
        bcBarChart.getDescription().setEnabled(false);//取消描述
        final List<ProductFragmentBarChartBean> data = new ArrayList<>();

        for (int i=1; i<=5; i++){
            ProductFragmentBarChartBean bean = new ProductFragmentBarChartBean();
            bean.setXb("MIA"+i);
            int a = new Random().nextInt(200)+200;
            bean.setScxl(a);
            bean.setRjscxl(a/5);
            bean.setDcl(new Random().nextFloat()*100);
            data.add(bean);
        }
        //Y轴(左边)
        YAxis axisLeft = bcBarChart.getAxisLeft();
        //axisLeft.setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawLabels(false);
        axisLeft.setAxisMaximum(0);
        axisLeft.setStartAtZero(true);
        axisLeft.setAxisMaximum(500);
        //图例
        Legend l = bcBarChart.getLegend();
        l.setTextSize(16);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setXEntrySpace(80);
        l.setFormSize(15);
        l.setTextSize(15);
        l.setDrawInside(false);
        l.setTextColor(Color.WHITE);
        //数据（3种数据）
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        ArrayList<BarEntry> yVals3 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            yVals1.add(new BarEntry(i,data.get(i).getRjscxl()));
            yVals2.add(new BarEntry(i,data.get(i).getScxl()));
            yVals3.add(new BarEntry(i, data.get(i).getDcl()));
        }

        BarDataSet set1, set2, set3;
        //如果未设置过数据
        if (bcBarChart.getData() != null && bcBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) bcBarChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) bcBarChart.getData().getDataSetByIndex(1);
            set3 = (BarDataSet) bcBarChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            bcBarChart.getData().notifyDataChanged();
            bcBarChart.notifyDataSetChanged();
        } else {
            // create 3 DataSets
            set1 = new BarDataSet(yVals1, "人均生产效率");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "生产效率");
            set2.setColor(Color.rgb(164, 228, 251));
            set3 = new BarDataSet(yVals3, "达成率");
            set3.setColor(Color.rgb(242, 247, 158));
            BarData barData = new BarData(set1, set2, set3);
            final DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            //设置Y轴的值的格式，（第三条数据要显示百分比）
            barData.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    switch (dataSetIndex){
                        case 0:
                        case 1:
                            return value+"";
                        case 2:
                            String p=decimalFormat.format(value);//format 返回的是字符串
                            return p+"%";
                    }
                    return "";
                }
            });
            barData.setValueTextColor(Color.WHITE);
            barData.setDrawValues(true);
            //data2.setValueTypeface(mTfLight);
            bcBarChart.setData(barData);
        }
        //组间距，柱子间距，柱子宽度，
        float groupSpace = 0.34f;
        float barSpace = 0.02f; // x3 DataSet
        float barWidth = 0.2f; // x3 DataSet
        // specify the width each bar should have
        bcBarChart.getBarData().setBarWidth(barWidth);

        //X轴
        XAxis xAxis = bcBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(16);
        //xAxis.setAvoidFirstLastClipping(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                Log.d(TAG, "getFormattedValue: "+value);
                if (value < 0 || value >= data.size()){
                    return "";
                }
                return data.get(index).getXb();
            }
        });
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(data.size());
        xAxis.setLabelCount(data.size()+1,true);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        //bcBarChart.getXAxis().setAxisMaximum(startYear + bcBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        bcBarChart.groupBars(0, groupSpace, barSpace);
        bcBarChart.invalidate();
    }


    private void init() {
        tvTitle.setText("控制器车间生产看板");
        List<ProductFragmentBean> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductFragmentBean bean = new ProductFragmentBean();
            bean.setA1("MIA");
            bean.setA2("700000520113" + i);
            bean.setA3("K50102054" + i);
            bean.setA4("主板 N623A3-C" + i);
            bean.setA5("1200");
            int c = (int) (Math.random() * 1200);
            bean.setA6("" + (1200 - c));
            bean.setA7("");
            bean.setA8("" + c);

            bean.setA9((int) ((((float) c * 1.0f) / 1200.0f) * 100)+"%");
            data.add(bean);
        }
        rvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecycler.setAdapter(new ProductFragmentAdapter(getContext(), data));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
