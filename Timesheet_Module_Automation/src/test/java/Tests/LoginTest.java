package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.BaseTest;
import Listeners.ExtentTestNGListener;
import Pages.login_POM;
import Utilities.ExcelUtility;

@Listeners(ExtentTestNGListener.class)
public class LoginTest extends BaseTest {

    String projectpath = System.getProperty("user.dir");

    @BeforeMethod
    public void beforemethod() {

        setup();
    }

    @AfterMethod
    public void aftermethod() {

        teardown();
    }

    @DataProvider(name = "logindata")
    public Object[][] logindata() throws Exception {

        return ExcelUtility.getexceldata(
                projectpath + "/src/test/resources/data.xlsx",
                "Sheet1");
    }

    @Test(dataProvider = "logindata")
    public void logintest(String username, String password) {

        login_POM obj = new login_POM(driver);

        obj.enterusername(username);

        obj.enterpassword(password);

        obj.clicklogin();

        Assert.assertTrue(
                obj.dashboard().isDisplayed(),
                "Dashboard page not displayed");
    }
}