package pac;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogoutTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        Reporter.log("Browser Launched", true);
    }

    @Test
    public void logoutVerification() throws InterruptedException {

        Reporter.log("Opening Application", true);

        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.name("email"))
                .sendKeys("jahnavireddy99592@gmail.com");

        driver.findElement(By.name("password"))
                .sendKeys("Jahnavi@123");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        Thread.sleep(3000);

        System.out.println(driver.getCurrentUrl());

        boolean loginStatus =
                driver.getPageSource()
                        .contains("Edit your account information");

        System.out.println("Login Status = " + loginStatus);

        Assert.assertTrue(loginStatus, "Login Failed");

        Reporter.log("Login Successful", true);

        driver.findElement(By.xpath("//span[text()='My Account']")).click();

        driver.findElement(By.linkText("Logout")).click();

        String actualText =
                driver.findElement(
                        By.xpath("//div[@id='content']//h1"))
                        .getText();

        Reporter.log("Verifying Logout", true);

        Assert.assertEquals(actualText, "Account Logout");

        Reporter.log("Logout Successful", true);
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

        Reporter.log("Browser Closed", true);
    }
}