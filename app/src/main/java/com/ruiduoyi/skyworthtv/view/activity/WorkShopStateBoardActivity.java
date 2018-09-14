package com.ruiduoyi.skyworthtv.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.view.fragment.BLMXFragment;
import com.ruiduoyi.skyworthtv.view.fragment.ProductFragment;
import com.ruiduoyi.skyworthtv.view.fragment.StaffChartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *车间看板（员工评价表）
 */
@Deprecated
public class WorkShopStateBoardActivity extends AppCompatActivity {
    @BindView(R.id.ll_fragmentContainer_stateBoardBoard)
    LinearLayout llFragmentContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_shop_state_board);
        ButterKnife.bind(this);

    }

    private void replceFragment(Fragment target){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ll_fragmentContainer_stateBoardBoard, target)
                .commit();
    }
}
