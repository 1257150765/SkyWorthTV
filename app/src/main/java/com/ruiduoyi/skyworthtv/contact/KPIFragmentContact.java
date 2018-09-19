package com.ruiduoyi.skyworthtv.contact;

import com.ruiduoyi.skyworthtv.model.bean.KPIFragmentBean;

/**
 * Created by Chen on 2018-09-18.
 */

public interface KPIFragmentContact {
    interface Presentor{
        void loadData(String xb, String funcId);
    }
    interface View{
        void onLoadDataSucceed(KPIFragmentBean bean);
    }
}
