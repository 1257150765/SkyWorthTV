package com.ruiduoyi.skyworthtv.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.StaffInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chen on 2018-08-07.
 */

public class StaffChartFragmentAdapter extends RecyclerView.Adapter<StaffChartFragmentAdapter.StaffChartFragmentHolder> {


    private Context mContext;
    private List<StaffInfo> mData;

    public StaffChartFragmentAdapter(Context mContext, List<StaffInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public StaffChartFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        StaffChartFragmentHolder holder = new StaffChartFragmentHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.staff_chart_fragment_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(StaffChartFragmentHolder holder, int position) {
        StaffInfo staffInfo = mData.get(position);
        holder.tvLbmLbmc.setText(staffInfo.getLbm_lbmc());
        holder.tvNRow.setText(staffInfo.getNRow()+"");
        holder.tvWkmName.setText(staffInfo.getWkm_name());
        holder.tvWkmRzrq.setText(staffInfo.getWkm_rzrq());
        holder.tvWkmWkno.setText(staffInfo.getWkm_wkno());
        holder.ivJnlJn01.setImageResource(getResource(staffInfo.getJnl_jn01()));
        holder.ivJnlJn02.setImageResource(getResource(staffInfo.getJnl_jn02()));
        holder.ivJnlJn03.setImageResource(getResource(staffInfo.getJnl_jn03()));
        holder.ivJnlJn04.setImageResource(getResource(staffInfo.getJnl_jn04()));
        holder.ivJnlJn05.setImageResource(getResource(staffInfo.getJnl_jn05()));
        holder.ivJnlJn06.setImageResource(getResource(staffInfo.getJnl_jn06()));
        holder.ivJnlJn07.setImageResource(getResource(staffInfo.getJnl_jn07()));
        holder.ivJnlJn08.setImageResource(getResource(staffInfo.getJnl_jn08()));
        holder.ivJnlJn9.setImageResource(getResource(staffInfo.getJnl_jn09()));
        holder.ivJnlJn10.setImageResource(getResource(staffInfo.getJnl_jn10()));
        holder.ivJnlJn11.setImageResource(getResource(staffInfo.getJnl_jn11()));
        holder.ivJnlJn12.setImageResource(getResource(staffInfo.getJnl_jn12()));
        holder.ivJnlJn13.setImageResource(getResource(staffInfo.getJnl_jn13()));
        holder.ivJnlJn14.setImageResource(getResource(staffInfo.getJnl_jn14()));
        holder.ivJnlJn15.setImageResource(getResource(staffInfo.getJnl_jn15()));
        holder.ivJnlJn16.setImageResource(getResource(staffInfo.getJnl_jn16()));
        holder.ivJnlJn17.setImageResource(getResource(staffInfo.getJnl_jn17()));
        holder.ivJnlJn18.setImageResource(getResource(staffInfo.getJnl_jn18()));
        holder.ivJnlJn19.setImageResource(getResource(staffInfo.getJnl_jn19()));
        holder.ivJnlJn20.setImageResource(getResource(staffInfo.getJnl_jn20()));
        holder.ivJnlJn21.setImageResource(getResource(staffInfo.getJnl_jn21()));
        holder.ivJnlJn22.setImageResource(getResource(staffInfo.getJnl_jn23()));
        holder.ivJnlJn23.setText(staffInfo.getJnl_jn22());

    }

    private int getResource(int pic) {
        int resourceId = R.mipmap.level9;
        switch (pic){
            case 1:
                resourceId = R.mipmap.level1;
                break;
            case 2:
                resourceId = R.mipmap.level2;
                break;
            case 3:
                resourceId = R.mipmap.level3;
                break;
            case 4:
                resourceId = R.mipmap.level4;
                break;

        }
        return resourceId;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<StaffInfo> ucData) {
        notifyItemRangeRemoved(0,mData.size());
        this.mData = ucData;
        //notifyItemRangeChanged(0,mData.size());
        notifyItemRangeInserted(0,mData.size());
    }


    static class StaffChartFragmentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nRow_staffChartFragment_item)
        TextView tvNRow;
        @BindView(R.id.tv_wkm_wkno_staffChartFragment_item)
        TextView tvWkmWkno;
        @BindView(R.id.tv_wkm_name_staffChartFragment_item)
        TextView tvWkmName;
        @BindView(R.id.tv_wkm_rzrq_staffChartFragment_item)
        TextView tvWkmRzrq;
        @BindView(R.id.tv_lbm_lbmc_staffChartFragment_item)
        TextView tvLbmLbmc;
        @BindView(R.id.iv_jnl_jn01_staffChartFragment_item)
        ImageView ivJnlJn01;
        @BindView(R.id.iv_jnl_jn02_staffChartFragment_item)
        ImageView ivJnlJn02;
        @BindView(R.id.iv_jnl_jn03_staffChartFragment_item)
        ImageView ivJnlJn03;
        @BindView(R.id.iv_jnl_jn04_staffChartFragment_item)
        ImageView ivJnlJn04;
        @BindView(R.id.iv_jnl_jn05_staffChartFragment_item)
        ImageView ivJnlJn05;
        @BindView(R.id.iv_jnl_jn06_staffChartFragment_item)
        ImageView ivJnlJn06;
        @BindView(R.id.iv_jnl_jn07_staffChartFragment_item)
        ImageView ivJnlJn07;
        @BindView(R.id.iv_jnl_jn08_staffChartFragment_item)
        ImageView ivJnlJn08;
        @BindView(R.id.iv_jnl_jn9_staffChartFragment_item)
        ImageView ivJnlJn9;
        @BindView(R.id.iv_jnl_jn10_staffChartFragment_item)
        ImageView ivJnlJn10;
        @BindView(R.id.iv_jnl_jn11_staffChartFragment_item)
        ImageView ivJnlJn11;
        @BindView(R.id.iv_jnl_jn12_staffChartFragment_item)
        ImageView ivJnlJn12;
        @BindView(R.id.iv_jnl_jn13_staffChartFragment_item)
        ImageView ivJnlJn13;
        @BindView(R.id.iv_jnl_jn14_staffChartFragment_item)
        ImageView ivJnlJn14;
        @BindView(R.id.iv_jnl_jn15_staffChartFragment_item)
        ImageView ivJnlJn15;
        @BindView(R.id.iv_jnl_jn16_staffChartFragment_item)
        ImageView ivJnlJn16;
        @BindView(R.id.iv_jnl_jn17_staffChartFragment_item)
        ImageView ivJnlJn17;
        @BindView(R.id.iv_jnl_jn18_staffChartFragment_item)
        ImageView ivJnlJn18;
        @BindView(R.id.iv_jnl_jn19_staffChartFragment_item)
        ImageView ivJnlJn19;
        @BindView(R.id.iv_jnl_jn20_staffChartFragment_item)
        ImageView ivJnlJn20;
        @BindView(R.id.iv_jnl_jn21_staffChartFragment_item)
        ImageView ivJnlJn21;
        @BindView(R.id.iv_jnl_jn22_staffChartFragment_item)
        ImageView ivJnlJn22;
        @BindView(R.id.iv_jnl_jn23_staffChartFragment_item)
        TextView ivJnlJn23;
        public StaffChartFragmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
