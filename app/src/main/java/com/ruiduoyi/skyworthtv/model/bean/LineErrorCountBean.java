package com.ruiduoyi.skyworthtv.model.bean;

/**
 * Created by Chen on 2018-09-11.
 */

public class LineErrorCountBean {
    
    /**
     * xbd_xbdm : K1
     * xbd_gzdm : ZZGXJC
     * xbd_xh : 1
     * opr_gzmc : 总装工序检查(控制器)
     * err_gzsl : 0
     */

    private String xbd_xbdm;
    private String xbd_gzdm;
    private int xbd_xh;
    private String opr_gzmc;
    private int err_gzsl;

    public String getXbd_xbdm() {
        return xbd_xbdm;
    }

    public void setXbd_xbdm(String xbd_xbdm) {
        this.xbd_xbdm = xbd_xbdm;
    }

    public String getXbd_gzdm() {
        return xbd_gzdm;
    }

    public void setXbd_gzdm(String xbd_gzdm) {
        this.xbd_gzdm = xbd_gzdm;
    }

    public int getXbd_xh() {
        return xbd_xh;
    }

    public void setXbd_xh(int xbd_xh) {
        this.xbd_xh = xbd_xh;
    }

    public String getOpr_gzmc() {
        return opr_gzmc;
    }

    public void setOpr_gzmc(String opr_gzmc) {
        this.opr_gzmc = opr_gzmc;
    }

    public int getErr_gzsl() {
        return err_gzsl;
    }

    public void setErr_gzsl(int err_gzsl) {
        this.err_gzsl = err_gzsl;
    }
}
