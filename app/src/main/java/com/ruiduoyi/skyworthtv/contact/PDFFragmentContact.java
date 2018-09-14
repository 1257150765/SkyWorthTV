package com.ruiduoyi.skyworthtv.contact;

import com.ruiduoyi.skyworthtv.model.bean.PDFFragmentBean;

/**
 * Created by Chen on 2018-09-13.
 */

public interface PDFFragmentContact {
    interface Presentor{
        void loadData(String xb, String funcId);

    }
    interface View{
        void onLoadDataSucceed(PDFFragmentBean bean);
    }
}
