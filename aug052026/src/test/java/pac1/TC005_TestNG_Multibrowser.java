package pac1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC005_TestNG_Multibrowser {

    WebDriver driver;

    @Test(dataProvider = "dp")
    public void f(String uname, String pword) {

        driver.findElement(By.name("username")).sendKeys(uname);
        driver.findElement(By.name("password")).sendKeys(pword);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        boolean dashboardPresent =
                driver.findElements(By.xpath("//h6[text()='Dashboard']")).size() > 0;

        if (uname.equals("Admin") && pword.equals("admin123")) {

            Assert.assertTrue(dashboardPresent,
                    "Valid login failed for: " + uname);

            System.out.println("Login Successful");

        } else {

            Assert.assertFalse(dashboardPresent,
                    "Invalid login unexpectedly succeeded");

            System.out.println("Invalid Login Verified");
        }
    }

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(String browser) {

        System.out.println("@BeforeMethod");

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        else if (browser.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();

        driver.get(
                "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    public void afterMethod() {

        System.out.println("@AfterMethod");

        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "dp")
    public Object[][] dp() {

        return new Object[][] {

                { "Admin", "admin123" },

                { "pooja", "welcome" },

                { "uma", "apple" }

        };
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("@AfterTest");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("@BeforeTest");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("@BeforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("@AfterSuite");
    }
}