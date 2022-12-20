package com.tutorialNinja.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 50;
    public static final int PAGE_LOAD_WAIT_TIME = 30;
    public static String GenerateEmailTimestamp() {
        Date date = new Date();
        String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
        return "biswaji"+timeStamp+"@gmail.com";
    }

    public static Object[][] excelReader(String sheetName) throws IOException {
        File excelFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\ExcelData\\TestData.xlsx");
        FileInputStream fisExcel = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data= new Object[rowCount][colCount];

        for(int i=0; i<rowCount; i++) {
            XSSFRow row = sheet.getRow(i + 1);

            for(int j=0; j<colCount; j++) {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();

                switch (cellType) {

                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;

                    case NUMERIC:
                        data[i][j] = Integer.toString((int)cell.getNumericCellValue());
                        break;
                }
            }
        }

        return data;
    }
}
