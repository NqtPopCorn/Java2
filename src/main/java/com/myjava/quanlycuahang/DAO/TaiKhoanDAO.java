package com.myjava.quanlycuahang.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myjava.quanlycuahang.DTO.TaiKhoanDTO;
import com.myjava.quanlycuahang.UTIL.JDBCUtil;

public class TaiKhoanDAO implements DataAccessObject<TaiKhoanDTO> {

    private static TaiKhoanDAO instance;
    private TaiKhoanDAO() {

    }
    public static TaiKhoanDAO getInstance(){
        if(instance == null) instance = new TaiKhoanDAO();
        return instance;
    }

    @Override
    public int insert(TaiKhoanDTO obj) throws SQLException{
        Connection con = null;
        String sql = "INSERT INTO `taikhoan`(`matk`,`tendangnhap`,`matkhau`,`trangthai`,`tenVaiTro`) VALUES (?,?,?,?,?)";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, obj.getMatk());
            pst.setString(2, obj.getUsername());
            pst.setString(3, obj.getMatkhau());
            pst.setInt(4, obj.getTrangthai());
            pst.setString(5, obj.getTenVaiTro());
            res = pst.executeUpdate();
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public int update(TaiKhoanDTO obj) throws SQLException{
        Connection con = null;
        
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            if (obj.getMatkhau().equals("")) {
                String sql = "UPDATE `taikhoan` SET `tendangnhap`=?,`trangthai`=?,`tenVaiTro`=? WHERE `matk`=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, obj.getUsername());
                pst.setInt(2, obj.getTrangthai());
                pst.setString(3, obj.getTenVaiTro());
                pst.setInt(4, obj.getMatk());
            }
            else {
                String sql = "UPDATE `taikhoan` SET `tendangnhap`=?, `matkhau`=?,`trangthai`=?,`tenVaiTro`=? WHERE `matk`=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, obj.getUsername());
                pst.setString(2, obj.getMatkhau());
                pst.setInt(3, obj.getTrangthai());
                pst.setString(4, obj.getTenVaiTro());
                pst.setInt(5, obj.getMatk());
            }
            res = pst.executeUpdate();
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }  
    
    @Override
    public int delete(String id) throws SQLException{
        Connection con = null;
        String sql = "UPDATE `taikhoan` SET `trangthai`='-1' where `matk` = ?";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            res = pst.executeUpdate();
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public ArrayList<TaiKhoanDTO> selectAll() throws SQLException{
        Connection con = null;
        String sql = "SELECT * FROM taikhoan WHERE trangthai = '0' OR trangthai = '1'";
        PreparedStatement pst = null;
        ArrayList<TaiKhoanDTO> res = new ArrayList<>();
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int matk = rs.getInt("matk");
                String username = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                String tenVaiTro = rs.getString("tenVaiTro");
                int trangthai = rs.getInt("trangthai");
                TaiKhoanDTO tk = new TaiKhoanDTO(matk, username, matkhau, tenVaiTro, trangthai);
                res.add(tk);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public TaiKhoanDTO selectById(Object pk) throws SQLException{
        Connection con = null;
        String sql = "SELECT * FROM taikhoan WHERE tendangnhap=?";
        PreparedStatement pst = null;
        TaiKhoanDTO res = null;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, pk.toString());
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int matk = rs.getInt("matk");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                String tenVaiTro = rs.getString("tenVaiTro");
                res = new TaiKhoanDTO(matk, tendangnhap, matkhau, tenVaiTro, trangthai);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    public int lock(int matk) {
        Connection con = null;
        String sql = "UPDATE `taikhoan` SET `trangthai`='0' where `matk` = ?";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, matk);
            res = pst.executeUpdate();
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }


    public int unlock(int matk) {
        Connection con = null;
        String sql = "UPDATE `taikhoan` SET `trangthai`='1' where `matk` = ?";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, matk);
            res = pst.executeUpdate();
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    //@Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'doanjava2' AND   TABLE_NAME   = 'taikhoan'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (rs2.next()) {
                result = rs2.getInt("AUTO_INCREMENT");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
