package pac;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void registerUser() throws IOException {

        PropertyReader pr =
                new PropertyReader();

        driver.get(
                pr.getProperty("url"));

        Assert.assertEquals(
                driver.getTitle(),
                "Your Store");

        System.out.println(
                "Title Verified");

        driver.findElement(
                By.xpath(
                        pr.getProperty("myaccount")))
                .click();

        driver.findElement(
                By.xpath(
                        pr.getProperty("register")))
                .click();

        driver.findElement(
                By.id(
                        pr.getProperty("firstname")))
                .sendKeys("Jahnavi");

        driver.findElement(
                By.id(
                        pr.getProperty("lastname")))
                .sendKeys("Reddy");

        driver.findElement(
                By.id(
                        pr.getProperty("email")))
                .sendKeys(
                        "jahnavi"
                        + System.currentTimeMillis()
                        + "@gmail.com");

        driver.findElement(
                By.id(
                        pr.getProperty("telephone")))
                .sendKeys("9876543210");

        driver.findElement(
                By.id(
                        pr.getProperty("password")))
                .sendKeys("Test@123");

        driver.findElement(
                By.id(
                        pr.getProperty("confirm")))
                .sendKeys("Test@123");

        driver.findElement(
                By.name(
                        pr.getProperty("policy")))
                .click();

        driver.findElement(
                By.xpath(
                        pr.getProperty("continuebtn")))
                .click();

        String message =
                driver.findElement(
                        By.xpath("//div[@id='content']/h1"))
                        .getText();

        System.out.println(
                "Result = "
                + message);

        Assert.assertEquals(
                message,
                "Your Account Has Been Created!");
    }
}