package com.myjava.quanlycuahang.DTO;

public class TaiKhoanDTO {
    private int matk;
    private String username;
    private String matkhau;
    private String tenVaiTro;
    private int trangthai;

    public TaiKhoanDTO() {
        
    }
    
    public TaiKhoanDTO(int matk,  String username, String matkhau, String tenVaiTro, int trangthai) {
        this.matk = matk;
        this.username = username;
        this.matkhau = matkhau;
        this.tenVaiTro = tenVaiTro;
        this.trangthai = trangthai;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }
    
}
