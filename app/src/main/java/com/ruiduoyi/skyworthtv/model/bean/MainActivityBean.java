package com.ruiduoyi.skyworthtv.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chen on 2018-08-21.
 */

public class MainActivityBean implements Serializable{

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : {"Table":[{"brd_devid":"K1TV","brd_devms":"总装车间看板","brd_mkdm":"KB03","lbm_lbmc":"总装线别实时看板","brd_xh":1,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"K2TV","brd_devms":"总装车间看板","brd_mkdm":"KB03","lbm_lbmc":"总装线别实时看板","brd_xh":1,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"K3TV","brd_devms":"总装车间看板","brd_mkdm":"KB03","lbm_lbmc":"总装线别实时看板","brd_xh":1,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"KZQ01","brd_devms":"控制器车间总看板","brd_mkdm":"KB01","lbm_lbmc":"员工技能矩阵评价表","brd_xh":1,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"KZQ01","brd_devms":"控制器车间总看板","brd_mkdm":"KB02","lbm_lbmc":"PDF展示","brd_xh":2,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"MI01","brd_devms":"MI01线看板","brd_mkdm":"KB06","lbm_lbmc":"控制器车间生产看板","brd_xh":1,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"MI01","brd_devms":"MI01线看板","brd_mkdm":"KB04","lbm_lbmc":"前7天KPI看板","brd_xh":2,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"MI01","brd_devms":"MI01线看板","brd_mkdm":"KB05","lbm_lbmc":"车间不良明细","brd_xh":3,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"MI02","brd_devms":"MI02线看板","brd_mkdm":"KB06","lbm_lbmc":"控制器车间生产看板","brd_xh":1,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"MI02","brd_devms":"MI02线看板","brd_mkdm":"KB04","lbm_lbmc":"前7天KPI看板","brd_xh":2,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"},{"brd_devid":"MI02","brd_devms":"MI02线看板","brd_mkdm":"KB05","lbm_lbmc":"车间不良明细","brd_xh":3,"brd_kb_chg_time":"","brd_kb_refresh_time":"30"}]}
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

    public static class UcDataBean implements Serializable{
        private List<TableBean> Table;

        public List<TableBean> getTable() {
            return Table;
        }

        public void setTable(List<TableBean> Table) {
            this.Table = Table;
        }

        public static class TableBean implements Serializable{
            /**
             * brd_devid : K1TV
             * brd_devms : 总装车间看板
             * brd_mkdm : KB03
             * lbm_lbmc : 总装线别实时看板
             * brd_xh : 1
             * brd_kb_chg_time :
             * brd_kb_refresh_time : 30
             */

            private String brd_devid;
            private String brd_devms;
            private String brd_mkdm;
            private String lbm_lbmc;
            private int brd_xh;
            private String brd_kb_chg_time;
            private String brd_kb_refresh_time;

            public String getBrd_devid() {
                return brd_devid;
            }

            public void setBrd_devid(String brd_devid) {
                this.brd_devid = brd_devid;
            }

            public String getBrd_devms() {
                return brd_devms;
            }

            public void setBrd_devms(String brd_devms) {
                this.brd_devms = brd_devms;
            }

            public String getBrd_mkdm() {
                return brd_mkdm;
            }

            public void setBrd_mkdm(String brd_mkdm) {
                this.brd_mkdm = brd_mkdm;
            }

            public String getLbm_lbmc() {
                return lbm_lbmc;
            }

            public void setLbm_lbmc(String lbm_lbmc) {
                this.lbm_lbmc = lbm_lbmc;
            }

            public int getBrd_xh() {
                return brd_xh;
            }

            public void setBrd_xh(int brd_xh) {
                this.brd_xh = brd_xh;
            }

            public String getBrd_kb_chg_time() {
                return brd_kb_chg_time;
            }

            public void setBrd_kb_chg_time(String brd_kb_chg_time) {
                this.brd_kb_chg_time = brd_kb_chg_time;
            }

            public String getBrd_kb_refresh_time() {
                return brd_kb_refresh_time;
            }

            public void setBrd_kb_refresh_time(String brd_kb_refresh_time) {
                this.brd_kb_refresh_time = brd_kb_refresh_time;
            }
        }
    }
}
