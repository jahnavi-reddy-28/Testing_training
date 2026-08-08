package pac;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POMLocators {

    WebDriver driver;

    By desktops = By.linkText("Desktops");
    By mac = By.linkText("Mac (1)");
    By heading = By.tagName("h2");

    By sortDropdown = By.id("input-sort");

    By addToCart = By.xpath("//span[text()='Add to Cart']");

    By successMessage = By.cssSelector(".alert-success");

    By searchBox = By.name("search");

    By searchButton =
            By.cssSelector("button.btn.btn-default.btn-lg");

    By resultHeading =
            By.xpath("//div[@id='content']//h1");

    public POMLocators(WebDriver driver) {
        this.driver = driver;
    }

    public void goToMacPage() {

        driver.findElement(desktops).click();

        driver.findElement(mac).click();
    }

    public String getMacHeading() {

        return driver.findElement(heading).getText();
    }

    public void sortAndAddToCart(String sortOption) {

        Select select =
                new Select(driver.findElement(sortDropdown));

        select.selectByVisibleText(sortOption);

        driver.findElement(addToCart).click();
    }

    public String getSuccessMessage() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement msg =
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(successMessage));

        return msg.getText();
    }

    public void searchProduct(
            String firstSearch,
            String secondSearch) {

        WebElement search =
                driver.findElement(searchBox);

        search.sendKeys(firstSearch);

        search.clear();

        search.sendKeys(secondSearch);

        driver.findElement(searchButton).click();
    }

    public String getResultHeading() {

        return driver.findElement(resultHeading).getText();
    }
}
