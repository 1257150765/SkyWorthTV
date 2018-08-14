package com.ruiduoyi.skyworthtv.contact;

import com.ruiduoyi.skyworthtv.model.bean.BaseBean;
import com.ruiduoyi.skyworthtv.model.bean.StaffInfo;

/**
 * Created by Chen on 2018-08-07.
 */

public interface StaffChartFragmentContact {
    public interface View{
        void onGetWorkListSucceed(BaseBean<StaffInfo> data);
    }
    public interface Presentor{
        void getWorkList(String key_page);

    }
}
