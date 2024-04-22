package com.example.exceltools;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelFile {

    public static void main(String[] args) {
        String filePath = "D:\\Program Files (x86)\\qqMessage\\191379744\\FileRecv\\3月2日招聘会岗位需求相关信息.xls";
        readExcelFile1(filePath);
    }

    private static void readExcelFile(String filePath) {
        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readExcelFile1(String filePath) {
        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);


            for (Row row : sheet) {
                Cell demandMajorCell = row.getCell(10);
                Cell recruitNumberCell = row.getCell(3);
                if (demandMajorCell != null && demandMajorCell.toString().contains("软件") ) {

                    String name = row.getCell(0).toString();
                    String position = row.getCell(10).toString();
                    String email = row.getCell(5).toString();
                    String number = row.getCell(11).toString();
                    String salary = row.getCell(13).toString();

                    System.out.println(String.format("%-5s%-35s%-25s%-10s%-10s", name, position, email, number, salary));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readExcelFile2(String filePath) {
        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell demandMajorCell = row.getCell(12);
                if (demandMajorCell != null) {
                    System.out.println(demandMajorCell.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
