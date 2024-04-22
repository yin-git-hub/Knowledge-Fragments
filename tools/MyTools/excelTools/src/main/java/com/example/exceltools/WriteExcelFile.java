package com.example.exceltools;

import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelFile {

    public static void main(String[] args) {
        String filePath = "output.xlsx";
        String[][] data = {{"A1", "B1", "C1"}, {"A2", "B2", "C2"}};
        writeExcelFile(filePath, data);
    }

    private static void writeExcelFile(String filePath, String[][] data) {
        try (Workbook workbook = WorkbookFactory.create(true);
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet();

            for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
                Row row = sheet.createRow(rowIndex);
                for (int columnIndex = 0; columnIndex < data[rowIndex].length; columnIndex++) {
                    row.createCell(columnIndex).setCellValue(data[rowIndex][columnIndex]);
                }
            }

            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
