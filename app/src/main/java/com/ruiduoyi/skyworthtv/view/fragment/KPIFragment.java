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
import com.ruiduoyi.skyworthtv.contact.KPIFragmentContact;
import com.ruiduoyi.skyworthtv.model.bean.KPIFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.PlanCompleteBean;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBarChartBean;
import com.ruiduoyi.skyworthtv.presentor.KPIFragmentPresentor;
import com.ruiduoyi.skyworthtv.view.activity.BaseFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 控制器车间前7天kpi看板
 */
public class KPIFragment extends BaseFragment implements KPIFragmentContact.View {
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
    private KPIFragmentPresentor presentor;
    private float yValueTextSize = 4f;


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
    protected void load() {
        super.load();
        presentor.loadData(devId,funcId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_kpi, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        presentor = new KPIFragmentPresentor(getContext(),this);
        //initLeftTop();
        //initRightTop();
        //initLeftBottom();
        //initRightBottom();
        return mRootView;
    }

    private void initRightTop(List<KPIFragmentBean.UcDataBean.Table1Bean> table1) {
        String timeKey = "";
        bcRightTop.setDrawGridBackground(false);
        bcRightTop.getAxisRight().setEnabled(false);
        bcRightTop.getDescription().setEnabled(false);
        int max = 2;
        final Map<String,ArrayList<KPIFragmentBean.UcDataBean.Table1Bean>> mapData = new HashMap<>();
        for (KPIFragmentBean.UcDataBean.Table1Bean table1Bean : table1){
            if (table1Bean.getScl_blsl() > max){
                max = table1Bean.getScl_blsl();
            }
            if (mapData.containsKey(table1Bean.getScl_xbdm())){
                mapData.get(table1Bean.getScl_xbdm()).add(table1Bean);
            }else {
                ArrayList<KPIFragmentBean.UcDataBean.Table1Bean> list = new ArrayList<>();
                list.add(table1Bean);
                mapData.put(table1Bean.getScl_xbdm(),list);
            }
        }

        YAxis axisLeft = bcRightTop.getAxisLeft();
        //axisLeft.setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(true);
        axisLeft.setTextColor(Color.WHITE);
        axisLeft.setStartAtZero(true);
        axisLeft.setAxisMaximum(0);
        axisLeft.setAxisMaximum((float) max + max/10);
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
        BarDataSet set;
        BarData barData = new BarData();
        float groupSpace = 0.34f;
        float barSpace = 0.02f;
        float barWidth = 0.3f;

        for (String xb : mapData.keySet()){
            List<BarEntry> y = new ArrayList<>();
            timeKey = xb;//用来获取X轴的日期，一个辅助变量，因为每条线的日期都是一样的，随便取一个即可
            for (int i=0; i<mapData.get(xb).size();i++){
                y.add(new BarEntry(i, (float) mapData.get(xb).get(i).getScl_blsl()));
            }
            set = new BarDataSet(y,xb);
            set.setColor(getColor(mapData.get(xb).get(0).getScl_color()));
            barData.addDataSet(set);
        }
        barWidth = 0.6f / mapData.size();
        final DecimalFormat decimalFormat = new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        /*barData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                String p = decimalFormat.format(value);//format 返回的是字符串
                return p + "%";
            }
        });*/
        barData.setValueTextColor(Color.WHITE);
        barData.setDrawValues(true);
        barData.setValueTextSize(yValueTextSize);
        //data2.setValueTypeface(mTfLight);
        bcRightTop.setData(barData);


        // specify the width each bar should have
        bcRightTop.getBarData().setBarWidth(barWidth);
        XAxis xAxis = bcRightTop.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(12);
        //xAxis.setAvoidFirstLastClipping(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        final String finalTimeKey = timeKey;
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                //index -= 1;
                //Log.d(TAG, "getFormattedValue: " + value);
                if (index < 0 || index >= mapData.get(finalTimeKey).size()) {
                    return "";
                }
                return mapData.get(finalTimeKey).get(index).getScl_rq();
            }
        });
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(mapData.get(timeKey).size());
        xAxis.setLabelCount(mapData.get(timeKey).size() + 1,true);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        //bcBarChart.getXAxis().setAxisMaximum(startYear + bcBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        if (mapData.size() >=2){
            //没有2个以上不能调用，否则BarData needs to hold at least 2 BarDataSets to allow grouping.
            bcRightTop.groupBars(0, groupSpace, barSpace);
        }

        bcRightTop.invalidate();
    }

    private void initLeftTop(List<KPIFragmentBean.UcDataBean.TableBean> table) {
        String timeKey = "";
        bcLeftTop.setDrawGridBackground(false);
        bcLeftTop.getAxisRight().setEnabled(false);
        bcLeftTop.getDescription().setEnabled(false);
        double max = 2;
        final Map<String,ArrayList<KPIFragmentBean.UcDataBean.TableBean>> mapData = new HashMap<>();
        for (KPIFragmentBean.UcDataBean.TableBean tableBean : table){
            if (tableBean.getScl_jhdclv() > max){
                max = tableBean.getScl_jhdclv();
            }
            if (mapData.containsKey(tableBean.getScl_xbdm())){
                mapData.get(tableBean.getScl_xbdm()).add(tableBean);
            }else {
                ArrayList<KPIFragmentBean.UcDataBean.TableBean> list = new ArrayList<>();
                list.add(tableBean);
                mapData.put(tableBean.getScl_xbdm(),list);
            }
        }

        YAxis axisLeft = bcLeftTop.getAxisLeft();
        //axisLeft.setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(true);
        axisLeft.setTextColor(Color.WHITE);
        axisLeft.setStartAtZero(true);
        axisLeft.setAxisMaximum(0);
        axisLeft.setAxisMaximum((float) ((float) max  + max/10));
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
        BarDataSet set;
        BarData barData = new BarData();
        float groupSpace = 0.34f;
        float barSpace = 0.02f;
        float barWidth = 0.3f;

        for (String xb : mapData.keySet()){
            List<BarEntry> y = new ArrayList<>();
            timeKey = xb;//用来获取X轴的日期，一个辅助变量，因为每条线的日期都是一样的，随便取一个即可
            for (int i=0; i<mapData.get(xb).size();i++){
                y.add(new BarEntry(i, (float) mapData.get(xb).get(i).getScl_jhdclv()));
            }
            set = new BarDataSet(y,xb);
            set.setColor(getColor(mapData.get(xb).get(0).getScl_color()));
            barData.addDataSet(set);
        }
        barWidth = 0.6f / mapData.size();
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
        barData.setValueTextSize(yValueTextSize);
        //data2.setValueTypeface(mTfLight);
        bcLeftTop.setData(barData);


        // specify the width each bar should have
        bcLeftTop.getBarData().setBarWidth(barWidth);
        XAxis xAxis = bcLeftTop.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(12);
        //xAxis.setAvoidFirstLastClipping(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        final String finalTimeKey = timeKey;
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                //index -= 1;
                //Log.d(TAG, "getFormattedValue: " + value);
                if (index < 0 || index >= mapData.get(finalTimeKey).size()) {
                    return "";
                }
                return mapData.get(finalTimeKey).get(index).getScl_rq();
            }
        });
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(mapData.get(timeKey).size());
        xAxis.setLabelCount(mapData.get(timeKey).size() + 1,true);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        //bcBarChart.getXAxis().setAxisMaximum(startYear + bcBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        if (mapData.size() >=2){
            //没有2个以上不能调用，否则BarData needs to hold at least 2 BarDataSets to allow grouping.
            bcLeftTop.groupBars(0, groupSpace, barSpace);
        }

        bcLeftTop.invalidate();
    }

    private int getColor(String scl_color) {
        int color = 0;
        try {
            color = Color.parseColor(scl_color);
        }catch (Exception e){
            color = Color.parseColor("#68F1AF");
        }
        return color;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadDataSucceed(KPIFragmentBean bean) {

        //Log.d(TAG, "onLoadDataSucceed: 数据加载成功 ");
        try {
            if (bean.isUtStatus()){
                List<KPIFragmentBean.UcDataBean.TableBean> table = bean.getUcData().getTable();
                initLeftTop(table);
                List<KPIFragmentBean.UcDataBean.Table1Bean> table1 = bean.getUcData().getTable1();
                initRightTop(table1);
                List<KPIFragmentBean.UcDataBean.Table2Bean> table2 = bean.getUcData().getTable2();
                initLeftBottom(table2);
                List<KPIFragmentBean.UcDataBean.Table3Bean> table3 = bean.getUcData().getTable3();
                initRightBottom(table3);
            }
        }catch (Exception e){

        }

    }

    private void initRightBottom(List<KPIFragmentBean.UcDataBean.Table3Bean> table3) {
        String timeKey = "";
        double max = 2;
        final Map<String,ArrayList<KPIFragmentBean.UcDataBean.Table3Bean>> mapData = new HashMap<>();
        for (KPIFragmentBean.UcDataBean.Table3Bean table3Bean : table3){
            if (table3Bean.getScl_scxlv() > max){
                max = table3Bean.getScl_scxlv();
            }
            if (mapData.containsKey(table3Bean.getScl_xbdm())){
                mapData.get(table3Bean.getScl_xbdm()).add(table3Bean);
            }else {
                ArrayList<KPIFragmentBean.UcDataBean.Table3Bean> list = new ArrayList<>();
                list.add(table3Bean);
                mapData.put(table3Bean.getScl_xbdm(),list);
            }
        }
        Legend l = lcRightBottom.getLegend();
        l.setTextSize(16);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setXEntrySpace(80);
        l.setFormSize(15);
        l.setTextSize(15);
        l.setDrawInside(false);
        l.setTextColor(Color.WHITE);

        ArrayList<ArrayList<Entry>> allY = new ArrayList<ArrayList<Entry>>();
        LineDataSet set;
        LineData lineData = new LineData();
        lineData.setDrawValues(true);
        lineData.setValueTextColor(Color.WHITE);
        for (String xb : mapData.keySet()){
            List<Entry> y = new ArrayList<>();
            timeKey = xb;//用来获取X轴的日期，一个辅助变量，因为每条线的日期都是一样的，随便取一个即可
            for (int i=0; i<mapData.get(xb).size();i++){
                y.add(new Entry(i+1, (float) mapData.get(xb).get(i).getScl_scxlv()));
            }
            set = new LineDataSet(y, xb);
            set.setColor(getColor(mapData.get(xb).get(0).getScl_color()));
            set.setDrawFilled(true);
            set.setDrawValues(true);
            set.setValueTextColor(Color.WHITE);
            set.setDrawCircleHole(true);
            set.setCircleColorHole(getColor(mapData.get(xb).get(0).getScl_color()));
            set.setCircleColor(getColor(mapData.get(xb).get(0).getScl_color()));

            lineData.addDataSet(set);
        }
        final String finalTimeKey = timeKey;
        XAxis xAxis = lcRightBottom.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum((mapData.get(timeKey).size()) + 1);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(mapData.get(timeKey).size() + 2,true);
        xAxis.setAvoidFirstLastClipping(true);
        //xAxis.setDrawLabels(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) (value - 1);
                //Log.d(TAG, "getFormattedValue: " + value);
                if (index < 0 || index >=mapData.get(finalTimeKey).size()){
                    return "";
                }
                return mapData.get(finalTimeKey).get(index).getScl_rq();
            }
        });

        lcRightBottom.getAxisRight().setEnabled(false);
        YAxis yAxis = lcRightBottom.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setLabelCount(3);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum((float) ((float) max  + max/10));
        lcRightBottom.setData(lineData);
        lcRightBottom.getLegend().setEnabled(true);
        lcRightBottom.getDescription().setEnabled(false);
        //bcBuLiangPercent.groupBars(0,0.5f,0);
        lcRightBottom.invalidate();
    }

    private void initLeftBottom(List<KPIFragmentBean.UcDataBean.Table2Bean> table2) {
        String timeKey = "";
        hbcLeftBottom.setDrawGridBackground(false);
        hbcLeftBottom.getAxisRight().setEnabled(false);
        hbcLeftBottom.getDescription().setEnabled(false);
        final Map<String,ArrayList<KPIFragmentBean.UcDataBean.Table2Bean>> mapData = new HashMap<>();
        double max = 2;
        for (KPIFragmentBean.UcDataBean.Table2Bean table2Bean : table2){
            if (table2Bean.getScl_scxlv() > max){
                max = table2Bean.getScl_scxlv();
            }
            if (mapData.containsKey(table2Bean.getScl_xbdm())){
                mapData.get(table2Bean.getScl_xbdm()).add(table2Bean);
            }else {
                ArrayList<KPIFragmentBean.UcDataBean.Table2Bean> list = new ArrayList<>();
                list.add(table2Bean);
                mapData.put(table2Bean.getScl_xbdm(),list);
            }
        }
        YAxis axisLeft = hbcLeftBottom.getAxisLeft();
        //axisLeft.setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(true);
        axisLeft.setTextColor(Color.WHITE);
        axisLeft.setStartAtZero(true);
        axisLeft.setAxisMaximum(0);
        axisLeft.setAxisMaximum((float) ((float) max  + max/10));
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
        BarDataSet set;
        BarData barData = new BarData();
        float groupSpace = 0.34f;
        float barSpace = 0.02f;
        float barWidth = 0.3f;

        for (String xb : mapData.keySet()){
            List<BarEntry> y = new ArrayList<>();
            timeKey = xb;//用来获取X轴的日期，一个辅助变量，因为每条线的日期都是一样的，随便取一个即可
            for (int i=0; i<mapData.get(xb).size();i++){
                y.add(new BarEntry(i, (float) mapData.get(xb).get(i).getScl_scxlv()));
            }
            set = new BarDataSet(y,xb);
            set.setColor(getColor(mapData.get(xb).get(0).getScl_color()));
            barData.addDataSet(set);
        }
        barWidth = 0.6f / mapData.size();

        barData.setValueTextColor(Color.WHITE);
        barData.setDrawValues(true);
        barData.setValueTextSize(6);
        //data2.setValueTypeface(mTfLight);
        hbcLeftBottom.setData(barData);
        // specify the width each bar should have
        hbcLeftBottom.getBarData().setBarWidth(barWidth);
        XAxis xAxis = hbcLeftBottom.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(15);
        //xAxis.setAvoidFirstLastClipping(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        final String finalTimeKey = timeKey;
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                //index -= 1;
                //Log.d(TAG, "getFormattedValue: " + value);
                if (index < 0 || index >= mapData.get(finalTimeKey).size()) {
                    return "";
                }
                return mapData.get(finalTimeKey).get(index).getScl_rq();
            }
        });
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum((mapData.get(finalTimeKey).size()));
        xAxis.setLabelCount(mapData.get(timeKey).size() + 1,true);
        if (mapData.size() >=2){
            //没有2个以上不能调用，否则BarData needs to hold at least 2 BarDataSets to allow grouping.
            hbcLeftBottom.groupBars(0, groupSpace, barSpace);
        }
        hbcLeftBottom.invalidate();
    }
}
