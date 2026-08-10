package pac;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

        POMPageFactory page =
                new POMPageFactory(driver);

        // Verify Home Page
        String homeTitle = page.getHomePageTitle();

        System.out.println("Home Page Title = " + homeTitle);

        Assert.assertEquals(
                homeTitle,
                "Your Store",
                "Home page title mismatch"
        );

        Reporter.log("Home Page Verified", true);

        // Go to Register Page
        page.gotoRegisterPage();

        // Verify Register Page
        String registerTitle =
                page.verifyRegisterPage();

        System.out.println(
                "Register Page Title = " + registerTitle
        );

        Assert.assertEquals(
                registerTitle,
                "Register Account",
                "Register page title mismatch"
        );

        Reporter.log(
                "Register Page Verified",
                true
        );

        // Fill Personal Details
        page.fillPersonalDetails(
                fname,
                lname,
                emailid,
                phno
        );

        // Fill Password
        page.fillPasswords(
                password,
                cpassword
        );

        // Submit
        page.submittingForm();

        // Get Result
        String result =
                page.getRegistrationResult();

        System.out.println(
                "Registration Result = " + result
        );

        // Verify Result
        if (result.contains(
                "Your Account Has Been Created!")) {

            Reporter.log(
                    "Account created successfully",
                    true
            );

            Assert.assertTrue(true);

        } else if (
                result.toLowerCase().contains(
                        "already registered")) {

            Reporter.log(
                    "Account already exists - PASS",
                    true
            );

            Assert.assertTrue(true);

        } else {

            Assert.fail(
                    "Unexpected registration result: "
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

        InputStream inputStream =
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("data.xlsx");

        if (inputStream == null) {

            throw new IOException(
                    "data.xlsx not found in src/test/resources. "
                    + "Please place data.xlsx inside "
                    + "src/test/resources/"
            );
        }

        XSSFWorkbook workbook =
                new XSSFWorkbook(inputStream);

        XSSFSheet sheet =
                workbook.getSheetAt(0);

        int rowCount =
                sheet.getPhysicalNumberOfRows();

        System.out.println(
                "Number of rows = " + rowCount
        );

        if (rowCount <= 1) {

            workbook.close();

            throw new IOException(
                    "Excel file does not contain test data."
            );
        }

        Object[][] data =
                new Object[rowCount - 1][6];

        DataFormatter formatter =
                new DataFormatter();

        for (int i = 1; i < rowCount; i++) {

            Row row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            data[i - 1][0] =
                    formatter.formatCellValue(
                            row.getCell(0)
                    );

            data[i - 1][1] =
                    formatter.formatCellValue(
                            row.getCell(1)
                    );

            data[i - 1][2] =
                    formatter.formatCellValue(
                            row.getCell(2)
                    );

            data[i - 1][3] =
                    formatter.formatCellValue(
                            row.getCell(3)
                    );

            data[i - 1][4] =
                    formatter.formatCellValue(
                            row.getCell(4)
                    );

            data[i - 1][5] =
                    formatter.formatCellValue(
                            row.getCell(5)
                    );
        }

        workbook.close();
        inputStream.close();

        return data;
    }
}