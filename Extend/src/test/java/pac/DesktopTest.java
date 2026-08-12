package pac;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class DesktopTest extends BaseTest {

    @Test(dataProvider = "sortData")
    public void DesktopFlow(String sortOption)
            throws IOException {

        POMLocators pom =
                new POMLocators(driver);

        ExtentTest test =
                extent.createTest("Desktop Test - " + sortOption);

        pom.gotoMacPage();

        String heading =
                pom.getMacHeading();

        Assert.assertEquals(heading, "Mac");

        test.pass("Mac Page Verified");

        Reporter.log("Mac Page Verified", true);

        pom.addtoCart(sortOption);

        String actualMessage =
                pom.verifySuccessMessage();

        Assert.assertTrue(actualMessage.contains(
                "Success: You have added iMac to your shopping cart!"));

        test.pass("Product Added Successfully");

        String screenshotName =
                "Desktop_" +
                        sortOption.replaceAll("[^a-zA-Z0-9]", "_");

        test.addScreenCaptureFromPath(
                capturescreenshot(screenshotName));
    }

    @DataProvider(name = "sortData")
    public Object[][] sortData() {

        return new Object[][] {

                {"Name (A - Z)"},
                {"Name (Z - A)"}
        };
    }
}