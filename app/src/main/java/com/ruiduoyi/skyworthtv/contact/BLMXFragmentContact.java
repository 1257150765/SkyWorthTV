package com.ruiduoyi.skyworthtv.contact;

import com.ruiduoyi.skyworthtv.model.bean.BLMXFragmentBean;

/**
 * Created by Chen on 2018-09-13.
 */

public interface BLMXFragmentContact {

    interface Presentor{
        void loadData(String xb, String funcId);
    }
    interface View{
        void onLoadDataSucceed(BLMXFragmentBean bean);
    }

}
