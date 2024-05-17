package com.myjava.quanlycuahang.DTO;

public class VaiTro_ChucNangDTO {
    private int maVaiTro;
    private String maChucNang;
    private Boolean them;
    private Boolean sua;
    private Boolean xoa;

    public VaiTro_ChucNangDTO() {

    }

    public VaiTro_ChucNangDTO(int maVaiTro, String maChucNang, Boolean them, Boolean sua, Boolean xoa) {
        this.maVaiTro = maVaiTro;
        this.maChucNang = maChucNang;
        this.them = them;
        this.sua = sua;
        this.xoa = xoa;
    }

    //getter, setter for all properties
    public int getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(int maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(String maChucNang) {
        this.maChucNang = maChucNang;
    }

    public Boolean getThem() {
        return them;
    }

    public void setThem(Boolean them) {
        this.them = them;
    }

    public Boolean getSua() {
        return sua;
    }

    public void setSua(Boolean sua) {
        this.sua = sua;
    }

    public Boolean getXoa() {
        return xoa;
    }

    public void setXoa(Boolean xoa) {
        this.xoa = xoa;
    }
}
