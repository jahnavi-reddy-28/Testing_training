package pac1;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class lab6 {
    @Test
    public void ecommerceTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {

            driver.manage().window().maximize();
            driver.get("https://tutorialsninja.com/demo/");

            // Login
            driver.findElement(By.linkText("My Account")).click();
            driver.findElement(By.linkText("Login")).click();

            driver.findElement(By.id("input-email"))
                    .sendKeys("your_email@example.com");

            driver.findElement(By.id("input-password"))
                    .sendKeys("your_password");

            driver.findElement(By.xpath("//input[@value='Login']"))
                    .click();

            // Components -> Monitors
            driver.findElement(By.linkText("Components")).click();
            driver.findElement(By.linkText("Monitors (2)")).click();

            // Show 25
            WebElement show =
                    driver.findElement(By.id("input-limit"));

            org.openqa.selenium.support.ui.Select select =
                    new org.openqa.selenium.support.ui.Select(show);

            select.selectByVisibleText("25");

            Thread.sleep(2000);

            // Add first item to cart
            driver.findElement(By.xpath("(//span[text()='Add to Cart'])[1]"))
                    .click();

            // Open Apple Cinema product
            driver.findElement(By.linkText("Apple Cinema 30\""))
                    .click();

            // Specification tab
            driver.findElement(By.linkText("Specification"))
                    .click();

            // Add to wishlist
            driver.findElement(By.xpath("//button[@data-original-title='Add to Wish List']"))
                    .click();

            String wishMsg =
                    driver.findElement(By.cssSelector(".alert-success"))
                            .getText();

            Assert.assertTrue(
                    wishMsg.contains("Success: You have added Apple Cinema 30")
            );

            // Search Mobile
            WebElement search =
                    driver.findElement(By.name("search"));

            search.clear();
            search.sendKeys("Mobile");

            driver.findElement(By.cssSelector("#search button"))
                    .click();

            driver.findElement(By.name("description"))
                    .click();

            driver.findElement(By.id("button-search"))
                    .click();

            // HTC Touch HD
            driver.findElement(By.linkText("HTC Touch HD"))
                    .click();

            WebElement qty =
                    driver.findElement(By.id("input-quantity"));

            qty.clear();
            qty.sendKeys("3");

            driver.findElement(By.id("button-cart"))
                    .click();

            String cartMsg =
                    driver.findElement(By.cssSelector(".alert-success"))
                            .getText();

            Assert.assertTrue(
                    cartMsg.contains("Success: You have added HTC Touch HD")
            );

            // View Cart
            driver.findElement(By.xpath("//span[text()='Shopping Cart']"))
                    .click();

            String productName =
                    driver.findElement(
                            By.linkText("HTC Touch HD"))
                            .getText();

            Assert.assertEquals(productName, "HTC Touch HD");

            // Checkout
            driver.findElement(By.linkText("Checkout"))
                    .click();

            // Logout
            driver.findElement(By.linkText("My Account"))
                    .click();

            driver.findElement(By.linkText("Logout"))
                    .click();

            String heading =
                    driver.findElement(By.xpath("//h1"))
                            .getText();

            Assert.assertEquals(heading, "Account Logout");

            driver.findElement(By.linkText("Continue"))
                    .click();

            System.out.println("Test Passed");

        } finally {
            driver.quit();
        }
    }
}



