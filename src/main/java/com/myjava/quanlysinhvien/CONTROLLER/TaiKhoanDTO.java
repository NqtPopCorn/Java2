package com.myjava.quanlysinhvien.CONTROLLER;

public class TaiKhoanDTO {
    private String username;
    private String matkhau;
    private int manhomquyen;
    private int trangthai;

    public TaiKhoanDTO() {
        
    }
    
    public TaiKhoanDTO( String username, String matkhau, int manhomquyen, int trangthai) {
        this.username = username;
        this.matkhau = matkhau;
        this.manhomquyen = manhomquyen;
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

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    
}
