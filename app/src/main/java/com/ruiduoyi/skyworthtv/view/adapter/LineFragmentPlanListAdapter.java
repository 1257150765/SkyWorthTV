package com.ruiduoyi.skyworthtv.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.model.bean.LineFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.PlanListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chen on 2018-09-12.
 */

public class LineFragmentPlanListAdapter extends RecyclerView.Adapter<LineFragmentPlanListAdapter.PlanListHolder> {

    private Context context;
    private List<LineFragmentBean.UcDataBean.Table3Bean> data;

    public LineFragmentPlanListAdapter(Context context, List<LineFragmentBean.UcDataBean.Table3Bean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public PlanListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlanListHolder holder = new PlanListHolder(LayoutInflater.from(context).inflate(R.layout.item_line_fragment_plan_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(PlanListHolder holder, int position) {
        LineFragmentBean.UcDataBean.Table3Bean bean = data.get(position);
        holder.tvDdbh.setText(bean.getPqd_djbh());
        holder.tvWldm.setText(bean.getPlm_wldm());
        holder.tvJhsl.setText(""+bean.getPqd_jhscsl());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class PlanListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_ddbh_item_planList)
        TextView tvDdbh;
        @BindView(R.id.tv_wldm_item_planList)
        TextView tvWldm;
        @BindView(R.id.tv_jhsl_item_planList)
        TextView tvJhsl;
        public PlanListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
