package com.myjava.quanlycuahang.DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class VaiTroDTO {

    private int maVaiTro;
    private String tenVaiTro;
    
    public VaiTroDTO() {
    }

    public VaiTroDTO(int maVaiTro, String tenVaiTro) {
        this.maVaiTro = maVaiTro;
        this.tenVaiTro = tenVaiTro;
    }

    public int getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(int maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }
}
