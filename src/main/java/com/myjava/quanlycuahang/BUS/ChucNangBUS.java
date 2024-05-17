package com.myjava.quanlycuahang.BUS;

import java.util.ArrayList;

import com.myjava.quanlycuahang.DAO.ChucNangDAO;
import com.myjava.quanlycuahang.DTO.ChucNangDTO;

public class ChucNangBUS implements BUSInterface<ChucNangDTO>{
    final public static String QLTK = "QLTK";
    final public static String QLKH = "QLKH";
    final public static String QLSP = "QLSP";
    final public static String QLPX = "QLPX";
    final public static String QLNV = "QLNV";
    final public static String QLPN = "QLPN";
    final public static String MACHUCNANG = "maChucNang";
    final public static String TENCHUCNANG = "tenChucNang";

    private ArrayList<ChucNangDTO> listChucNang;

    private static ChucNangBUS instance;
    private ChucNangBUS() {
        listChucNang = new ArrayList<>();
        try {
            listChucNang = ChucNangDAO.getInstance().selectAll();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static ChucNangBUS getInstance() {
        if (instance == null) {
            instance = new ChucNangBUS();
        }
        return instance;
    }

    @Override
    public ArrayList<ChucNangDTO> getList() {
        return listChucNang;
    }

    @Override
    public int them(ChucNangDTO chucNang) {
        int rs = 0;
        //kiem tra ma duy nhat
        for (ChucNangDTO cn : listChucNang) {
            if (cn.getMaChucNang().equals(chucNang.getMaChucNang())) {
                System.out.println("Mã chức năng đã tồn tại");
                return -1;
            }
        }
        try {
            rs = ChucNangDAO.getInstance().insert(chucNang);
            listChucNang.add(chucNang);
            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int sua(ChucNangDTO obj) {
        int rs = 0;
        try {
            rs = ChucNangDAO.getInstance().update(obj);
            for (ChucNangDTO cn : listChucNang) {
                if (cn.getMaChucNang().equals(obj.getMaChucNang())) {
                    cn.setTenChucNang(obj.getTenChucNang());
                    break;
                }
            }
            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int xoa(Object id) {
        int rs = 0;
        try {
            rs = ChucNangDAO.getInstance().delete(id.toString());
            if (rs != 0) {
                for (ChucNangDTO cn : listChucNang) {
                    if (cn.getMaChucNang().equals(id)) {
                        listChucNang.remove(cn);
                        break;
                    }
                }
            }
            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public ArrayList<ChucNangDTO> loc(String fieldName, String value) {
        ArrayList<ChucNangDTO> result = new ArrayList<>();
        for (ChucNangDTO cn : listChucNang) {
            switch(fieldName) {
                case MACHUCNANG:
                    if (cn.getMaChucNang().contains(value)) {
                        result.add(cn);
                    }
                    break;
                case TENCHUCNANG:
                    if (cn.getTenChucNang().contains(value)) {
                        result.add(cn);
                    }
                    break;
            }
        }
        return result;
    }

    public ChucNangDTO tim(String fieldName, String value) {
        for (ChucNangDTO cn : listChucNang) {
           switch(fieldName) {
               case MACHUCNANG:
                   if (cn.getMaChucNang().equals(value)) {
                       return cn;
                   }
                   break;
               case TENCHUCNANG:
                   if (cn.getTenChucNang().equals(value)) {
                       return cn;
                   }
                   break;
           }
        }
        return null;
    }
}
