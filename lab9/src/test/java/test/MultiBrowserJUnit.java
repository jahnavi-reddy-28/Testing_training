package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultiBrowserJUnit {

    public void executeFlow(WebDriver driver) {

        driver.manage().window().maximize();

        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.linkText("Desktops")).click();

        driver.findElement(By.linkText("Mac (1)")).click();

        String macHeading =
                driver.findElement(By.tagName("h2")).getText();

        assertEquals("Mac", macHeading);

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

        assertTrue(
                successMsg.getText().contains(
                        "Success: You have added iMac to your shopping cart!"));

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

        assertEquals("Search - Monitors", resultHeading);

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