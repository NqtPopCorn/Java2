package com.myjava.quanlycuahang.BUS;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.myjava.quanlycuahang.DAO.VaiTro_ChucNangDAO;
import com.myjava.quanlycuahang.DTO.VaiTro_ChucNangDTO;

public class VaiTro_ChucNangBUS {
    final static String MA_VAI_TRO = "maVaiTro";
    final static String MA_CHUC_NANG = "maChucNang";
    final static String THEM = "them";
    final static String SUA = "sua";
    final static String XOA = "xoa";
    private ArrayList<VaiTro_ChucNangDTO> listVaiTro_ChucNang;

    private static VaiTro_ChucNangBUS instance;
    public static VaiTro_ChucNangBUS getInstance() {
        if (instance == null) {
            instance = new VaiTro_ChucNangBUS();
        }
        return instance;
    }

    private VaiTro_ChucNangBUS() {
        try {
            listVaiTro_ChucNang = VaiTro_ChucNangDAO.getInstance().selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<VaiTro_ChucNangDTO> getList() {
        return listVaiTro_ChucNang;
    }

    public int them(VaiTro_ChucNangDTO obj) throws Exception {
        //kiem tra khoa chinh (maVaiTro, maChucNang) da ton tai chua
        for (VaiTro_ChucNangDTO vt : listVaiTro_ChucNang) {
            if (vt.getMaVaiTro() == obj.getMaVaiTro() && vt.getMaChucNang().equals(obj.getMaChucNang())) {
                throw new Exception("Mã vai trò và mã chức năng đã tồn tại");
            }
        }
        listVaiTro_ChucNang.add(obj);
        return VaiTro_ChucNangDAO.getInstance().insert(obj);
    }

    public int sua(VaiTro_ChucNangDTO obj) throws Exception {
        int rs = VaiTro_ChucNangDAO.getInstance().update(obj);
        if (rs == 0) {
            return 0;
        }
        for (VaiTro_ChucNangDTO vtcn : listVaiTro_ChucNang) {
            if (vtcn.getMaVaiTro() == obj.getMaVaiTro() && vtcn.getMaChucNang().equals(obj.getMaChucNang())) {
                vtcn.setThem(obj.getThem());
                vtcn.setSua(obj.getSua());
                vtcn.setXoa(obj.getXoa());
                return VaiTro_ChucNangDAO.getInstance().update(obj);
            }
        }
        return rs;
    }

    public int xoa(int maVaiTro, String maChucNang) throws Exception {
        int rs = VaiTro_ChucNangDAO.getInstance().delete(maVaiTro, maChucNang);
        if (rs == 0) {
            return 0;
        }
        for (VaiTro_ChucNangDTO vtcn : listVaiTro_ChucNang) {
            if (vtcn.getMaVaiTro() == maVaiTro && vtcn.getMaChucNang().equals(maChucNang)) {
                listVaiTro_ChucNang.remove(vtcn);
                return rs;
            }
        }
        return 0;
    }

    public ArrayList<VaiTro_ChucNangDTO> loc(String fieldName, String value) {
        ArrayList<VaiTro_ChucNangDTO> result = new ArrayList<>();
        for (VaiTro_ChucNangDTO vtcn : listVaiTro_ChucNang) {
            switch(fieldName) {
                case MA_VAI_TRO:
                    if ((vtcn.getMaVaiTro()+"").equals(value)) {
                        result.add(vtcn);
                    }
                    break;
                case MA_CHUC_NANG:
                    if (vtcn.getMaChucNang().equals(value)) {
                        result.add(vtcn);
                    }
                    break;
                case THEM:
                    if (vtcn.getThem().toString().equals(value)) {
                        result.add(vtcn);
                    }
                    break;
                case SUA:
                    if (vtcn.getSua().toString().equals(value)) {
                        result.add(vtcn);
                    }
                    break;
                case XOA:
                    if (vtcn.getXoa().toString().equals(value)) {
                        result.add(vtcn);
                    }
                    break;
            }
        }
        return null;
    }

    public VaiTro_ChucNangDTO tim(int maVaiTro, String maChucNang) {
        for (VaiTro_ChucNangDTO vtcn : listVaiTro_ChucNang) {
            if (vtcn.getMaVaiTro() == maVaiTro && vtcn.getMaChucNang().equals(maChucNang)) {
                return vtcn;
            }
        }
        return null;
    }

    public ArrayList<VaiTro_ChucNangDTO> getChucNang(int maVaiTro) {
        ArrayList<VaiTro_ChucNangDTO> list = new ArrayList<VaiTro_ChucNangDTO>();
        for (VaiTro_ChucNangDTO vtcn : listVaiTro_ChucNang) {
            if (vtcn.getMaVaiTro() == maVaiTro) {
                list.add(vtcn);
            }
        }
        return list;
    }
    
    public int xoaChucNang(int maVaiTro) {
        listVaiTro_ChucNang.removeIf(vtcn -> vtcn.getMaVaiTro() == maVaiTro);
        return VaiTro_ChucNangDAO.getInstance().xoaChucNang(maVaiTro);
    }
}
