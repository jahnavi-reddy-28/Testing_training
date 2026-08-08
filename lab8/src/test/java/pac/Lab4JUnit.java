package pac;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Lab4JUnit {

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
    public void logoutTest() {

        driver.findElement(By.xpath("//span[text()='My Account']")).click();

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.name("email"))
              .sendKeys("your_email");

        driver.findElement(By.name("password"))
              .sendKeys("your_password");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        driver.findElement(By.xpath("//span[text()='My Account']")).click();

        driver.findElement(By.linkText("Logout")).click();

        String actual =
                driver.findElement(
                        By.xpath("//div[@id='content']//h1"))
                        .getText();

        assertEquals("Account Logout", actual);
    }

    @After
    public void tearDown() {

        driver.quit();
    }
}