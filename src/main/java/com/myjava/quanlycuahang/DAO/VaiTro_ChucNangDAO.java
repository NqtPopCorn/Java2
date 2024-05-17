package com.myjava.quanlycuahang.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.myjava.quanlycuahang.DTO.VaiTro_ChucNangDTO;
import com.myjava.quanlycuahang.UTIL.JDBCUtil;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class VaiTro_ChucNangDAO {
    private static VaiTro_ChucNangDAO instance;
    private VaiTro_ChucNangDAO() {
    }
    public static VaiTro_ChucNangDAO getInstance(){
        if(instance == null) instance = new VaiTro_ChucNangDAO();
        return instance;
    }

    public int insert(VaiTro_ChucNangDTO obj) throws Exception {
        String sql = "INSERT INTO vaiTro_chucNang(maVaiTro, maChucNang, them, sua, xoa) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pst = JDBCUtil.getConnection().prepareStatement(sql);
        pst.setInt(1, obj.getMaVaiTro());
        pst.setString(2, obj.getMaChucNang());
        pst.setBoolean(3, obj.getThem());
        pst.setBoolean(4, obj.getSua());
        pst.setBoolean(5, obj.getXoa());
        return pst.executeUpdate();
    }

    public int update(VaiTro_ChucNangDTO obj) throws Exception {
        String sql = "UPDATE vaiTro_chucNang SET them = ?, sua = ?, xoa = ? WHERE maVaiTro = ? AND maChucNang = ?";
        PreparedStatement pst = JDBCUtil.getConnection().prepareStatement(sql);
        pst.setBoolean(1, obj.getThem());
        pst.setBoolean(2, obj.getSua());
        pst.setBoolean(3, obj.getXoa());
        pst.setInt(4, obj.getMaVaiTro());
        pst.setString(5, obj.getMaChucNang());
        return pst.executeUpdate();
    }

    public int delete(int maVaiTro, String maChucNang) throws Exception {
        String sql = "DELETE FROM vaiTro_chucNang WHERE maVaiTro = ? AND maChucNang = ?";
        PreparedStatement pst = JDBCUtil.getConnection().prepareStatement(sql);
        pst.setInt(1, maVaiTro);
        pst.setString(2, maChucNang);
        return pst.executeUpdate();
    }

    public ArrayList<VaiTro_ChucNangDTO> selectAll() throws Exception {
        String sql = "SELECT * FROM vaiTro_chucNang";
        PreparedStatement pst = JDBCUtil.getConnection().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        ArrayList<VaiTro_ChucNangDTO> list = new ArrayList<VaiTro_ChucNangDTO>();
        while (rs.next()) {
            VaiTro_ChucNangDTO obj = new VaiTro_ChucNangDTO();
            obj.setMaVaiTro(rs.getInt("maVaiTro"));
            obj.setMaChucNang(rs.getString("maChucNang"));
            obj.setThem(rs.getBoolean("them"));
            obj.setSua(rs.getBoolean("sua"));
            obj.setXoa(rs.getBoolean("xoa"));
            list.add(obj);
        }
        return list;
    }

    public ArrayList<VaiTro_ChucNangDTO> getChucNang(int maVaiTro) {
        ArrayList<VaiTro_ChucNangDTO> list = new ArrayList<VaiTro_ChucNangDTO>();
        try {
            String sql = "SELECT * FROM vaiTro_chucNang WHERE maVaiTro = ?";
            PreparedStatement pst = JDBCUtil.getConnection().prepareStatement(sql);
            pst.setInt(1, maVaiTro);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                VaiTro_ChucNangDTO obj = new VaiTro_ChucNangDTO();
                obj.setMaVaiTro(rs.getInt("maVaiTro"));
                obj.setMaChucNang(rs.getString("maChucNang"));
                obj.setThem(rs.getBoolean("them"));
                obj.setSua(rs.getBoolean("sua"));
                obj.setXoa(rs.getBoolean("xoa"));
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public VaiTro_ChucNangDTO selectByPK(int maVaiTro, String maChucNang) throws Exception {
        try {
            String sql = "SELECT * FROM vaiTro_chucNang WHERE maVaiTro = ? AND maChucNang = ?";
            PreparedStatement pst = JDBCUtil.getConnection().prepareStatement(sql);
            pst.setInt(1, maVaiTro);
            pst.setString(2, maChucNang);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                VaiTro_ChucNangDTO obj = new VaiTro_ChucNangDTO();
                obj.setMaVaiTro(rs.getInt("maVaiTro"));
                obj.setMaChucNang(rs.getString("maChucNang"));
                obj.setThem(rs.getBoolean("them"));
                obj.setSua(rs.getBoolean("sua"));
                obj.setXoa(rs.getBoolean("xoa"));
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
    
    public int xoaChucNang(int maVaiTro) {
        int rs = 0;
        try {
            String sql = "DELETE FROM vaiTro_chucNang WHERE maVaiTro = ?";
            PreparedStatement pst = JDBCUtil.getConnection().prepareStatement(sql);
            pst.setInt(1, maVaiTro);
            rs = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
