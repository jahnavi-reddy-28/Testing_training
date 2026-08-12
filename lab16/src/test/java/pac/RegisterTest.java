package pac;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    String projectpath = System.getProperty("user.dir");

    @Test(dataProvider = "UserDetails")
    public void fillRegisterForm(String fname,
                                 String lname,
                                 String email,
                                 String phone,
                                 String password,
                                 String confirmPassword) {

        System.out.println(fname + " " + lname);
    }

    @DataProvider(name = "UserDetails")
    public Object[][] getData() throws IOException {

        File file = new File(projectpath + "\\data.xlsx");

        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheetAt(0);

        // Debug Information
        System.out.println("Sheet Name = " + sheet.getSheetName());
        System.out.println("Last Row Number = " + sheet.getLastRowNum());

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            System.out.println("Row " + i + " = " + sheet.getRow(i));
        }

        int firstRow = -1;

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i) != null) {
                firstRow = i;
                break;
            }
        }

        if (firstRow == -1) {
            workbook.close();
            fis.close();
            throw new RuntimeException("Excel sheet is empty");
        }

        int cols = sheet.getRow(firstRow).getLastCellNum();
        int rows = sheet.getLastRowNum();

        System.out.println("First Data Row = " + firstRow);
        System.out.println("Total Columns = " + cols);
        System.out.println("Total Rows = " + rows);

        Object[][] data = new Object[rows - firstRow][cols];

        DataFormatter formatter = new DataFormatter();

        int index = 0;

        for (int i = firstRow + 1; i <= rows; i++) {

            if (sheet.getRow(i) == null) {
                System.out.println("Skipping Empty Row : " + i);
                continue;
            }

            for (int j = 0; j < cols; j++) {

                data[index][j] =
                        formatter.formatCellValue(
                                sheet.getRow(i).getCell(j));

                System.out.print(data[index][j] + " | ");
            }

            System.out.println();
            index++;
        }

        workbook.close();
        fis.close();

        return data;
    }
}