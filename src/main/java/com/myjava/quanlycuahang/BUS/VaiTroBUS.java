package com.myjava.quanlycuahang.BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import com.myjava.quanlycuahang.DAO.VaiTroDAO;
import com.myjava.quanlycuahang.DTO.VaiTroDTO;
import com.myjava.quanlycuahang.DTO.VaiTro_ChucNangDTO;
import com.myjava.quanlycuahang.GUI.MainFrame;

public class VaiTroBUS implements BUSInterface<VaiTroDTO>{
    final public static String MAVAITRO = "maVaiTro";
    final public static String TENVAITRO = "tenVaiTro";

    private ArrayList<VaiTroDTO> listVaiTro;

    private static VaiTroBUS instance;
    public static VaiTroBUS getInstance() {
        if (instance == null) {
            instance = new VaiTroBUS();
        }
        return instance;    
    }

    public VaiTroBUS() {
        listVaiTro = VaiTroDAO.getInstance().selectAll();
    }

    public ArrayList<VaiTroDTO> getList() {
        return listVaiTro;
    }

    public int them(VaiTroDTO VaiTro) throws Exception{
        //kiem tra quyen
        //co quyen them tai khoan se co quyen them vai tro
        VaiTro_ChucNangDTO vtcn = VaiTro_ChucNangBUS.getInstance().tim(MainFrame.vaitro.getMaVaiTro(), ChucNangBUS.QLTK);
        if (vtcn == null || vtcn.getThem() == false) {
            throw new SQLException("Bạn không có quyền thêm vai trò");
        }
        //kiem tra ten duy nhat
        for (VaiTroDTO q : listVaiTro) {
            if (q.getTenVaiTro().equals(VaiTro.getTenVaiTro())) {
                throw new Exception("Tên vai trò đã tồn tại");
            }
        }
        int rs = VaiTroDAO.getInstance().insert(VaiTro);
        listVaiTro.add(VaiTro);
        return rs;
    }

    public int xoa(Object id) throws Exception{
        //kiem tra quyen
        //co quyen xoa tai khoan se co quyen xoa vai tro, neu co it nhat mot tai khoan thuoc vai tro thi khong xoa duoc
        VaiTro_ChucNangDTO vtcn = VaiTro_ChucNangBUS.getInstance().tim(MainFrame.vaitro.getMaVaiTro(), ChucNangBUS.QLTK);
        VaiTroDTO vt = tim(MAVAITRO, id.toString());
        if (vtcn == null || vtcn.getXoa() == false || TaiKhoanBUS.getInstance().tim(TaiKhoanBUS.TENVAITRO, vt.getTenVaiTro()) != null) {
            throw new Exception("Bạn không có quyền xóa vai trò hoặc vai trò đã được sử dụng");
        }
        
        listVaiTro.remove(vt);
        return VaiTroDAO.getInstance().delete(id.toString());
    }

    public int sua(VaiTroDTO VaiTro) throws Exception{
        //kiem tra quyen
        //co quyen sua tai khoan se co quyen sua vai tro
        VaiTro_ChucNangDTO vtcn = VaiTro_ChucNangBUS.getInstance().tim(MainFrame.vaitro.getMaVaiTro(), ChucNangBUS.QLTK);
        if (vtcn == null || vtcn.getSua() == false) {
            throw new Exception("Bạn không có quyền sửa vai trò");
        }
        for (VaiTroDTO q : listVaiTro) {
            if (q.getMaVaiTro() == VaiTro.getMaVaiTro()) {
                q.setTenVaiTro(VaiTro.getTenVaiTro());
                return VaiTroDAO.getInstance().update(VaiTro);
            } 
        }
        return 0;
    }

    public ArrayList<VaiTroDTO> loc(String fieldName, String value) {
        ArrayList<VaiTroDTO> result = new ArrayList<>();
        for (VaiTroDTO q : listVaiTro) {
            if (fieldName.equals(MAVAITRO) && String.valueOf(q.getMaVaiTro()).contains(value)) {
                result.add(q);
            } else if (fieldName.equals(TENVAITRO) && q.getTenVaiTro().contains(value)) {
                result.add(q);
            }
        }
        return result;
    }

    public VaiTroDTO tim(String fieldName, String value) {
        for (VaiTroDTO q : listVaiTro) {
            if (fieldName.equals(MAVAITRO) && q.getMaVaiTro() == Integer.parseInt(value)){
                return q;
            } else if (fieldName.equals(TENVAITRO) && q.getTenVaiTro().equals(value)){
                return q;
            }
        }
        return null;
    }
    
    public int getAutoIncrement() {
        return VaiTroDAO.getInstance().getAutoIncrement();
    }
}
