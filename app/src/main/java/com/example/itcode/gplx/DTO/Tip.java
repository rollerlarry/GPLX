package com.example.itcode.gplx.DTO;

import java.io.Serializable;

public class Tip implements Serializable {
    private int tipID;
    private int tipTypeID;
    private String titleTip;
    private String contentTip;
    private byte[] imgTip;

    public Tip(int tipID, int tipTypeID, String titleTip, String contentTip, byte[] imgTip) {
        this.tipID = tipID;
        this.tipTypeID = tipTypeID;
        this.titleTip = titleTip;
        this.contentTip = contentTip;
        this.imgTip = imgTip;
    }

    public int getTipID() {
        return tipID;
    }

    public void setTipID(int tipID) {
        this.tipID = tipID;
    }

    public int getTipTypeID() {
        return tipTypeID;
    }

    public void setTipTypeID(int tipTypeID) {
        this.tipTypeID = tipTypeID;
    }

    public String getTitleTip() {
        return titleTip;
    }

    public void setTitleTip(String titleTip) {
        this.titleTip = titleTip;
    }

    public String getContentTip() {
        return contentTip;
    }

    public void setContentTip(String contentTip) {
        this.contentTip = contentTip;
    }

    public byte[] getImgTip() {
        return imgTip;
    }

    public void setImgTip(byte[] imgTip) {
        this.imgTip = imgTip;
    }
}
