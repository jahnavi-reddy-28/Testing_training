package pac;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Lab3JUnit {

    WebDriver driver;

    @Before
    public void setUp() {

        driver = new EdgeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/");
    }

    @Test
    public void loginTest() {

        driver.findElement(By.xpath("//span[text()='My Account']")).click();

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.name("email"))
              .sendKeys("your_email");

        driver.findElement(By.name("password"))
              .sendKeys("your_password");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        assertTrue(driver.getPageSource()
                .contains("Edit your account information"));
    }

    @After
    public void tearDown() {

        driver.quit();
    }
}