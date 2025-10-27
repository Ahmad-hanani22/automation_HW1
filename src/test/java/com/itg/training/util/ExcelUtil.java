package com.itg.training.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    private Workbook workbook;

    // ✅ فتح ملف الإكسل
    public ExcelUtil(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            System.out.println("❌ Failed to load Excel file: " + e.getMessage());
        }
    }

    // ✅ قراءة قيمة خلية معينة
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.out.println("⚠️ Sheet not found: " + sheetName);
            return "";
        }

        Row row = sheet.getRow(rowNum);
        if (row == null) return "";

        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        return cell.toString().trim();
    }

    // ✅ حساب عدد الصفوف داخل شيت معين
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return (sheet != null) ? sheet.getLastRowNum() : 0;
    }

    // ✅ إغلاق الملف بعد الانتهاء
    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            System.out.println("⚠️ Failed to close Excel file: " + e.getMessage());
        }
    }
}
