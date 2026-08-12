package pac;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class POMLocators {

    WebDriver driver;

    By desktops = By.xpath("//a[text()='Desktops']");
    By mac = By.xpath("//a[text()='Mac (1)']");
    By macHeading = By.tagName("h2");

    By sortDropdown = By.id("input-sort");

    By addToCart = By.xpath("//span[text()='Add to Cart']");

    By successMessage =
            By.cssSelector(".alert.alert-success");

    By searchBox = By.name("search");

    By searchButton =
            By.cssSelector("button.btn.btn-default.btn-lg");

    By resultHeading =
            By.xpath("//div[@id='content']//h1");

    public POMLocators(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoMacPage() {

        driver.findElement(desktops).click();

        Reporter.log("Clicked Desktop Menu", true);

        driver.findElement(mac).click();

        Reporter.log("Clicked Mac Option", true);
    }

    public String getMacHeading() {

        return driver.findElement(macHeading).getText();
    }

    public void addtoCart(String sortOption) {

        WebElement dropdown =
                driver.findElement(sortDropdown);

        Select select = new Select(dropdown);

        select.selectByVisibleText(sortOption);

        driver.findElement(addToCart).click();

        Reporter.log("Selected Sort : " + sortOption, true);
    }

    public String verifySuccessMessage() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement message =
                wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

        return message.getText();
    }

    public void searchText(String firstSearch,
                           String secondSearch) {

        WebElement search =
                driver.findElement(searchBox);

        search.sendKeys(firstSearch);

        search.clear();

        search.sendKeys(secondSearch);

        driver.findElement(searchButton).click();
    }

    public String resultPageHeading() {

        return driver.findElement(resultHeading).getText();
    }
}