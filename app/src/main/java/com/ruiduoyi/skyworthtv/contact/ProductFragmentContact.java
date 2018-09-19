package com.ruiduoyi.skyworthtv.contact;

import com.ruiduoyi.skyworthtv.model.bean.BLMXFragmentBean;
import com.ruiduoyi.skyworthtv.model.bean.ProductFragmentBean;

/**
 * Created by Chen on 2018-09-13.
 */

public interface ProductFragmentContact {

    interface Presentor{
        void loadData(String xb, String funcId);
    }
    interface View{
        void onLoadDataSucceed(ProductFragmentBean bean);
    }

}
