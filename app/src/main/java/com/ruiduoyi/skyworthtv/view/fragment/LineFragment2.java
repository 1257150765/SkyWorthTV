package com.ruiduoyi.skyworthtv.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.ruiduoyi.skyworthtv.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Deprecated
public class LineFragment2 extends Fragment {
    private static final String TAG = LineFragment2.class.getSimpleName();
    @BindView(R.id.lc_lineChart_lineFragment2)
    LineChart lcLineChart;
    @BindView(R.id.bc_barChart_lineFragment2)
    BarChart bcBarChart;
    Unbinder unbinder;
    private View mRootView;

    public LineFragment2() {
        // Required empty public constructor
    }


    public static LineFragment2 newInstance() {
        LineFragment2 fragment = new LineFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_line_fragment2, container, false);
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, mRootView);
        initLineChart();
        initBarChart();
        return mRootView;
    }

    private void initBarChart() {
        /**************************************************************************************/
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            data.add((int) (Math.random() * 600 + 10));
        }
        final List<String> timeData = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            timeData.add((i+1)+"日");
        }
        XAxis xAxis = bcBarChart.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(data.size()+1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                value -= 1;
                Log.d(TAG, "getFormattedValue: " + value);
                if (value < 0 || value >= timeData.size()){
                    return "";
                }
                return "" + timeData.get((int) value);
            }
        });
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(data.size()+1);
        //xAxis.setDrawLabels(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAvoidFirstLastClipping(true);
        bcBarChart.getAxisRight().setEnabled(false);
        YAxis yAxis = bcBarChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setLabelCount(4);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(600);
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            entries.add(new BarEntry(i+1, data.get(i)));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "生产效率");
        barDataSet.setColor(getResources().getColor(R.color.linFragment_blue3));
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(true);
        barData.setValueTextColor(Color.WHITE);
        barData.setBarWidth(0.4f);
        bcBarChart.setData(barData);
        bcBarChart.getLegend().setEnabled(false);
        bcBarChart.getDescription().setEnabled(false);
        //bcBuLiangPercent.groupBars(0,0.5f,0);
        bcBarChart.invalidate();
    }

    private void initLineChart() {
        //初始化生产效率图（折线图）
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add((int) (Math.random() * 50 + 5));
        }
        final List<String> timeData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            timeData.add((i*2+8)+":00");
        }
        XAxis xAxis = lcLineChart.getXAxis();
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
        xAxis.setLabelRotationAngle(-45f);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(data.size() + 2, true);
        //xAxis.setDrawLabels(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        lcLineChart.getAxisRight().setEnabled(false);
        YAxis yAxis = lcLineChart.getAxisLeft();
        yAxis.setDrawGridLines(true);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setLabelCount(3);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(100);
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            entries.add(new Entry(i+1, data.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "生产效率");
        lineDataSet.setColor(getResources().getColor(R.color.linFragment_blue3));
        LineData lineData = new LineData(lineDataSet);
        lineData.setDrawValues(true);
        lineData.setValueTextColor(Color.WHITE);
        lcLineChart.setData(lineData);
        lcLineChart.getLegend().setEnabled(false);
        lcLineChart.getDescription().setEnabled(false);
        //bcBuLiangPercent.groupBars(0,0.5f,0);
        lcLineChart.invalidate();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
