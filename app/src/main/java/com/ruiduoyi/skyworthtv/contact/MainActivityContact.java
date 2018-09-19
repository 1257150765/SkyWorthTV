package com.ruiduoyi.skyworthtv.contact;

import com.ruiduoyi.skyworthtv.model.bean.MainActivityBean;
import com.ruiduoyi.skyworthtv.model.bean.NotificationBean;

/**
 * Created by Chen on 2018-08-10.
 */

public interface MainActivityContact {

    public interface View extends BaseContact.View{
        void onCheckUpdateSucceed(boolean hasUpdate,String url);

        void onUpdate(Integer value);

        void onUpdateComplete();

        void onLoadDataSucceed(MainActivityBean bean);

    }

    public interface Presentor {
        void checkUpdate();
        void update(String url);
        void loadData();

    }
}
