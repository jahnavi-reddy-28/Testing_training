package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.XMLObjectRepository;

/**
 * Common helpers shared by all module page objects (Timesheet, Attendance,
 * Reports, ProjectInfo). Mirrors the locator-parsing convention already used
 * in Pages.login_POM so the framework style stays consistent.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** Resolves a locator key from objectrepository.xml into a Selenium By. */
    protected By by(String locatorKey) {

        String locator = XMLObjectRepository.getlocator(locatorKey);

        if (locator == null) {
            throw new RuntimeException("Locator not found for key: " + locatorKey);
        }

        if (locator.startsWith("TODO_UPDATE:")) {
            System.out.println("WARNING: '" + locatorKey
                    + "' still has a placeholder locator. "
                    + "Update objectrepository.xml with the real locator from the app.");
            locator = locator.substring("TODO_UPDATE:".length());
        }

        String[] parts = locator.split(":", 2);
        String type = parts[0];
        String value = parts[1];

        switch (type.toLowerCase()) {
            case "name":
                return By.name(value);
            case "id":
                return By.id(value);
            case "xpath":
                return By.xpath(value);
            case "css":
                return By.cssSelector(value);
            default:
                throw new RuntimeException("Invalid locator type: " + type);
        }
    }

    protected WebElement waitVisible(String locatorKey) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by(locatorKey)));
    }

    protected WebElement waitClickable(String locatorKey) {
        return wait.until(ExpectedConditions.elementToBeClickable(by(locatorKey)));
    }

    protected void click(String locatorKey) {
        waitClickable(locatorKey).click();
    }

    protected void type(String locatorKey, String text) {
        WebElement el = waitVisible(locatorKey);
        el.clear();
        el.sendKeys(text);
    }

    protected boolean isDisplayed(String locatorKey) {
        try {
            return waitVisible(locatorKey).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Selects the given employee via the shared "Type for hints..." widget. */
    public void selectEmployee(String employeeName) {
        type("employee_name_input", employeeName);
        click("employee_hint_option");
    }
}
