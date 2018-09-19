package com.ruiduoyi.skyworthtv.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018-09-19.
 */

public class NotificationBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : {"Table":[{"refresh_time":"30","notice_msg":"热烈欢迎各领导莅临指导!","display_pos":"1","font_size":"30","font_color":"#FF0000","back_color":"#88cccccc"}]}
     */

    private boolean utStatus;
    private String ucMsg;
    private UcDataBean ucData;

    public boolean isUtStatus() {
        return utStatus;
    }

    public void setUtStatus(boolean utStatus) {
        this.utStatus = utStatus;
    }

    public String getUcMsg() {
        return ucMsg;
    }

    public void setUcMsg(String ucMsg) {
        this.ucMsg = ucMsg;
    }

    public UcDataBean getUcData() {
        return ucData;
    }

    public void setUcData(UcDataBean ucData) {
        this.ucData = ucData;
    }

    public static class UcDataBean {
        private List<TableBean> Table;

        public List<TableBean> getTable() {
            return Table;
        }

        public void setTable(List<TableBean> Table) {
            this.Table = Table;
        }

        public static class TableBean {
            /**
             * refresh_time : 30
             * notice_msg : 热烈欢迎各领导莅临指导!
             * display_pos : 1
             * font_size : 30
             * font_color : #FF0000
             * back_color : #88cccccc
             */

            private String refresh_time;
            private String notice_msg;
            private String display_pos;
            private String font_size;
            private String font_color;
            private String back_color;

            public String getRefresh_time() {
                return refresh_time;
            }

            public void setRefresh_time(String refresh_time) {
                this.refresh_time = refresh_time;
            }

            public String getNotice_msg() {
                return notice_msg;
            }

            public void setNotice_msg(String notice_msg) {
                this.notice_msg = notice_msg;
            }

            public String getDisplay_pos() {
                return display_pos;
            }

            public void setDisplay_pos(String display_pos) {
                this.display_pos = display_pos;
            }

            public String getFont_size() {
                return font_size;
            }

            public void setFont_size(String font_size) {
                this.font_size = font_size;
            }

            public String getFont_color() {
                return font_color;
            }

            public void setFont_color(String font_color) {
                this.font_color = font_color;
            }

            public String getBack_color() {
                return back_color;
            }

            public void setBack_color(String back_color) {
                this.back_color = back_color;
            }
        }
    }
}
