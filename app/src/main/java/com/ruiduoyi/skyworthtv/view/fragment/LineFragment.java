package com.ruiduoyi.skyworthtv.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anderson.dashboardview.view.DashboardView;
import com.bumptech.glide.Glide;
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
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.contact.LineFramentContact;
import com.ruiduoyi.skyworthtv.model.bean.LineFragmentBean;
import com.ruiduoyi.skyworthtv.presentor.LineFragmentPresentor;
import com.ruiduoyi.skyworthtv.view.activity.BaseFragment;
import com.ruiduoyi.skyworthtv.view.adapter.LineFragmentPlanListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 此看板用于拉线（SMT,AOI...）
 * 看板分为9个区域：
 * 从上到下
 * 从左到右
 * 管理担当        线别              订单信息
 * 计划信息        时段产能对比      达成率
 * 检查点不良件数  时段人员效率推移  人均生产效率
 */
public class LineFragment extends BaseFragment implements LineFramentContact.View {
    private static final String TAG = LineFragment.class.getSimpleName();
    /*@BindView(R.id.pc_completePercent_lineFragment)
    PieChart pcCompletePercent; //达成率（上面圆饼图）*/
    @BindView(R.id.bc_buLiangPercent_lineFragment)
    BarChart bcBuLiangPercent; //不良率（上面柱状图）
    @BindView(R.id.bc_productPercent_lineFragment)
    BarChart bcProductPercent;//生产效率（中间条状图（中））
    @BindView(R.id.bc_barChart_lineFragment2)
    BarChart bcBarChart;//达成率（中间条状图（右））
    @BindView(R.id.bc_barChart_lineFragment3)
    BarChart bcBarChart3;//不良件数（下面条状图（左））
    @BindView(R.id.lc_personPercent_lineFragment)
    LineChart lcPersonPercent;//人员效率推移（下面折线图（中））
    @BindView(R.id.lc_lineChart_lineFragment2)
    LineChart lcLineChart;//人均生产效率（下面折线图（右））
    @BindView(R.id.iv_headPhoto1_lineFragment)
    ImageView ivHeadPhoto1;
    @BindView(R.id.tv_name1_lineFragment)
    TextView tvName1;
    @BindView(R.id.tv_phone1_lineFragment)
    TextView tvPhone1;
    @BindView(R.id.iv_headPhoto2_lineFragment)
    ImageView ivHeadPhoto2;
    @BindView(R.id.tv_name2_lineFragment)
    TextView tvName2;
    @BindView(R.id.tv_phone2_lineFragment)
    TextView tvPhone2;
    @BindView(R.id.iv_headPhoto3_lineFragment)
    ImageView ivHeadPhoto3;
    @BindView(R.id.tv_name3_lineFragment)
    TextView tvName3;
    @BindView(R.id.tv_phone3_lineFragment)
    TextView tvPhone3;
    @BindView(R.id.tv_ddbh_lineFragment)
    TextView tvDdbh;
    @BindView(R.id.tv_wldm_lineFragment)
    TextView tvWldm;
    @BindView(R.id.tv_type_lineFragment)
    TextView tvType;
    @BindView(R.id.tv_pmgg_lineFragment)
    TextView tvPmgg;
    @BindView(R.id.tv_jhsl_lineFragment)
    TextView tvJhsl;
    @BindView(R.id.tv_rksl_lineFragment)
    TextView tvRksl;
    @BindView(R.id.tv_bzrs_lineFragment)
    TextView tvBzrs;
    @BindView(R.id.tv_xscn_lineFragment)
    TextView tvXscn;
    @BindView(R.id.tv_sjry_lineFragment)
    TextView tvSjry;
    @BindView(R.id.tv_gzsl_lineFragment)
    TextView tvGzsl;
    @BindView(R.id.rv_planList_lineFragment)
    RecyclerView rvPlanList;
    @BindView(R.id.tv_jrcl_lineFragment)
    TextView tvJrcl;
    @BindView(R.id.dbv_complete_lineFragment)
    DashboardView dbvComplete;
    @BindView(R.id.tv_lineName_lineFragment)
    TextView tvLineName;


