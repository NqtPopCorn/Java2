package com.myjava.quanlysinhvien.MODEL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.myjava.quanlysinhvien.JDBCUtil;
import com.myjava.quanlysinhvien.CONTROLLER.TaiKhoanDTO;

public class TaiKhoanDAO implements DataAccessObject<TaiKhoanDTO> {

    private static TaiKhoanDAO instance;
    private TaiKhoanDAO() {

    }
    public static TaiKhoanDAO getInstance(){
        if(instance == null) instance = new TaiKhoanDAO();
        return instance;
    }

    @Override
    public int insert(TaiKhoanDTO obj) {
        Connection con = null;
        String sql = "INSERT INTO `taikhoan`(`tendangnhap`,`matkhau`,`trangthai`,`manhomquyen`) VALUES (?,?,?,?,?)";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(2, obj.getUsername());
            pst.setString(3, obj.getMatkhau());
            pst.setInt(5, obj.getTrangthai());
            pst.setInt(4, obj.getManhomquyen());
            res = pst.executeUpdate();
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public int update(TaiKhoanDTO obj) {
        Connection con = null;
        String sql = "UPDATE `taikhoan` SET `matkhau`=?,`trangthai`=?,`manhomquyen`=? WHERE `tendangnhap`=?";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, obj.getMatkhau());
            pst.setInt(2, obj.getTrangthai());
            pst.setInt(3, obj.getManhomquyen());
            pst.setString(4, obj.getUsername());
            res = pst.executeUpdate();
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }  
    
    @Override
    public int delete(String tendangnhap) {
        Connection con = null;
        String sql = "UPDATE `taikhoan` SET `trangthai`='-1' where `tendangnhap` = ?";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, tendangnhap);
            res = pst.executeUpdate();
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public ArrayList<TaiKhoanDTO> selectAll() {
        Connection con = null;
        String sql = "SELECT * FROM taikhoan WHERE trangthai = '0' OR trangthai = '1'";
        PreparedStatement pst = null;
        ArrayList<TaiKhoanDTO> res = new ArrayList<>();
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String username = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                TaiKhoanDTO tk = new TaiKhoanDTO(username, matkhau, manhomquyen, trangthai);
                res.add(tk);
            }
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public TaiKhoanDTO selectById(String t) {
        Connection con = null;
        String sql = "SELECT * FROM taikhoan WHERE tendangnhap=?";
        PreparedStatement pst = null;
        TaiKhoanDTO res = null;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                res = new TaiKhoanDTO(tendangnhap, matkhau, manhomquyen, trangthai);
            }
        } catch(Exception e) {

        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }

    @Override
    public int getAutoIncrement() {
        //chua hieu
        return -1;
    }
}
