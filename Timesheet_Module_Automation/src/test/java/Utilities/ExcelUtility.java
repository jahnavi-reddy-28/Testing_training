package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public static Object[][] getexceldata(String filepath, String sheetname)
            throws InvalidFormatException, IOException {

        File file = new File(filepath);

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet(sheetname);

        int rowcount = sheet.getPhysicalNumberOfRows();

        int colcount = sheet.getRow(0).getPhysicalNumberOfCells();

        System.out.println("Rows : " + rowcount);
        System.out.println("Columns : " + colcount);

        Object[][] data = new Object[rowcount][colcount];

        DataFormatter formatter = new DataFormatter();

        for (int i = 0; i < rowcount; i++) {

            for (int j = 0; j < colcount; j++) {

                data[i][j] =
                        formatter.formatCellValue(
                                sheet.getRow(i).getCell(j));

            }
        }

        workbook.close();

        return data;
    }
}