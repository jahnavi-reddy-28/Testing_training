package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.XMLObjectRepository;

public class login_POM {

    WebDriver driver;

    String user = XMLObjectRepository.getlocator("usern");
    String pass = XMLObjectRepository.getlocator("passw");
    String button1 = XMLObjectRepository.getlocator("loginbutton");
    String dashboard = XMLObjectRepository.getlocator("dashboard");

    By username = getBy(user);
    By password = getBy(pass);
    By button = getBy(button1);
    By Dashboard1 = getBy(dashboard);

  
    public login_POM(WebDriver driver) {
        this.driver = driver;
    }


    private By getBy(String locator) {

        String[] parts = locator.split(":", 2);

        String type = parts[0];
        String value = parts[1];

        if (type.equalsIgnoreCase("name")) {

            return By.name(value);

        } else if (type.equalsIgnoreCase("id")) {

            return By.id(value);

        } else if (type.equalsIgnoreCase("xpath")) {

            return By.xpath(value);

        } else if (type.equalsIgnoreCase("css")) {

            return By.cssSelector(value);

        } else {

            throw new RuntimeException("Invalid Locator type: " + type);
        }
    }

    // Enter username
    public void enterusername(String uname) {

        driver.findElement(username).sendKeys(uname);
    }

    // Enter password
    public void enterpassword(String pword) {

        driver.findElement(password).sendKeys(pword);
    }

    // Click Login
    public void clicklogin() {

        driver.findElement(button).click();
    }

    // Get Dashboard element
    public WebElement dashboard() {

        return driver.findElement(Dashboard1);
    }
}