    private View mRootView;
    private Unbinder unbinder;
    private LineFragmentPresentor presentor;

    public LineFragment() {
        // Required empty public constructor
    }

    public static LineFragment newInstance() {
        LineFragment fragment = new LineFragment();
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
        mRootView = inflater.inflate(R.layout.fragment_line, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        presentor = new LineFragmentPresentor(getContext(), this);
        return mRootView;
    }

    public static BaseFragment newInstance(String devId, String funcId, String changeTime, String reflushTime) {
        LineFragment fragment = new LineFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.DEV_ID, devId);
        bundle.putString(BaseFragment.FUNC_ID, funcId);
        bundle.putString(BaseFragment.CHANGETIME, changeTime);
        bundle.putString(BaseFragment.REFLUSHTIME, reflushTime);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void load() {
        super.load();
        presentor.loadData(devId, funcId);
    }


    /**
     * 工站不良件数（条形图）
     *
     * @param beanList
     */
    private void initBottom3(List<LineFragmentBean.UcDataBean.Table6Bean> beanList) {
        final List<Integer> y = new ArrayList<>();
        final List<String> x = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < beanList.size(); i++) {
            LineFragmentBean.UcDataBean.Table6Bean bean = beanList.get(i);
            if (bean.getErr_gzsl() > max) {
                max = bean.getErr_gzsl();
            }
            y.add(bean.getErr_gzsl());
            x.add(bean.getOpr_gzmc());
        }
        XAxis xAxis = bcBarChart3.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(x.size() + 1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                value -= 1;
                //Log.d(TAG, "getFormattedValue: " + value);
                if (value < 0 || value >= x.size()) {
                    return "";
                }
                return "" + x.get((int) value);
            }
        });
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(x.size() + 1);
        //xAxis.setDrawLabels(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelRotationAngle(-30f);
        xAxis.setTextSize(6f);
        bcBarChart3.getAxisRight().setEnabled(false);
        YAxis yAxis = bcBarChart3.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setLabelCount(5);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(max + 2);
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new BarEntry(i + 1, y.get(i)));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "生产效率");
        barDataSet.setColor(getResources().getColor(R.color.linFragment_blue3));
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(true);
        barData.setValueTextColor(Color.WHITE);
        barData.setBarWidth(0.4f);
        bcBarChart3.setData(barData);
        bcBarChart3.getLegend().setEnabled(false);
        bcBarChart3.getDescription().setEnabled(false);
        //bcBuLiangPercent.groupBars(0,0.5f,0);
        bcBarChart3.invalidate();
    }

    //初始化生产效率图（折线图）
    private void initBottom2(List<LineFragmentBean.UcDataBean.Table8Bean> table8) {
        final List<Integer> y = new ArrayList<>();
        for (int i = 0; i < table8.size(); i++) {
            y.add((int) table8.get(i).getPqd_ryxv_v());
        }
        final List<String> x = new ArrayList<>();
        for (int i = 0; i < table8.size(); i++) {
            x.add(table8.get(i).getPqd_thrq());
        }
        XAxis xAxis = lcLineChart.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setLabelRotationAngle(-30f);
        xAxis.setAxisMaximum(x.size() + 1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                //Log.d(TAG, "getFormattedValue: " + value);
                value -= 1;
                if (value < 0 || value >= x.size()) {
                    return "";
                }
                return x.get((int) value);
            }
        });
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(x.size());
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
        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(i + 1, y.get(i)));
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


    private void initBottom(List<LineFragmentBean.UcDataBean.Table7Bean> table7) {
        //初始化人均效率图（折线图）
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < table7.size(); i++) {
            y.add((int) table7.get(i).getSdb_ryxv_v());
        }
        final List<String> x = new ArrayList<>();
        for (int i = 0; i < table7.size(); i++) {
            x.add(table7.get(i).getSdb_kssj());
        }
        XAxis xAxis = lcPersonPercent.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(x.size() + 1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                value -= 1;
                //Log.d(TAG, "getFormattedValue: " + value);
                if (value < 0 || value >= x.size()) {
                    return "";
                }
                return x.get((int) value);
            }
        });
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(x.size() + 1);
        //xAxis.setDrawLabels(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        lcPersonPercent.getAxisRight().setEnabled(false);
        YAxis yAxis = lcPersonPercent.getAxisLeft();
        yAxis.setDrawGridLines(true);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setLabelCount(4);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(100);
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(i + 1, y.get(i)));
        }
        LineDataSet lineDataSet = new LineDataSet(entries, "生产效率");
        lineDataSet.setColor(getResources().getColor(R.color.orange));
        lineDataSet.setDrawFilled(true);
        LineData lineData = new LineData(lineDataSet);
        lineData.setDrawValues(true);
        lineData.setValueTextColor(Color.WHITE);
        lcPersonPercent.setData(lineData);
        lcPersonPercent.getLegend().setEnabled(false);
        lcPersonPercent.getDescription().setEnabled(false);
        //bcBuLiangPercent.groupBars(0,0.5f,0);
        lcPersonPercent.invalidate();
    }

    //中间,//初始化时段产能对比图(条状图0
    private void initMiddle(List<LineFragmentBean.UcDataBean.Table4Bean> table4) {
        int max = 0;
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < table4.size(); i++) {
            int t = table4.get(i).getRkd_rksl_v();
            if (t > max) {
                max = t;
            }
            y.add(t);
        }
        final List<String> x = new ArrayList<>();
        for (int i = 0; i < table4.size(); i++) {
            x.add(table4.get(i).getSdb_kssj());
        }
        XAxis xAxis = bcProductPercent.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(table4.size() + 1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                //Log.d(TAG, "getFormattedValue: " + value);
                value -= 1;
                if (value < 0 || value >= x.size()) {
                    return "";
                }
                return x.get((int) value);
            }
        });
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(x.size() + 1);
        //xAxis.setDrawLabels(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAvoidFirstLastClipping(true);
        bcProductPercent.getAxisRight().setEnabled(false);
        YAxis yAxis = bcProductPercent.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(max + 20);
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new BarEntry(i + 1, y.get(i)));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "生产效率");
        barDataSet.setColor(getResources().getColor(R.color.linFragment_blue4));
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(true);
        barData.setValueTextColor(Color.WHITE);
        barData.setBarWidth(0.4f);
        bcProductPercent.setData(barData);
        bcProductPercent.getLegend().setEnabled(false);
        bcProductPercent.getDescription().setEnabled(false);
        //bcBuLiangPercent.groupBars(0,0.5f,0);
        bcProductPercent.invalidate();

    }

    /**
     * 达成率
     *
     * @param table5
     */
    private void initMiddle2(List<LineFragmentBean.UcDataBean.Table5Bean> table5) {
        /**************************************************************************************/
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < table5.size(); i++) {
            y.add((int) table5.get(i).getPqd_dclv());
        }
        final List<String> x = new ArrayList<>();
        for (int i = 0; i < table5.size(); i++) {
            x.add(table5.get(i).getPqd_thrq());
        }
        XAxis xAxis = bcBarChart.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setLabelRotationAngle(-30f);
        xAxis.setAxisMaximum(x.size() + 1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                value -= 1;
                //Log.d(TAG, "getFormattedValue: " + value);
                if (value < 0 || value >= x.size()) {
                    return "";
                }
                return "" + x.get((int) value);
            }
        });
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(x.size() + 1);
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
        yAxis.setAxisMaximum(100);
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new BarEntry(i + 1, y.get(i)));
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


    private void initTop(LineFragmentBean.UcDataBean.Table1Bean bean) {
        tvLineName.setText("线别:"+bean.getPqd_xbdm());
        tvJrcl.setText("今日产量\n\n" + bean.getRkd_rksl_v());
        dbvComplete.setPercent((int) bean.getPqd_dclv());
        dbvComplete.setEndColor(getResources().getColor(R.color.linFragment_blue3));
        dbvComplete.setStartColor(getResources().getColor(R.color.red));
        //初始化达成率（饼状图）
        /*List<PieEntry> entrys = new ArrayList<>();
        entrys.add(new PieEntry((float) bean.getPqd_dclv(), ""));
        entrys.add(new PieEntry((float) (100 - bean.getPqd_dclv()), ""));
        PieDataSet dataSet = new PieDataSet(entrys, "");
        ArrayList<Integer> colors = new ArrayList<Integer>();
        dataSet.setSliceSpace(3f);
        dataSet.setDrawValues(false);

        colors.add(getResources().getColor(R.color.linFragment_blue));
        colors.add(Color.parseColor("#00000000"));
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(11f);
        *//*data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

                return value+"%";
            }
        });*//*
        data.setDrawValues(true);
        data.setValueTextColor(Color.BLUE);

        pcCompletePercent.setData(data);
        //pcCompletePercent.setUsePercentValues(true);
        pcCompletePercent.setRotationAngle(90f);
        pcCompletePercent.getLegend().setEnabled(false);
        pcCompletePercent.setUsePercentValues(true);
        pcCompletePercent.setDrawCenterText(false);
        pcCompletePercent.setDrawHoleEnabled(false);
        Description description = pcCompletePercent.getDescription();
        description.setText("完成率");
        //description.setPosition(pcCompletePercent.getWidth()/2,pcCompletePercent.getHeight()/2);
        pcCompletePercent.invalidate();
*/
        /**************************************************************************/
        //初始化不良率（饼状图）
        XAxis xAxis = bcBuLiangPercent.getXAxis();
        //xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(2);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                //Log.d(TAG, "getFormattedValue: " + value);
                return "" + value;
            }
        });
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3, true);
        xAxis.setDrawLabels(false);
        //xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        bcBuLiangPercent.getAxisRight().setEnabled(false);
        YAxis yAxis = bcBuLiangPercent.getAxisLeft();
        yAxis.setDrawLabels(false);
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(false);
        double t = bean.getPqd_bllv() % 10;
        yAxis.setAxisMaximum((float) (10 * (t + 1)));

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, (float) bean.getPqd_bllv()));
        BarDataSet barDataSet = new BarDataSet(entries, "不良率");

        barDataSet.setColor(getResources().getColor(R.color.linFragment_blue3));
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(true);
        barData.setValueTextColor(Color.WHITE);

        barData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return ((int) (value)) + "%";
            }
        });
        barData.setBarWidth(0.4f);
        bcBuLiangPercent.setData(barData);
        bcBuLiangPercent.getLegend().setEnabled(false);
        bcBuLiangPercent.getDescription().setEnabled(false);
        //bcBuLiangPercent.groupBars(0,0.5f,0);

        bcBuLiangPercent.invalidate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadDataSucceed(LineFragmentBean value) {

        try {
            initTable(value.getUcData().getTable());
            initTable1(value.getUcData().getTable1());
            initTable2(value.getUcData().getTable2());
            initTable3(value.getUcData().getTable3());
            initTable4(value.getUcData().getTable4());
            initTable5(value.getUcData().getTable5());
            initTable6(value.getUcData().getTable6());
            initTable7(value.getUcData().getTable7());
            initTable8(value.getUcData().getTable8());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTable8(List<LineFragmentBean.UcDataBean.Table8Bean> table8) {
        if (table8.size() <= 0) {
            return;
        }
        initBottom2(table8);
    }

    private void initTable7(List<LineFragmentBean.UcDataBean.Table7Bean> table7) {
        if (table7.size() <= 0) {
            return;
        }
        initBottom(table7);
    }

    /**
     * 第七块区域
     *
     * @param table6
     */
    private void initTable6(List<LineFragmentBean.UcDataBean.Table6Bean> table6) {
        if (table6.size() <= 0) {
            return;
        }
        initBottom3(table6);
    }

    private void initTable5(List<LineFragmentBean.UcDataBean.Table5Bean> table5) {
        if (table5.size() <= 0) {
            return;
        }
        initMiddle2(table5);
    }

    private void initTable4(List<LineFragmentBean.UcDataBean.Table4Bean> table4) {
        if (table4.size() <= 0) {
            return;
        }
        initMiddle(table4);
    }

    /**
     * 第四块区域 计划列表
     *
     * @param table3
     */
    private void initTable3(List<LineFragmentBean.UcDataBean.Table3Bean> table3) {
        if (table3.size() <= 0) {
            return;
        }
        LineFragmentPlanListAdapter adapter = new LineFragmentPlanListAdapter(getContext(), table3);
        rvPlanList.setAdapter(adapter);
        rvPlanList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 第三块区域 当前计划
     *
     * @param table2
     */
    private void initTable2(List<LineFragmentBean.UcDataBean.Table2Bean> table2) {
        if (table2.size() <= 0) {
            return;
        }
        LineFragmentBean.UcDataBean.Table2Bean currPlanBean = table2.get(0);
        tvDdbh.setText(currPlanBean.getPlm_djbh());
        tvWldm.setText(currPlanBean.getPlm_wldm());
        tvPmgg.setText(currPlanBean.getItm_pmgg());
        tvJhsl.setText("" + currPlanBean.getPlm_jhsl());
        tvRksl.setText("" + currPlanBean.getRkd_rksl_v());
        tvBzrs.setText("" + currPlanBean.getXbm_bzrs());
        tvXscn.setText("" + currPlanBean.getPlm_xscn());
        tvSjry.setText("" + currPlanBean.getXbm_sjry());
        tvGzsl.setText("" + currPlanBean.getErr_gzsl_v());
    }

    /**
     * 第二块区域，
     *
     * @param table1
     */
    private void initTable1(List<LineFragmentBean.UcDataBean.Table1Bean> table1) {
        if (table1.size() <= 0) {
            return;
        }
        initTop(table1.get(0));
    }

    /**
     * 第一块区域，管理担当
     */
    private void initTable(List<LineFragmentBean.UcDataBean.TableBean> table) {
        if (table.size() <= 0) {
            return;
        }
        LineFragmentBean.UcDataBean.TableBean managerBean = table.get(0);
        String baseImgUrl = managerBean.getXbm_photopath();
        if(!"".equals(managerBean.getXbm_xbdm())){
            tvLineName.setText("线别:"+managerBean.getXbm_xbdm());
        }
        //特殊情况 会导致Glide加载图片出错，（Glide需要Fragmenton Attach回调后才可能load）
        if (isAdded()) {
            Glide.with(getContext()).load(baseImgUrl + "/" + managerBean.getXbm_scgz() + ".jpg").error(R.mipmap.defult_head_photo).into(ivHeadPhoto1);
            Glide.with(getContext()).load(baseImgUrl + "/" + managerBean.getXbm_pzgz() + ".jpg").error(R.mipmap.defult_head_photo).into(ivHeadPhoto2);
            Glide.with(getContext()).load(baseImgUrl + "/" + managerBean.getXbm_scbz() + ".jpg").error(R.mipmap.defult_head_photo).into(ivHeadPhoto3);
        }
        tvName1.setText(managerBean.getXbm_scgzmc());
        tvName2.setText(managerBean.getXbm_pzgzmc());
        tvName3.setText(managerBean.getXbm_scbzmc());
        tvPhone1.setText(managerBean.getXbm_scgzPhone());
        tvPhone2.setText(managerBean.getXbm_pzgzPhone());
        tvPhone3.setText(managerBean.getXbm_pzgzPhone());
    }
}
