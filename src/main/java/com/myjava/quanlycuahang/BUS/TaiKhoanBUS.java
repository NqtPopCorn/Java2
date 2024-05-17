package com.myjava.quanlycuahang.BUS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myjava.quanlycuahang.DAO.TaiKhoanDAO;
import com.myjava.quanlycuahang.DTO.TaiKhoanDTO;
import com.myjava.quanlycuahang.DTO.VaiTro_ChucNangDTO;
import com.myjava.quanlycuahang.GUI.MainFrame;

public class TaiKhoanBUS implements BUSInterface<TaiKhoanDTO>{
    final public static String MATK = "matk";
    final public static String TENDANGNHAP = "tendangnhap";
    final public static String TENVAITRO = "tenVaiTro";


    private ArrayList<TaiKhoanDTO> listTaiKhoan;

    private static TaiKhoanBUS instance;

    public static TaiKhoanBUS getInstance() {
        if (instance == null) {
            instance = new TaiKhoanBUS();
        }
        return instance;
    }

    private TaiKhoanBUS() {
        try {
            listTaiKhoan = TaiKhoanDAO.getInstance().selectAll();
        }
        catch (SQLException e) {
            listTaiKhoan = new ArrayList<>();
            System.err.println("Khong the lay du lieu tu CSDL\n" + e.getMessage());
        }
        
    }

    @Override
    public ArrayList<TaiKhoanDTO> getList() {
        return listTaiKhoan;
    }

    @Override
    public int them(TaiKhoanDTO taiKhoan) throws SQLException {
        //kiem tra quyen QLTK cua vai tro
        VaiTro_ChucNangDTO vtcn = VaiTro_ChucNangBUS.getInstance().tim(MainFrame.vaitro.getMaVaiTro(), ChucNangBUS.QLTK);
        if (vtcn == null || vtcn.getThem() == false) {
            throw new SQLException("Bạn không có quyền thêm tài khoản");
        }
        int rs = 0;
        //kiem tra ma duy nhat
        for (TaiKhoanDTO tk : listTaiKhoan) {
            if (tk.getUsername().equals(taiKhoan.getUsername())) {
                System.out.println("Tên đăng nhập đã tồn tại");
                throw new SQLException("Tên đăng nhập đã tồn tại");
            }
        }
        try {
            rs = TaiKhoanDAO.getInstance().insert(taiKhoan);
            listTaiKhoan.add(taiKhoan);
            return rs;
        }
        catch(SQLException e) {
            throw e;
        }
    }

