package com.itg.training.lancome.util;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelReader {
    private Sheet sheet;

    public ExcelReader(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException("❌ Error loading Excel file: " + e.getMessage());
        }
    }

    public String getCellData(int rowNum, int colNum) {
        try {
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            return cell.toString().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
