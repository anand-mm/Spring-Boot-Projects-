package com.codeforyou.apachepoi;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class Controller {

    public void excel(HttpServletResponse response) throws Exception {
        FileInputStream templateFile = new FileInputStream("tests-example.xls");
        Workbook workbook = WorkbookFactory.create(templateFile);
        templateFile.close();

        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Your Data");

        FileOutputStream outputFile = new FileOutputStream("modified_template.xls");
        workbook.write(outputFile);
        outputFile.close();
        workbook.close();

    }

}
