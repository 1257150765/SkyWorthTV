package com.ruiduoyi.skyworthtv.contact;

/**
 * Created by Chen on 2018-08-10.
 */

public interface MainActivityContact {

    public interface View extends BaseContact.View{
        void onCheckUpdateSucceed(boolean hasUpdate,String url);

        void onUpdate(Integer value);

        void onUpdateComplete();
    }

    public interface Presentor {
        void checkUpdate();
        void update(String url);
    }
}
