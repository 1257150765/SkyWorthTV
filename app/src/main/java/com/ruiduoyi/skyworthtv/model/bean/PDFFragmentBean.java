package com.ruiduoyi.skyworthtv.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018-09-13.
 */

public class PDFFragmentBean {


    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : {"Table":[{"brd_url":"http://172.24.176.7:9999/BoardFile/","brd_xh":1,"brd_name":"通字【2018】6月第2号 总第04号 关于2018\u201c端午节\u201d的放假通知","brd_file":"通字【2018】6月第2号 总第04号 关于2018\u201c端午节\u201d的放假通知.pdf","brd_time":"30"},{"brd_url":"http://172.24.176.7:9999/BoardFile/","brd_xh":2,"brd_name":"电子元器件识别培训","brd_file":"电子元器件识别培训.pdf","brd_time":"30"}]}
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
             * brd_url : http://172.24.176.7:9999/BoardFile/
             * brd_xh : 1
             * brd_name : 通字【2018】6月第2号 总第04号 关于2018“端午节”的放假通知
             * brd_file : 通字【2018】6月第2号 总第04号 关于2018“端午节”的放假通知.pdf
             * brd_time : 30
             */

            private String brd_url;
            private int brd_xh;
            private String brd_name;
            private String brd_file;
            private String brd_time;

            public String getBrd_url() {
                return brd_url;
            }

            public void setBrd_url(String brd_url) {
                this.brd_url = brd_url;
            }

            public int getBrd_xh() {
                return brd_xh;
            }

            public void setBrd_xh(int brd_xh) {
                this.brd_xh = brd_xh;
            }

            public String getBrd_name() {
                return brd_name;
            }

            public void setBrd_name(String brd_name) {
                this.brd_name = brd_name;
            }

            public String getBrd_file() {
                return brd_file;
            }

            public void setBrd_file(String brd_file) {
                this.brd_file = brd_file;
            }

            public String getBrd_time() {
                return brd_time;
            }

            public void setBrd_time(String brd_time) {
                this.brd_time = brd_time;
            }
        }
    }
}
