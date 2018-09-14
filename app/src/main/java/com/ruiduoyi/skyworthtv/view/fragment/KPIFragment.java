package com.ruiduoyi.skyworthtv.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.PlanCompleteBean;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBarChartBean;
import com.ruiduoyi.skyworthtv.view.activity.BaseFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 控制器车间前7天kpi看板
 */
public class KPIFragment extends BaseFragment {
    private static final String TAG = KPIFragment.class.getSimpleName();
    @BindView(R.id.bc_leftTop_kpiFragment)
    BarChart bcLeftTop;
    @BindView(R.id.bc_rightTop_kpiFragment)
    BarChart bcRightTop;
    @BindView(R.id.hbc_leftBottom_kpiFragment)
    HorizontalBarChart hbcLeftBottom;
    @BindView(R.id.lc_rightBottom_kpiFragment)
    LineChart lcRightBottom;
    Unbinder unbinder;
    private View mRootView;

    public KPIFragment() {
        // Required empty public constructor
    }


    public static BaseFragment newInstance(String devId,String funcId,String changeTime, String reflushTime) {
        KPIFragment fragment = new KPIFragment();
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
        mRootView = inflater.inflate(R.layout.fragment_kpi, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        initLeftTop();
        initRightTop();
        initLeftBottom();
        initRightBottom();
        return mRootView;
    }

    private void initRightBottom() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            data.add((int) (Math.random() * 50 + 5));
        }
        final List<String> timeData = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            timeData.add((i+1)+"日");
        }
        XAxis xAxis = lcRightBottom.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(data.size()+1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d(TAG, "getFormattedValue: " + value);
                value -= 1;
                if (value < 0 || value >=timeData.size()){
                    return "";
                }
                return timeData.get((int) value);
            }
        });
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(data.size());
        //xAxis.setDrawLabels(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        lcRightBottom.getAxisRight().setEnabled(false);
        YAxis yAxis = lcRightBottom.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setLabelCount(3);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(100);
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            entries.add(new Entry(i+1, data.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "人均生产效率");
        lineDataSet.setColor(getResources().getColor(R.color.linFragment_blue3));
        lineDataSet.setDrawFilled(true);
        LineData lineData = new LineData(lineDataSet);
        lineData.setDrawValues(true);
        lineData.setValueTextColor(Color.WHITE);
        lcRightBottom.setData(lineData);
        lcRightBottom.getLegend().setEnabled(false);
        lcRightBottom.getDescription().setEnabled(false);
        //bcBuLiangPercent.groupBars(0,0.5f,0);
        lcRightBottom.invalidate();
    }

    private void initLeftBottom() {
        hbcLeftBottom.setDrawGridBackground(false);
        hbcLeftBottom.getAxisRight().setEnabled(false);
        hbcLeftBottom.getDescription().setEnabled(false);
        final List<PlanCompleteBean> data = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            PlanCompleteBean bean = new PlanCompleteBean();
            bean.setM1Number(new Random().nextFloat() * 20);
            bean.setM2Number(new Random().nextFloat() * 20);
            bean.setDate("8月"+(20+i)+"日");
            data.add(bean);
        }
        YAxis axisLeft = hbcLeftBottom.getAxisLeft();
        //axisLeft.setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(true);
        axisLeft.setTextColor(Color.WHITE);
        axisLeft.setStartAtZero(true);
        axisLeft.setAxisMaximum(0);
        axisLeft.setAxisMaximum(20);
        Legend l = hbcLeftBottom.getLegend();
        l.setTextSize(16);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setXEntrySpace(80);
        l.setFormSize(15);
        l.setTextSize(15);
        l.setDrawInside(false);
        l.setTextColor(Color.WHITE);
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        for (int i = 0; i < data.size(); i++) {
            yVals1.add(new BarEntry(i, data.get(i).getM1Number()));
            yVals2.add(new BarEntry(i, data.get(i).getM2Number()));
        }
        BarDataSet set1, set2;
        if (hbcLeftBottom.getData() != null && hbcLeftBottom.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) hbcLeftBottom.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) hbcLeftBottom.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            hbcLeftBottom.getData().notifyDataChanged();
            hbcLeftBottom.notifyDataSetChanged();
        } else {
            // create 3 DataSets
            set1 = new BarDataSet(yVals1, "M1");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "M2");
            set2.setColor(Color.rgb(164, 228, 251));

            BarData barData = new BarData(set1, set2);
            final DecimalFormat decimalFormat = new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.

            barData.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    String p = decimalFormat.format(value);//format 返回的是字符串
                    return p + "";

                }
            });
            barData.setValueTextColor(Color.WHITE);
            barData.setDrawValues(true);
            barData.setValueTextSize(10);

            //data2.setValueTypeface(mTfLight);
            hbcLeftBottom.setData(barData);
        }
        float groupSpace = 0.34f;
        float barSpace = 0.02f; // x2 DataSet
        float barWidth = 0.3f; // x2 DataSet
        // specify the width each bar should have
        hbcLeftBottom.getBarData().setBarWidth(barWidth);
        XAxis xAxis = hbcLeftBottom.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(15);
        //xAxis.setAvoidFirstLastClipping(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                //index -= 1;
                Log.d(TAG, "getFormattedValue: " + value);
                if (index < 0 || index >= data.size()) {
                    return "";
                }
                return data.get(index).getDate();
            }
        });
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(data.size());
        xAxis.setLabelCount(data.size() + 1,true);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        //bcBarChart.getXAxis().setAxisMaximum(startYear + bcBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        hbcLeftBottom.groupBars(0, groupSpace, barSpace);
        hbcLeftBottom.invalidate();
    }

    private void initRightTop() {
        bcRightTop.setDrawGridBackground(false);
        bcRightTop.getAxisRight().setEnabled(false);
        bcRightTop.getDescription().setEnabled(false);
        final List<PlanCompleteBean> data = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            PlanCompleteBean bean = new PlanCompleteBean();
            bean.setM1Number(new Random().nextFloat() * 20);
            bean.setM2Number(new Random().nextFloat() * 20);
            bean.setDate("8月"+(20+i)+"日");
            data.add(bean);
        }
        YAxis axisLeft = bcRightTop.getAxisLeft();
        //axisLeft.setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(true);
        axisLeft.setTextColor(Color.WHITE);
        axisLeft.setStartAtZero(true);
        axisLeft.setAxisMaximum(0);
        axisLeft.setAxisMaximum(20);
        Legend l = bcRightTop.getLegend();
        l.setTextSize(16);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setXEntrySpace(80);
        l.setFormSize(15);
        l.setTextSize(15);
        l.setDrawInside(false);
        l.setTextColor(Color.WHITE);
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        for (int i = 0; i < data.size(); i++) {
            yVals1.add(new BarEntry(i, data.get(i).getM1Number()));
            yVals2.add(new BarEntry(i, data.get(i).getM2Number()));
        }
        BarDataSet set1, set2;
        if (bcRightTop.getData() != null && bcRightTop.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) bcRightTop.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) bcRightTop.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            bcRightTop.getData().notifyDataChanged();
            bcRightTop.notifyDataSetChanged();
        } else {
            // create 3 DataSets
            set1 = new BarDataSet(yVals1, "M1");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "M2");
            set2.setColor(Color.rgb(164, 228, 251));

            BarData barData = new BarData(set1, set2);
            final DecimalFormat decimalFormat = new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.

            barData.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    String p = decimalFormat.format(value);//format 返回的是字符串
                    return p + "";

                }
            });
            barData.setValueTextColor(Color.WHITE);
            barData.setDrawValues(true);
            barData.setValueTextSize(10);
            //data2.setValueTypeface(mTfLight);
            bcRightTop.setData(barData);
        }
        float groupSpace = 0.34f;
        float barSpace = 0.02f; // x2 DataSet
        float barWidth = 0.3f; // x2 DataSet
        // specify the width each bar should have
        bcRightTop.getBarData().setBarWidth(barWidth);
        XAxis xAxis = bcRightTop.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(15);
        //xAxis.setAvoidFirstLastClipping(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                //index -= 1;
                Log.d(TAG, "getFormattedValue: " + value);
                if (index < 0 || index >= data.size()) {
                    return "";
                }
                return data.get(index).getDate();
            }
        });
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(data.size());
        xAxis.setLabelCount(data.size() + 1,true);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        //bcBarChart.getXAxis().setAxisMaximum(startYear + bcBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        bcRightTop.groupBars(0, groupSpace, barSpace);
        bcRightTop.invalidate();
    }

    private void initLeftTop() {
        bcLeftTop.setDrawGridBackground(false);
        bcLeftTop.getAxisRight().setEnabled(false);
        bcLeftTop.getDescription().setEnabled(false);
        final List<PlanCompleteBean> data = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            PlanCompleteBean bean = new PlanCompleteBean();
            bean.setM1Number(new Random().nextFloat() * 100);
            bean.setM2Number(new Random().nextFloat() * 100);
            bean.setDate("8月"+(20+i)+"日");
            data.add(bean);
        }
        YAxis axisLeft = bcLeftTop.getAxisLeft();
        //axisLeft.setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(true);
        axisLeft.setTextColor(Color.WHITE);
        axisLeft.setStartAtZero(true);
        axisLeft.setAxisMaximum(0);
        axisLeft.setAxisMaximum(100);
        Legend l = bcLeftTop.getLegend();
        l.setTextSize(16);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setXEntrySpace(80);
        l.setFormSize(15);
        l.setTextSize(15);
        l.setDrawInside(false);
        l.setTextColor(Color.WHITE);
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        for (int i = 0; i < data.size(); i++) {
            yVals1.add(new BarEntry(i, data.get(i).getM1Number()));
            yVals2.add(new BarEntry(i, data.get(i).getM2Number()));
        }
        BarDataSet set1, set2;
        if (bcLeftTop.getData() != null && bcLeftTop.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) bcLeftTop.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) bcLeftTop.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            bcLeftTop.getData().notifyDataChanged();
            bcLeftTop.notifyDataSetChanged();
        } else {
            // create 3 DataSets
            set1 = new BarDataSet(yVals1, "M1");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "M2");
            set2.setColor(Color.rgb(164, 228, 251));

            BarData barData = new BarData(set1, set2);
            final DecimalFormat decimalFormat = new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.

            barData.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    String p = decimalFormat.format(value);//format 返回的是字符串
                    return p + "%";

                }
            });
            barData.setValueTextColor(Color.WHITE);
            barData.setDrawValues(true);
            barData.setValueTextSize(10);
            //data2.setValueTypeface(mTfLight);
            bcLeftTop.setData(barData);
        }
        float groupSpace = 0.34f;
        float barSpace = 0.02f; // x2 DataSet
        float barWidth = 0.3f; // x2 DataSet
        // specify the width each bar should have
        bcLeftTop.getBarData().setBarWidth(barWidth);
        XAxis xAxis = bcLeftTop.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(15);
        //xAxis.setAvoidFirstLastClipping(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                //index -= 1;
                Log.d(TAG, "getFormattedValue: " + value);
                if (index < 0 || index >= data.size()) {
                    return "";
                }
                return data.get(index).getDate();
            }
        });
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(data.size());
        xAxis.setLabelCount(data.size() + 1,true);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        //bcBarChart.getXAxis().setAxisMaximum(startYear + bcBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        bcLeftTop.groupBars(0, groupSpace, barSpace);
        bcLeftTop.invalidate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
