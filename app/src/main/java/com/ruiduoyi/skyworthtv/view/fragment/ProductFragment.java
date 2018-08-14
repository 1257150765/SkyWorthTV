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
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBarChartBean;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBean;
import com.ruiduoyi.skyworthtv.view.adapter.ProductFragmentAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
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

    public ProductFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
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
        //bcBarChart.setPinchZoom(true);//设置按比例放缩柱状图

        /*axisLeft.setTextColor(Color.WHITE);*/
        bcBarChart.setDrawGridBackground(false);
        bcBarChart.getAxisRight().setEnabled(false);
        bcBarChart.getDescription().setEnabled(false);
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
        YAxis axisLeft = bcBarChart.getAxisLeft();
        //axisLeft.setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawLabels(false);
        axisLeft.setAxisMaximum(0);
        axisLeft.setStartAtZero(true);
        axisLeft.setAxisMaximum(500);
        Legend l = bcBarChart.getLegend();
        l.setTextSize(16);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setXOffset(20);
        l.setDrawInside(false);
        l.setTextColor(Color.WHITE);
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
        for (int i = 0; i < 5; i++) {
            yVals1.add(new BarEntry(i,data.get(i).getRjscxl()));
            yVals2.add(new BarEntry(i,data.get(i).getScxl()));
            yVals3.add(new BarEntry(i, data.get(i).getDcl()));
        }

        BarDataSet set1, set2, set3;
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
        float groupSpace = 0.34f;
        float barSpace = 0.02f; // x3 DataSet
        float barWidth = 0.2f; // x3 DataSet
        // specify the width each bar should have
        bcBarChart.getBarData().setBarWidth(barWidth);
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
                if (value < 0 || value >= 5){
                    return "";
                }
                return data.get(index).getXb();
            }
        });
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(5);
        xAxis.setLabelCount(data.size()+1,true);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        //bcBarChart.getXAxis().setAxisMaximum(startYear + bcBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        bcBarChart.groupBars(0, groupSpace, barSpace);

        bcBarChart.invalidate();
    }

    private void init() {
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
