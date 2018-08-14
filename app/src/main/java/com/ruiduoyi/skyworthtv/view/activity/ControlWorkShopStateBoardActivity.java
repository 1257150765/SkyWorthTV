package com.ruiduoyi.skyworthtv.view.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ruiduoyi.skyworthtv.R;
import com.ruiduoyi.skyworthtv.view.fragment.BLMXFragment;
import com.ruiduoyi.skyworthtv.view.fragment.ProductFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 控制器车间
 */
public class ControlWorkShopStateBoardActivity extends AppCompatActivity {
    public static final String START_TYPE_BLMXFRAGMENT = "blmx";
    public static final String START_TYPE_PRODUCTFRAGMENT = "product";
    public static final String START_TYPE = "startType";
    @BindView(R.id.ll_fragmentContainer_controlStateBoardBoard)
    LinearLayout llFragmentContainer;
    private String startType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_work_shop_state_board);
        ButterKnife.bind(this);
        startType = getIntent().getStringExtra(START_TYPE);
        switch (startType){
            case START_TYPE_BLMXFRAGMENT:
                replceFragment(BLMXFragment.newInstance());
                break;
            case START_TYPE_PRODUCTFRAGMENT:
                replceFragment(ProductFragment.newInstance());
                break;
        }

    }

    private void replceFragment(Fragment target){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ll_fragmentContainer_controlStateBoardBoard, target)
                .commit();
    }

}
