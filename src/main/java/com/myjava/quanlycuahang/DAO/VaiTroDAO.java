package com.myjava.quanlycuahang.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.myjava.quanlycuahang.DTO.VaiTroDTO;
import com.myjava.quanlycuahang.UTIL.JDBCUtil;

public class VaiTroDAO implements DataAccessObject<VaiTroDTO>{
    
    private static VaiTroDAO instance;
    private VaiTroDAO() {

    }
    public static VaiTroDAO getInstance(){
        if(instance == null) instance = new VaiTroDAO();
        return instance;
    }

    @Override
    public int insert(VaiTroDTO vt) {
        Connection con = null;
        vt.setMaVaiTro(this.getAutoIncrement());
        PreparedStatement pst = null;
        int res = 0;
        try {
            //thuc hien sql la lay ra id cua vaitro vua them
            String sql = "INSERT INTO `vaitro`(`maVaiTro`,`tenVaiTro`) VALUES (?, ?);";
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, vt.getMaVaiTro());
            pst.setString(2, vt.getTenVaiTro());
            res = pst.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public int update(VaiTroDTO obj) {
        Connection con = null;
        String sql = "UPDATE `vaitro` SET `tenVaiTro`=? WHERE `maVaiTro`=?";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, obj.getTenVaiTro());
            pst.setInt(2, obj.getMaVaiTro());
            res = pst.executeUpdate();
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public int delete(String pk) {
        Connection con = null;
        String sql = "DELETE FROM `vaitro` WHERE `maVaiTro`=?";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, pk);
            res = pst.executeUpdate();
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public ArrayList<VaiTroDTO> selectAll() {
        Connection con = null;
        String sql = "SELECT * FROM vaitro";
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<VaiTroDTO> res = new ArrayList<>();
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                VaiTroDTO vt = new VaiTroDTO();
                vt.setMaVaiTro(rs.getInt("maVaiTro"));
                vt.setTenVaiTro(rs.getString("tenVaiTro"));
                res.add(vt);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public VaiTroDTO selectById(Object pk) {
        Connection con = null;
        String sql = "SELECT * FROM quyen WHERE maVaiTro=?";
        PreparedStatement pst = null;
        VaiTroDTO res = null;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, pk.toString());
            res = new VaiTroDTO();
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    public int getAutoIncrement() {
        Connection con = null;
        String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'doanjava2' AND TABLE_NAME = 'vaitro'";
        PreparedStatement pst = null;
        ResultSet rs = null;
        int res = -1;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                res = rs.getInt("AUTO_INCREMENT");
            }
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }
}
