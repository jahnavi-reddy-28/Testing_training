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

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        Reporter.log("Browser Launched", true);
    }

    @Test
    public void loginVerification() {

        Reporter.log("Opening Application", true);

        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.name("email"))
              .sendKeys("jahnavireddy99592@gmail.com");

        driver.findElement(By.name("password"))
              .sendKeys("Jahnavi@123");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        Reporter.log("Login button clicked", true);

        boolean status =
                driver.findElements(
                        By.linkText("Edit your account information"))
                        .size() > 0;

        Reporter.log("Verifying Login", true);

        Assert.assertTrue(status, "Login Failed");

        Reporter.log("Login Successful", true);
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

        Reporter.log("Browser Closed", true);
    }
}