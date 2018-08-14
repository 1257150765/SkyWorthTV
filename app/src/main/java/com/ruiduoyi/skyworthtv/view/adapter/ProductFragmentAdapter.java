package com.ruiduoyi.skyworthtv.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chen on 2018-08-07.
 */

public class ProductFragmentAdapter extends RecyclerView.Adapter<ProductFragmentAdapter.ProductFragmentHolder> {



    private Context mContext;
    private List<ProductFragmentBean> mData;

    public ProductFragmentAdapter(Context mContext, List<ProductFragmentBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ProductFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ProductFragmentHolder holder = new ProductFragmentHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.product_fragment_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductFragmentHolder holder, int position) {
        ProductFragmentBean bean = mData.get(position);
        holder.tvBz.setText(bean.getA1());
        holder.tvScddh.setText(bean.getA2());
        holder.tvWlbh.setText(bean.getA3());
        holder.tvCpmc.setText(bean.getA4());
        holder.tvDds.setText(bean.getA5());
        holder.tvSjwcsl.setText(bean.getA6());
        holder.tvBlps.setText(bean.getA8());
        holder.tvBll.setText(bean.getA9());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class ProductFragmentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bz_productFragment)
        TextView tvBz;
        @BindView(R.id.tv_scddh_productFragment)
        TextView tvScddh;
        @BindView(R.id.tv_wlbh_productFragment)
        TextView tvWlbh;
        @BindView(R.id.tv_cpmc_productFragment)
        TextView tvCpmc;
        @BindView(R.id.tv_dds_productFragment)
        TextView tvDds;
        @BindView(R.id.tv_sjwcsl_productFragment)
        TextView tvSjwcsl;
        @BindView(R.id.tv_blps_productFragment)
        TextView tvBlps;
        @BindView(R.id.tv_bll_productFragment)
        TextView tvBll;
        public ProductFragmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}