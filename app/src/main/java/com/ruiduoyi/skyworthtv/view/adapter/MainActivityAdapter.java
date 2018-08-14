package com.ruiduoyi.skyworthtv.view.adapter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.util.DensityUtil;
import com.ruiduoyi.skyworthtv.view.activity.ControlWorkShopStateBoardActivity;
import com.ruiduoyi.skyworthtv.view.activity.TestActivity;
import com.ruiduoyi.skyworthtv.view.activity.WorkShopStateBoardActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chen on 2018-08-07.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityHolder> {

    private static final String TAG = MainActivityHolder.class.getSimpleName();
    private List<String> mData;
    private Context mContext;

    public MainActivityAdapter( Context mContext,List<String> mData) {
        this.mData = mData;
        this.mContext = mContext;
    }



    @Override
    public MainActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MainActivityHolder holder = new MainActivityHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MainActivityHolder holder, final int position) {
        holder.tvText.setText(mData.get(position));
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        mContext.startActivity(new Intent(mContext,WorkShopStateBoardActivity.class));
                        break;
                    case 1:
                        mContext.startActivity(new Intent(mContext, TestActivity.class));
                        break;
                    case 2:
                        Intent intent = new Intent(mContext, ControlWorkShopStateBoardActivity.class);
                        intent.putExtra(ControlWorkShopStateBoardActivity.START_TYPE, ControlWorkShopStateBoardActivity.START_TYPE_PRODUCTFRAGMENT);
                        mContext.startActivity(intent);
                        break;
                    case 3:
                        Intent intent2 = new Intent(mContext, ControlWorkShopStateBoardActivity.class);
                        intent2.putExtra(ControlWorkShopStateBoardActivity.START_TYPE, ControlWorkShopStateBoardActivity.START_TYPE_BLMXFRAGMENT);
                        mContext.startActivity(intent2);
                        break;
                    default:
                        Toast.makeText(v.getContext(),""+mData.get(position),Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        holder.content.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d(TAG, "onFocusChange: "+position+"--"+hasFocus);
                if (hasFocus){
                    bordBackGround(v);
                    animaBig(v);
                }else {
                    defaultBackGround(v);
                    animaSmall(v);
                }
            }
        });
    }

    private void defaultBackGround(View v) {
        v.setBackground(v.getContext().getDrawable(R.drawable.default_background_item_main_activity));
    }

    private void bordBackGround(View v) {
        v.setBackground(v.getContext().getDrawable(R.drawable.bord_background_item_main_activity));
    }

    private void animaSmall(View v) {
       /* ObjectAnimator animator = ObjectAnimator.ofFloat(v,"scaleX",level1.2f,level1.0f);
        animator.setDuration(500);
        animator.start();*/
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(v.getContext(), R.animator.item_small);
        // 创建组合动画对象  &  加载XML动画
        animator.setTarget(v);
        // 设置动画作用对象
        animator.start();
        if (v instanceof CardView){
            ((CardView) v).setCardElevation(DensityUtil.dip2px(v.getContext(),2));
        }
    }

    private void animaBig(View v) {
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(v,"scale",level1.0f,level1.2f);
        animator.setDuration(500);
        animator.start();*/
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(v.getContext(), R.animator.item_big);
        // 创建组合动画对象  &  加载XML动画
        animator.setTarget(v);
        // 设置动画作用对象
        animator.start();
        if (v instanceof CardView){
            ((CardView) v).setCardElevation(DensityUtil.dip2px(v.getContext(),10));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MainActivityHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_text_mainActivity_item)
        TextView tvText;
        View content;
        public MainActivityHolder(View itemView) {
            super(itemView);
            content = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
