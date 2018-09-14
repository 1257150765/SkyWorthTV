package com.ruiduoyi.skyworthtv.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.BLMXBean;
import com.ruiduoyi.skyworthtv.model.bean.BLMXFragmentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chen on 2018-08-07.
 */

public class BLMXFragmentAdapter extends RecyclerView.Adapter<BLMXFragmentAdapter.BLMXFragmentHolder> {



    private Context mContext;
    private List<BLMXFragmentBean.UcDataBean.TableBean> mData;

    public BLMXFragmentAdapter(Context mContext, List<BLMXFragmentBean.UcDataBean.TableBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public BLMXFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BLMXFragmentHolder holder = new BLMXFragmentHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.blmx_fragment_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(BLMXFragmentHolder holder, int position) {
        BLMXFragmentBean.UcDataBean.TableBean bean = mData.get(position);
        holder.tvBz.setText(bean.getErr_xbdm());
        holder.tvScddh.setText(bean.getErr_plm_djbh());
        holder.tvWlbh.setText(bean.getPlm_wldm());
        holder.tvCpmc.setText(bean.getItm_pmgg());
        holder.tvTmh.setText(bean.getErr_bltm());
        holder.tvQxms.setText(bean.getErr_gzyyms());
        holder.tvGzlb.setText(bean.getErr_gzdlms());
        holder.tvJcdw.setText(bean.getOpr_gzmc());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class BLMXFragmentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bz_blmxFragment)
        TextView tvBz;
        @BindView(R.id.tv_scddh_blmxFragment)
        TextView tvScddh;
        @BindView(R.id.tv_wlbh_blmxFragment)
        TextView tvWlbh;
        @BindView(R.id.tv_cpmc_blmxFragment)
        TextView tvCpmc;
        @BindView(R.id.tv_tmh_blmxFragment)
        TextView tvTmh;
        @BindView(R.id.tv_qxms_blmxFragment)
        TextView tvQxms;
        @BindView(R.id.tv_gzlb_blmxFragment)
        TextView tvGzlb;
        @BindView(R.id.tv_jcdw_blmxFragment)
        TextView tvJcdw;
        public BLMXFragmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
