package com.myjava.quanlycuahang.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myjava.quanlycuahang.DTO.ChucNangDTO;
import com.myjava.quanlycuahang.UTIL.JDBCUtil;

public class ChucNangDAO implements DataAccessObject<ChucNangDTO> {
    private static ChucNangDAO instance;
    private ChucNangDAO() {
    }
    public static ChucNangDAO getInstance(){
        if(instance == null) instance = new ChucNangDAO();
        return instance;
    }
    
    @Override
    public int insert(ChucNangDTO chucNang) throws SQLException {
        Connection con = null;
        String sql = "INSERT INTO `chucnang`(`maChucNang`,`tenChucNang`) VALUES (?,?)";
        PreparedStatement pst = null;
        int res = 0;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, chucNang.getMaChucNang());
            pst.setString(2, chucNang.getTenChucNang());
            res = pst.executeUpdate();
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }
    
    @Override
    public int update(ChucNangDTO chucNang) throws SQLException {
        int res = 0;
        String query = "UPDATE chucnang SET tenChucNang = ? WHERE maChucNang = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(query);
            pst.setString(1, chucNang.getTenChucNang());
            pst.setString(2, chucNang.getMaChucNang());
            res = pst.executeUpdate();
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return res;
    }
    
    @Override
    public int delete(String id) throws SQLException {
        Connection con = null;
        String sql = "DELETE FROM `chucnang` WHERE `maChucNang` = ?";
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
    public ArrayList<ChucNangDTO> selectAll() throws SQLException {
        ArrayList<ChucNangDTO> chucNangList = new ArrayList<>();
        String query = "SELECT * FROM chucnang";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(query);
            try (ResultSet resultSet = pst.executeQuery()) {
                while (resultSet.next()) {
                    ChucNangDTO chucNang = new ChucNangDTO();
                    chucNang.setMaChucNang(resultSet.getString("maChucNang"));
                    chucNang.setTenChucNang(resultSet.getString("tenChucNang"));
                    chucNangList.add(chucNang);
                }
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return chucNangList;
    }
    
    @Override
    public ChucNangDTO selectById(Object id) throws SQLException {
        ChucNangDTO chucNang = null;
        String query = "SELECT * FROM chucnang WHERE id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(query);
            pst.setString(1, id.toString());
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    chucNang = new ChucNangDTO();
                    chucNang.setMaChucNang(resultSet.getString("maChucNang"));
                    chucNang.setTenChucNang(resultSet.getString("tenChucNang"));
                }
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return chucNang;
    }
}
