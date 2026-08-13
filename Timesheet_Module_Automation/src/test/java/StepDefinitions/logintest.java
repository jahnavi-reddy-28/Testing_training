package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class logintest {

    WebDriver driver;
    WebDriverWait wait;

    @Given("Open the OrangeHRM login page")
    public void open_the_orange_hrm_login_page() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get(
            "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        );

        // Wait until username field is displayed
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.name("username")
            )
        );
    }

    @When("Enter the username {string}")
    public void enter_the_username(String username) {

        WebElement usernameField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.name("username")
            )
        );

        usernameField.clear();
        usernameField.sendKeys(username);
    }

    @And("Enter the password {string}")
    public void enter_the_password(String password) {

        WebElement passwordField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.name("password")
            )
        );

        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @And("Click on login button")
    public void click_on_login_button() {

        WebElement loginButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")
            )
        );

        loginButton.click();
    }

    @Then("Dashboard page should be displayed")
    public void dashboard_page_should_be_displayed() {

        WebElement dashboard = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Dashboard']")
            )
        );

        Assert.assertTrue(
            dashboard.isDisplayed(),
            "Dashboard is not displayed"
        );

        System.out.println("Login successful - Dashboard displayed");
    }
}