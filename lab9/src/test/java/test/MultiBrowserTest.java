package test;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultiBrowserTest {

    public void executeFlow(WebDriver driver) {

        driver.manage().window().maximize();

        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.linkText("Desktops")).click();
        Reporter.log("Clicked on Desktops", true);

        driver.findElement(By.linkText("Mac (1)")).click();

        String macHeading =
                driver.findElement(By.tagName("h2")).getText();

        Assert.assertEquals(macHeading, "Mac");
        Reporter.log("Mac Heading Verified", true);

        WebElement sortDropdown =
                driver.findElement(By.id("input-sort"));

        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Name (A - Z)");

        driver.findElement(By.xpath("//span[text()='Add to Cart']"))
                .click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement successMsg =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert-success")));

        Assert.assertTrue(
                successMsg.getText().contains(
                        "Success: You have added iMac to your shopping cart!"));

        Reporter.log("Add To Cart Verified", true);

        WebElement search =
                driver.findElement(By.name("search"));

        search.sendKeys("Mobile");
        search.clear();
        search.sendKeys("Monitors");

        driver.findElement(
                By.cssSelector("button.btn.btn-default.btn-lg"))
                .click();

        String resultHeading =
                driver.findElement(
                        By.xpath("//div[@id='content']//h1"))
                        .getText();

        Assert.assertEquals(resultHeading, "Search - Monitors");

        Reporter.log("Search Result Verified", true);

        driver.quit();
    }

    @Test
    public void chromeTest() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        executeFlow(driver);
    }

    @Test
    public void edgeTest() {

        WebDriverManager.edgedriver().setup();

        WebDriver driver = new EdgeDriver();

        executeFlow(driver);
    }
}