    @Override
    public int sua(TaiKhoanDTO taiKhoan) throws SQLException{
        //kt quyen
        VaiTro_ChucNangDTO vtcn = VaiTro_ChucNangBUS.getInstance().tim(MainFrame.vaitro.getMaVaiTro(), ChucNangBUS.QLTK);
        if (vtcn == null || vtcn.getSua() == false) {
            throw new SQLException("Bạn không có quyền sửa tài khoản");
        }
        try {
            int rs = TaiKhoanDAO.getInstance().update(taiKhoan);
            for (TaiKhoanDTO tk : listTaiKhoan) {
                if (tk.getMatk() == taiKhoan.getMatk()) {
                    tk.setUsername(taiKhoan.getUsername());
                    tk.setMatkhau(taiKhoan.getMatkhau());
                    tk.setTrangthai(taiKhoan.getTrangthai());
                    tk.setTenVaiTro(taiKhoan.getTenVaiTro());
                    break;
                }
            }
            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public int xoa(Object matk) throws SQLException{
        //kt quyen
        VaiTro_ChucNangDTO vtcn = VaiTro_ChucNangBUS.getInstance().tim(MainFrame.vaitro.getMaVaiTro(), ChucNangBUS.QLTK);
        if (vtcn == null || vtcn.getXoa() == false) {
            throw new SQLException("Bạn không có quyền xóa tài khoản");
        }
        try {
            int rs = TaiKhoanDAO.getInstance().delete(matk.toString());
            for (TaiKhoanDTO tk : listTaiKhoan) {
                if (tk.getMatk() == Integer.parseInt(matk.toString())){
                    listTaiKhoan.remove(tk);
                    break;
                }
            }
            return rs;
        } catch (SQLException e) {
            throw e;
        }
        
    }

    @Override
    public ArrayList<TaiKhoanDTO> loc(String fieldName, String value) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        for (TaiKhoanDTO tk: listTaiKhoan) {
            if (fieldName.equals(Constant.TaiKhoan.TENDANGNHAP) && tk.getUsername().contains(value)) {
                result.add(tk);
            } else if (fieldName.equals(Constant.TaiKhoan.TRANGTHAI) && tk.getTrangthai() == Integer.parseInt(value)) {
                result.add(tk);
            } else if (fieldName.equals(Constant.TaiKhoan.TENVAITRO) && tk.getTenVaiTro().contains(value)) {
                result.add(tk);
            }
        }
        return result;
    }

    @Override
    public TaiKhoanDTO tim(String fieldName, String value) {
        for (TaiKhoanDTO tk : listTaiKhoan) {
            if (fieldName.equals(Constant.TaiKhoan.MATK) && tk.getMatk() == Integer.parseInt(value)) {
                return tk;
            } else if (fieldName.equals(Constant.TaiKhoan.TENDANGNHAP) && tk.getUsername().equals(value)) {
                return tk;
            } else if(fieldName.equals(Constant.TaiKhoan.TENVAITRO) && tk.getTenVaiTro().equals(value)) {
                return tk;
            }
        }
        return null;
    }

    public void toggle_lock(int index) throws Exception {
        //kiem quyen sua
        VaiTro_ChucNangDTO vtcn = VaiTro_ChucNangBUS.getInstance().tim(MainFrame.vaitro.getMaVaiTro(), ChucNangBUS.QLTK);
        if (vtcn == null || vtcn.getSua() == false) {
            throw new Exception("Bạn không có quyền sửa tài khoản");
        }
        TaiKhoanDTO tk = listTaiKhoan.get(index);
        if (tk.getTrangthai() == 1) {
            TaiKhoanDAO.getInstance().lock(tk.getMatk());
            tk.setTrangthai(0);
        }
        else if (tk.getTrangthai() == 0) {
            TaiKhoanDAO.getInstance().unlock(tk.getMatk());
            tk.setTrangthai(1);
        }
    }

    public int xuatFileExcel(String excelPath) throws Exception{
        Workbook workbook = new XSSFWorkbook();
        //kiem tra xem sheet "TaiKhoan" co ton tai chua
        Sheet sheet;
        if (workbook.getSheet("TaiKhoan") == null)
            sheet = workbook.createSheet("TaiKhoan");
        else
            sheet = workbook.getSheet("TaiKhoan");
        int row_start = 0;
        // Create a Row
        Row headerRow = sheet.createRow(row_start++);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        String header[] = {"Mã tài khoản", "Tên đăng nhập", "Mật khẩu", "Trạng thái", "Vai trò"};
        int width[] = {5000, 5000, 10000, 5000, 5000};

        // Creating header cells
        for (int i = 0; i < header.length; i++) {
            sheet.setColumnWidth(i, width[i]);
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(headerStyle);
        }

        //Accounts data
        ArrayList<ArrayList<String>> accounts = new ArrayList<>();
        for (TaiKhoanDTO tk : listTaiKhoan) {
            ArrayList<String> account = new ArrayList<>();
            account.add(tk.getMatk()+"");
            account.add(tk.getUsername());
            account.add(tk.getMatkhau());
            account.add(tk.getTrangthai() == 1? "Mở" : "Khóa");
            account.add(tk.getTenVaiTro());
            accounts.add(account);
        }
        // Creating data cells
        for (int i = 0; i < accounts.size(); i++) {
            Row row = sheet.createRow(row_start++);
            for (int j = 0; j < accounts.get(i).size(); j++) {
                row.createCell(j).setCellValue(accounts.get(i).get(j));
            }
        }

        // Ghi Workbook vào tệp
        try {
            FileOutputStream fileOut = new FileOutputStream(excelPath);
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    public int nhapTuFileExcel(String path) throws Exception{
        //kiem quyen them
        VaiTro_ChucNangDTO vtcn = VaiTro_ChucNangBUS.getInstance().tim(MainFrame.vaitro.getMaVaiTro(), ChucNangBUS.QLTK);
        if (vtcn == null || vtcn.getThem() == false) {
            System.out.println("Bạn không có quyền thêm tài khoản");
            return -1;
        }
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("TaiKhoan");
            int row_start = 1;
            while (sheet.getRow(row_start) != null) {
                Row row = sheet.getRow(row_start);
                TaiKhoanDTO tk = new TaiKhoanDTO();
                tk.setMatk((int)row.getCell(0).getNumericCellValue());
                tk.setUsername(row.getCell(1).getStringCellValue());
                tk.setMatkhau(row.getCell(2).getStringCellValue());
                tk.setTrangthai(row.getCell(3).getStringCellValue().equals("Mở")? 1 : 0);
                tk.setTenVaiTro(row.getCell(4).getStringCellValue());
                //kiem tra ma duy nhat
                ArrayList<TaiKhoanDTO> foundMatk = loc(Constant.TaiKhoan.MATK, row.getCell(0).getStringCellValue());
                ArrayList<TaiKhoanDTO> foundUsername = loc(Constant.TaiKhoan.TENDANGNHAP, row.getCell(1).getStringCellValue());
                if (foundMatk.size() == 0 && foundUsername.size() == 0){
                    them(tk);
                }
                row_start++;
            }
            workbook.close();
            fis.close();
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }   

    public int getAutoIncrement() {
        return TaiKhoanDAO.getInstance().getAutoIncrement();
    }
}
