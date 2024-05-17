package com.myjava.quanlycuahang.UTIL;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExcelWriter {
    public static void main(String[] args) throws IOException {

        // FileInputStream fis = new FileInputStream("testExcel.xlsx");
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a new Sheet
        workbook.createSheet("Demo");
        Sheet sheet = workbook.getSheetAt(0);
        // get existing sheet
        // Sheet sheet = workbook.getSheetAt(0);
        // or workbook.getSheet("Demo");

        int row_start = 0;
        // Create a Row
        Row headerRow = sheet.createRow(row_start++);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        String header[] = {"ID", "Username", "Password", "Statement", "Role"};

        // Creating cells
        for (int i = 0; i < header.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(headerStyle);
        }

        //Accounts data
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("1", "admin", "admin", "unlock", "admin"),
                Arrays.asList("2", "user", "user", "lock", "user"),
                Arrays.asList("3", "guest", "guest", "unlock", "guest")
        );

        for (int i = 0; i < accounts.size(); i++) {
            Row row = sheet.createRow(row_start++);
            for (int j = 0; j < accounts.get(i).size(); j++) {
                row.createCell(j).setCellValue(accounts.get(i).get(j));
            }
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("testExcel.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
}
