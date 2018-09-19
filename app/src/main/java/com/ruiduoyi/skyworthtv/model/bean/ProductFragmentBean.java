package com.ruiduoyi.skyworthtv.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018-08-08.
 */

public class ProductFragmentBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : {"Table":[{"pqd_xbdm":"K1","pqd_bcdm":"B","pqd_djbh":"700000520661","plm_wldm":"KDB001N0642","itm_pmgg":"KFR-35G/V1BB1A-3","plm_jhsl":1000,"pqd_jhscsl":1000,"rkd_ttl_rksl_v":0,"rkd_day_rksl_v":0,"err_ttl_gzsl_v":0,"err_day_gzsl_v":0,"bzrs":20},{"pqd_xbdm":"K1","pqd_bcdm":"B","pqd_djbh":"700000550940","plm_wldm":"KDA001N1440","itm_pmgg":"HWJZSS50FG01","plm_jhsl":400,"pqd_jhscsl":400,"rkd_ttl_rksl_v":600,"rkd_day_rksl_v":500,"err_ttl_gzsl_v":20,"err_day_gzsl_v":10,"bzrs":20},{"pqd_xbdm":"K2","pqd_bcdm":"B","pqd_djbh":"700000520739","plm_wldm":"KDA001W0224","itm_pmgg":"KFR-35W/F2C1A-3","plm_jhsl":1000,"pqd_jhscsl":1000,"rkd_ttl_rksl_v":600,"rkd_day_rksl_v":500,"err_ttl_gzsl_v":20,"err_day_gzsl_v":10,"bzrs":20},{"pqd_xbdm":"K2","pqd_bcdm":"B","pqd_djbh":"700000550935","plm_wldm":"KEA001W2120_A4218","itm_pmgg":"SMFH09A-3A2A1NA(O)","plm_jhsl":700,"pqd_jhscsl":700,"rkd_ttl_rksl_v":600,"rkd_day_rksl_v":480,"err_ttl_gzsl_v":15,"err_day_gzsl_v":15,"bzrs":20}],"Table1":[{"err_gzdm":"AOIG1","err_gzmc":"AOI工站","err_gzsl":20},{"err_gzdm":"DCT","err_gzmc":"DCT工站","err_gzsl":100},{"err_gzdm":"FCT","err_gzmc":"FCT工站","err_gzsl":0},{"err_gzdm":"ICT","err_gzmc":"ICT工站","err_gzsl":100},{"err_gzdm":"MI","err_gzmc":"MI外观检","err_gzsl":50},{"err_gzdm":"MUTAL","err_gzmc":"自互检","err_gzsl":50}],"Table2":[{"pqd_xbdm":"K1","pqd_jhscsl":1400,"rkd_day_rksl_v":500,"pqd_jhdclv":35.71,"pqd_scxlv":83.33,"pqd_yjscxlv":4.17},{"pqd_xbdm":"K2","pqd_jhscsl":1700,"rkd_day_rksl_v":980,"pqd_jhdclv":57.65,"pqd_scxlv":163.33,"pqd_yjscxlv":8.17}]}
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
        private List<Table1Bean> Table1;
        private List<Table2Bean> Table2;

        public List<TableBean> getTable() {
            return Table;
        }

        public void setTable(List<TableBean> Table) {
            this.Table = Table;
        }

        public List<Table1Bean> getTable1() {
            return Table1;
        }

        public void setTable1(List<Table1Bean> Table1) {
            this.Table1 = Table1;
        }

        public List<Table2Bean> getTable2() {
            return Table2;
        }

        public void setTable2(List<Table2Bean> Table2) {
            this.Table2 = Table2;
        }

        public static class TableBean {
            /**
             * pqd_xbdm : K1
             * pqd_bcdm : B
             * pqd_djbh : 700000520661
             * plm_wldm : KDB001N0642
             * itm_pmgg : KFR-35G/V1BB1A-3
             * plm_jhsl : 1000
             * pqd_jhscsl : 1000
             * rkd_ttl_rksl_v : 0.0
             * rkd_day_rksl_v : 0.0
             * err_ttl_gzsl_v : 0
             * err_day_gzsl_v : 0
             * bzrs : 20
             */

            private String pqd_xbdm;
            private String pqd_bcdm;
            private String pqd_djbh;
            private String plm_wldm;
            private String itm_pmgg;
            private int plm_jhsl;
            private int pqd_jhscsl;
            private double rkd_ttl_rksl_v;
            private double rkd_day_rksl_v;
            private int err_ttl_gzsl_v;
            private int err_day_gzsl_v;
            private int bzrs;

            public String getPqd_xbdm() {
                return pqd_xbdm;
            }

            public void setPqd_xbdm(String pqd_xbdm) {
                this.pqd_xbdm = pqd_xbdm;
            }

            public String getPqd_bcdm() {
                return pqd_bcdm;
            }

            public void setPqd_bcdm(String pqd_bcdm) {
                this.pqd_bcdm = pqd_bcdm;
            }

            public String getPqd_djbh() {
                return pqd_djbh;
            }

            public void setPqd_djbh(String pqd_djbh) {
                this.pqd_djbh = pqd_djbh;
            }

            public String getPlm_wldm() {
                return plm_wldm;
            }

            public void setPlm_wldm(String plm_wldm) {
                this.plm_wldm = plm_wldm;
            }

            public String getItm_pmgg() {
                return itm_pmgg;
            }

            public void setItm_pmgg(String itm_pmgg) {
                this.itm_pmgg = itm_pmgg;
            }

            public int getPlm_jhsl() {
                return plm_jhsl;
            }

            public void setPlm_jhsl(int plm_jhsl) {
                this.plm_jhsl = plm_jhsl;
            }

            public int getPqd_jhscsl() {
                return pqd_jhscsl;
            }

            public void setPqd_jhscsl(int pqd_jhscsl) {
                this.pqd_jhscsl = pqd_jhscsl;
            }

            public double getRkd_ttl_rksl_v() {
                return rkd_ttl_rksl_v;
            }

            public void setRkd_ttl_rksl_v(double rkd_ttl_rksl_v) {
                this.rkd_ttl_rksl_v = rkd_ttl_rksl_v;
            }

            public double getRkd_day_rksl_v() {
                return rkd_day_rksl_v;
            }

            public void setRkd_day_rksl_v(double rkd_day_rksl_v) {
                this.rkd_day_rksl_v = rkd_day_rksl_v;
            }

            public int getErr_ttl_gzsl_v() {
                return err_ttl_gzsl_v;
            }

            public void setErr_ttl_gzsl_v(int err_ttl_gzsl_v) {
                this.err_ttl_gzsl_v = err_ttl_gzsl_v;
            }

            public int getErr_day_gzsl_v() {
                return err_day_gzsl_v;
            }

            public void setErr_day_gzsl_v(int err_day_gzsl_v) {
                this.err_day_gzsl_v = err_day_gzsl_v;
            }

            public int getBzrs() {
                return bzrs;
            }

            public void setBzrs(int bzrs) {
                this.bzrs = bzrs;
            }
        }

        public static class Table1Bean {
            /**
             * err_gzdm : AOIG1
             * err_gzmc : AOI工站
             * err_gzsl : 20
             */

            private String err_gzdm;
            private String err_gzmc;
            private int err_gzsl;

            public String getErr_gzdm() {
                return err_gzdm;
            }

            public void setErr_gzdm(String err_gzdm) {
                this.err_gzdm = err_gzdm;
            }

            public String getErr_gzmc() {
                return err_gzmc;
            }

            public void setErr_gzmc(String err_gzmc) {
                this.err_gzmc = err_gzmc;
            }

            public int getErr_gzsl() {
                return err_gzsl;
            }

            public void setErr_gzsl(int err_gzsl) {
                this.err_gzsl = err_gzsl;
            }
        }

        public static class Table2Bean {
            /**
             * pqd_xbdm : K1
             * pqd_jhscsl : 1400
             * rkd_day_rksl_v : 500.0
             * pqd_jhdclv : 35.71
             * pqd_scxlv : 83.33
             * pqd_yjscxlv : 4.17
             */

            private String pqd_xbdm;
            private int pqd_jhscsl;
            private double rkd_day_rksl_v;
            private double pqd_jhdclv;
            private double pqd_scxlv;
            private double pqd_yjscxlv;

            public String getPqd_xbdm() {
                return pqd_xbdm;
            }

            public void setPqd_xbdm(String pqd_xbdm) {
                this.pqd_xbdm = pqd_xbdm;
            }

            public int getPqd_jhscsl() {
                return pqd_jhscsl;
            }

            public void setPqd_jhscsl(int pqd_jhscsl) {
                this.pqd_jhscsl = pqd_jhscsl;
            }

            public double getRkd_day_rksl_v() {
                return rkd_day_rksl_v;
            }

            public void setRkd_day_rksl_v(double rkd_day_rksl_v) {
                this.rkd_day_rksl_v = rkd_day_rksl_v;
            }

            public double getPqd_jhdclv() {
                return pqd_jhdclv;
            }

            public void setPqd_jhdclv(double pqd_jhdclv) {
                this.pqd_jhdclv = pqd_jhdclv;
            }

            public double getPqd_scxlv() {
                return pqd_scxlv;
            }

            public void setPqd_scxlv(double pqd_scxlv) {
                this.pqd_scxlv = pqd_scxlv;
            }

            public double getPqd_yjscxlv() {
                return pqd_yjscxlv;
            }

            public void setPqd_yjscxlv(double pqd_yjscxlv) {
                this.pqd_yjscxlv = pqd_yjscxlv;
            }
        }
    }
}
