package pac;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test(dataProvider = "RegisterData")
    public void fillRegisterForm(
            String fname,
            String lname,
            String emailid,
            String phno,
            String password,
            String cpassword) {

        POMPageFactory page = new POMPageFactory(driver);

        // Verify Home Page
        Assert.assertEquals(
                page.getHomePageTitle(),
                "Your Store"
        );

        Reporter.log("Home Page Verified", true);

        // Go to Register Page
        page.gotoRegisterPage();

        // Verify Register Page
        Assert.assertEquals(
                page.verifyRegisterPage(),
                "Register Account"
        );

        Reporter.log("Register Page Verified", true);

        // Fill Details
        page.fillPersonalDetails(
                fname,
                lname,
                emailid,
                phno
        );

        page.fillPasswords(
                password,
                cpassword
        );

        page.submittingForm();

        // Verify Result
        String result =
                page.getRegistrationResult();

        System.out.println(
                "Registration Result = " + result
        );

        if (result.contains(
                "Your Account Has Been Created!")) {

            Reporter.log(
                    "Account created successfully",
                    true
            );

            Assert.assertTrue(true);

        } else if (
                result.toLowerCase()
                        .contains("already registered")) {

            Reporter.log(
                    "Account already exists",
                    true
            );

            Assert.assertTrue(true);

        } else {

            Assert.fail(
                    "Unexpected Result : "
                            + result
            );
        }
    }

    @DataProvider(name = "RegisterData")
    public Object[][] registerData()
            throws IOException {

        System.out.println(
                "Reading registration data from Excel..."
        );

        String filePath =
                System.getProperty("user.dir")
                        + "\\data.xlsx";

        System.out.println(
                "Excel File Path = "
                        + filePath
        );

        File file = new File(filePath);

        if (!file.exists()) {

            throw new IOException(
                    "Excel file not found at : "
                            + file.getAbsolutePath()
            );
        }

        FileInputStream fis =
                new FileInputStream(file);

        XSSFWorkbook workbook =
                new XSSFWorkbook(fis);

        XSSFSheet sheet =
                workbook.getSheetAt(0);

        int rowCount =
                sheet.getPhysicalNumberOfRows();

        System.out.println(
                "Number of Rows = "
                        + rowCount
        );

        Object[][] data =
                new Object[rowCount - 1][6];

        DataFormatter formatter =
                new DataFormatter();

        for (int i = 1; i < rowCount; i++) {

            Row row =
                    sheet.getRow(i);

            data[i - 1][0] =
                    formatter.formatCellValue(
                            row.getCell(0));

            data[i - 1][1] =
                    formatter.formatCellValue(
                            row.getCell(1));

            data[i - 1][2] =
                    formatter.formatCellValue(
                            row.getCell(2));

            data[i - 1][3] =
                    formatter.formatCellValue(
                            row.getCell(3));

            data[i - 1][4] =
                    formatter.formatCellValue(
                            row.getCell(4));

            data[i - 1][5] =
                    formatter.formatCellValue(
                            row.getCell(5));
        }

        workbook.close();
        fis.close();

        return data;
    }
}