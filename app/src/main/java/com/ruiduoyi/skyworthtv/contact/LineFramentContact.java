package com.ruiduoyi.skyworthtv.contact;

import com.ruiduoyi.skyworthtv.model.bean.LineFragmentBean;

/**
 * Created by Chen on 2018-09-11.
 */

public interface LineFramentContact {

    interface Presentor {
        /*//加载生产担当
        @Deprecated
        void loadManager(String xb);
        //加载计划信息
        @Deprecated
        void loadPlanList(String xb);
        //当前计划
        @Deprecated
        void loadCurrPlan(String xb);
        //产量，完成率，不良率
        @Deprecated
        void loadLineFinishRate(String xb);
        //时段产能对比
        @Deprecated
        void loadLineSDQty(String xb);
        //7日达成率
        @Deprecated
        void loadLast7DayComplete(String xb);
        //不良数量统计
        @Deprecated
        void loadLineErrorCount(String xb);
*/
        void loadData(String xb, String funcId);
    }
    interface View{
        /*void onLoadManagerSucceed(BaseBean<ManagerBean> data);
        void onLoadPlanSucceed(BaseBean<PlanListBean> data);
        void onLoadCurrPlan(BaseBean<CurrPlanBean> data);
        void onLoadLineFinishRate(BaseBean<LineFinishRateBean> data);
        void onLoadLineSDQty(BaseBean<LineSDQtyBean> data);
        void onLoadLast7DayComplete(BaseBean<Last7DayCompleteBean> data);
        void onLoadLineErrorCount(BaseBean<LineErrorCountBean> data);*/

        void onLoadDataSucceed(LineFragmentBean value);
    }

}
