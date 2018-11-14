package com.example.itcode.gplx.DTO;

public class CauHoi {
    private int idCauHoi;
    private int idLoaiCauHoi;
    private String cauHoiText;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;
    private String dapAnDung;
    private String cauHoiHinhAnh;
    private String cauTraLoi;

    public CauHoi(int idCauHoi, int idLoaiCauHoi, String cauHoiText, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung, String cauHoiHinhAnh) {
        this.idCauHoi = idCauHoi;
        this.idLoaiCauHoi = idLoaiCauHoi;
        this.cauHoiText = cauHoiText;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.dapAnDung = dapAnDung;
        this.cauHoiHinhAnh = cauHoiHinhAnh;
    }

    public CauHoi() {
    }

    public int getIdCauHoi() {
        return idCauHoi;
    }

    public void setIdCauHoi(int idCauHoi) {
        this.idCauHoi = idCauHoi;
    }

    public int getIdLoaiCauHoi() {
        return idLoaiCauHoi;
    }

    public void setIdLoaiCauHoi(int idLoaiCauHoi) {
        this.idLoaiCauHoi = idLoaiCauHoi;
    }

    public String getCauHoiText() {
        return cauHoiText;
    }

    public void setCauHoiText(String cauHoiText) {
        this.cauHoiText = cauHoiText;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }

    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    public String getCauHoiHinhAnh() {
        return cauHoiHinhAnh;
    }

    public void setCauHoiHinhAnh(String cauHoiHinhAnh) {
        this.cauHoiHinhAnh = cauHoiHinhAnh;
    }
}
