package com.example.petprojects.ModelThuCung;

public class Store {
    private String idCuaHang, tenCuaHang, dichVuCuaHang, diaChiCuaHang;
    private int anhCuaHang;

    public Store() {
    }

    public Store(String idCuaHang, String tenCuaHang, String dichVuCuaHang, String diaChiCuaHang, int anhCuaHang) {
        this.idCuaHang = idCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.dichVuCuaHang = dichVuCuaHang;
        this.diaChiCuaHang = diaChiCuaHang;
        this.anhCuaHang = anhCuaHang;
    }

    public String getIdCuaHang() {
        return idCuaHang;
    }

    public void setIdCuaHang(String idCuaHang) {
        this.idCuaHang = idCuaHang;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getDichVuCuaHang() {
        return dichVuCuaHang;
    }

    public void setDichVuCuaHang(String dichVuCuaHang) {
        this.dichVuCuaHang = dichVuCuaHang;
    }

    public String getDiaChiCuaHang() {
        return diaChiCuaHang;
    }

    public void setDiaChiCuaHang(String diaChiCuaHang) {
        this.diaChiCuaHang = diaChiCuaHang;
    }

    public int getAnhCuaHang() {
        return anhCuaHang;
    }

    public void setAnhCuaHang(int anhCuaHang) {
        this.anhCuaHang = anhCuaHang;
    }
}