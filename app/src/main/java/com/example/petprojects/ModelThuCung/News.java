package com.example.petprojects.ModelThuCung;

public class News {
    private int resouceImages;
    private String tenTinTuc;


    public News() {
    }

    public News(int resouceImages, String tenTinTuc) {
        this.resouceImages = resouceImages;
        this.tenTinTuc = tenTinTuc;
    }

    public int getResouceImages() {
        return resouceImages;
    }

    public void setResouceImages(int resouceImages) {
        this.resouceImages = resouceImages;
    }

    public String getTenTinTuc() {
        return tenTinTuc;
    }

    public void setTenTinTuc(String tenTinTuc) {
        this.tenTinTuc = tenTinTuc;
    }
}
