package com.ruiduoyi.skyworthtv.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018-09-13.
 */

public class BLMXFragmentBean {


    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : {"Table":[{"err_rq":"2018-09-13","err_xbdm":"K1 B","err_plm_djbh":"700000528872","plm_wldm":"KDA001N1430","itm_pmgg":"HWJZSS35FG01","err_bltm":"QC1808000006","err_gzyyms":"","err_gzdlms":"","opr_gzmc":"总装运转测试1"},{"err_rq":"2018-09-13","err_xbdm":"K1 B","err_plm_djbh":"700000528872","plm_wldm":"KDA001N1430","itm_pmgg":"HWJZSS35FG01","err_bltm":"QC1808000004","err_gzyyms":"","err_gzdlms":"","opr_gzmc":"总装电子检漏"},{"err_rq":"2018-09-13","err_xbdm":"K1 B","err_plm_djbh":"700000475809","plm_wldm":"KDA001N0461","itm_pmgg":"KFR-26G/F2BA1A-3(A4)","err_bltm":"","err_gzyyms":"","err_gzdlms":"","opr_gzmc":"总装运转测试2"},{"err_rq":"2018-09-13","err_xbdm":"K1 B","err_plm_djbh":"700000475809","plm_wldm":"KDA001N0461","itm_pmgg":"KFR-26G/F2BA1A-3(A4)","err_bltm":"","err_gzyyms":"","err_gzdlms":"","opr_gzmc":"总装运转测试2"},{"err_rq":"2018-09-13","err_xbdm":"K1 B","err_plm_djbh":"700000475809","plm_wldm":"KDA001N0461","itm_pmgg":"KFR-26G/F2BA1A-3(A4)","err_bltm":"","err_gzyyms":"","err_gzdlms":"","opr_gzmc":"总装运转测试2"},{"err_rq":"2018-09-13","err_xbdm":"K1 B","err_plm_djbh":"700000475809","plm_wldm":"KDA001N0461","itm_pmgg":"KFR-26G/F2BA1A-3(A4)","err_bltm":"","err_gzyyms":"","err_gzdlms":"","opr_gzmc":"总装运转测试2"},{"err_rq":"2018-09-13","err_xbdm":"K1 B","err_plm_djbh":"700000475809","plm_wldm":"KDA001N0461","itm_pmgg":"KFR-26G/F2BA1A-3(A4)","err_bltm":"","err_gzyyms":"","err_gzdlms":"","opr_gzmc":"总装运转测试2"}]}
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
             * err_rq : 2018-09-13
             * err_xbdm : K1 B
             * err_plm_djbh : 700000528872
             * plm_wldm : KDA001N1430
             * itm_pmgg : HWJZSS35FG01
             * err_bltm : QC1808000006
             * err_gzyyms :
             * err_gzdlms :
             * opr_gzmc : 总装运转测试1
             */

            private String err_rq;
            private String err_xbdm;
            private String err_plm_djbh;
            private String plm_wldm;
            private String itm_pmgg;
            private String err_bltm;
            private String err_gzyyms;
            private String err_gzdlms;
            private String opr_gzmc;

            public String getErr_rq() {
                return err_rq;
            }

            public void setErr_rq(String err_rq) {
                this.err_rq = err_rq;
            }

            public String getErr_xbdm() {
                return err_xbdm;
            }

            public void setErr_xbdm(String err_xbdm) {
                this.err_xbdm = err_xbdm;
            }

            public String getErr_plm_djbh() {
                return err_plm_djbh;
            }

            public void setErr_plm_djbh(String err_plm_djbh) {
                this.err_plm_djbh = err_plm_djbh;
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

            public String getErr_bltm() {
                return err_bltm;
            }

            public void setErr_bltm(String err_bltm) {
                this.err_bltm = err_bltm;
            }

            public String getErr_gzyyms() {
                return err_gzyyms;
            }

            public void setErr_gzyyms(String err_gzyyms) {
                this.err_gzyyms = err_gzyyms;
            }

            public String getErr_gzdlms() {
                return err_gzdlms;
            }

            public void setErr_gzdlms(String err_gzdlms) {
                this.err_gzdlms = err_gzdlms;
            }

            public String getOpr_gzmc() {
                return opr_gzmc;
            }

            public void setOpr_gzmc(String opr_gzmc) {
                this.opr_gzmc = opr_gzmc;
            }
        }
    }
}
