package pac1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class lab1 {

    public static void main(String[] args) {

        WebDriverManager.edgedriver().setup();

        WebDriver driver = new EdgeDriver();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            driver.manage().window().maximize();

            driver.get("https://tutorialsninja.com/demo/");

            // Login
            driver.findElement(By.xpath("//span[text()='My Account']")).click();

            driver.findElement(By.linkText("Login")).click();

            driver.findElement(By.name("email"))
                    .sendKeys("jahnavireddy99592@gmail.com");

            driver.findElement(By.name("password"))
                    .sendKeys("Password@1234");

            driver.findElement(By.xpath("//input[@value='Login']")).click();

            // Verify Login
            if (driver.getPageSource()
                    .contains("Edit your account information")) {

                System.out.println("LOGIN SUCCESS");

            } else {

                System.out.println("LOGIN FAILED");
            }

            // Components -> Monitors
            driver.findElement(By.linkText("Components")).click();

            driver.findElement(By.linkText("Monitors (2)")).click();

            Select select =
                    new Select(driver.findElement(By.id("input-limit")));

            select.selectByVisibleText("25");

            // Open Apple Cinema Product
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("Apple Cinema 30\""))).click();

            // Quantity = 2
            WebElement qty =
                    driver.findElement(By.id("input-quantity"));

            qty.clear();
            qty.sendKeys("2");

            // Add to Wishlist
            driver.findElement(
                    By.xpath("//button[@data-original-title='Add to Wish List']"))
                    .click();

            try {

                WebElement wishListAlert =
                        wait.until(ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.xpath("//div[contains(@class,'alert')]")));

                String wishListMessage = wishListAlert.getText();

                System.out.println("Wishlist Message:");
                System.out.println(wishListMessage);

                if (wishListMessage.contains("wish list")) {
                    System.out.println("Wishlist Message Verified");
                } else {
                    System.out.println("Wishlist Message Not Verified");
                }

            } catch (Exception e) {

                System.out.println("Wishlist message not displayed");
            }

            // Search Mobile
            WebElement searchBox =
                    driver.findElement(By.name("search"));

            searchBox.clear();
            searchBox.sendKeys("Mobile");

            driver.findElement(
                    By.xpath("//button[@class='btn btn-default btn-lg']"))
                    .click();

            driver.findElement(By.id("description")).click();

            driver.findElement(By.id("button-search")).click();

            // Open HTC Touch HD
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("HTC Touch HD"))).click();

            String productName =
                    driver.findElement(
                            By.xpath("//div[@id='content']//h1"))
                            .getText();

            System.out.println("Opened Product: " + productName);

            // Quantity = 3
            WebElement mobileQty =
                    driver.findElement(By.id("input-quantity"));

            mobileQty.clear();
            mobileQty.sendKeys("3");

            // Add to Cart
            driver.findElement(By.id("button-cart")).click();

            try {

                WebElement cartAlert =
                        wait.until(ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.xpath("//div[contains(@class,'alert')]")));

                String cartMessage = cartAlert.getText();

                System.out.println("Cart Message:");
                System.out.println(cartMessage);

                if (cartMessage.contains("HTC Touch HD")) {
                    System.out.println("Cart Message Verified");
                } else {
                    System.out.println("Cart Message Not Verified");
                }

            } catch (Exception e) {

                System.out.println("No cart message displayed");
            }

            // Open Cart
            driver.findElement(
                    By.xpath("//div[@id='cart']//button"))
                    .click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[@class='text-left']//a")));

            String mobileName =
                    driver.findElement(
                            By.xpath("//td[@class='text-left']//a"))
                            .getText();

            if (mobileName.equals("HTC Touch HD")) {

                System.out.println("Mobile Name Matched");

            } else {

                System.out.println("Mobile Name Mismatched");
            }

            // Checkout
            try {

                driver.findElement(By.linkText("Checkout")).click();

                System.out.println("Checkout Clicked");

            } catch (Exception e) {

                System.out.println("Checkout not available");
            }

            // Logout
            driver.findElement(
                    By.xpath("//span[text()='My Account']"))
                    .click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("Logout"))).click();

            String logoutMessage =
                    wait.until(ExpectedConditions
                            .visibilityOfElementLocated(
                                    By.xpath("//div[@id='content']//h1")))
                            .getText();

            if (logoutMessage.equals("Account Logout")) {

                System.out.println("Account Logout Verified");

            } else {

                System.out.println("Account Logout Verification Failed");
            }

            driver.findElement(By.linkText("Continue")).click();

            System.out.println("TEST COMPLETED SUCCESSFULLY");

        } catch (Exception e) {

            System.out.println("TEST FAILED");
            e.printStackTrace();

        } finally {

            driver.quit();
        }
    }
}