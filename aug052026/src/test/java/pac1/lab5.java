package pac1;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class lab5  {

    public static void main(String[] args) throws InterruptedException {

     

        String url = "https://tutorialsninja.com/demo/";
        String expectedTitle = "Your Store";
        String expectedWarning = "Warning: You must agree to the Privacy Policy!";
        String expectedSuccessMessage = "Your Account Has Been Created!";

        String firstName = "Jahnavi";
        String lastName = "Reddy";

        String firstName33 = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG";
        String lastName33 = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG";

        String email = "jahnavi" + System.currentTimeMillis() + "@gmail.com";
        String telephone = "9876543210";
        String password = "Test123";

    
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(url);

        String actualTitle = driver.getTitle();

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("PASS - Title Verified");
        } else {
            System.out.println("FAIL - Title Verification Failed");
        }

        driver.findElement(By.linkText("My Account")).click();

        driver.findElement(By.linkText("Register")).click();

        String actualHeading =
                driver.findElement(By.tagName("h1")).getText();

        System.out.println("Heading Found : " + actualHeading);

        if (actualHeading.contains("Register")) {
            System.out.println("PASS - Register Page Opened");
        } else {
            System.out.println("FAIL - Register Page Not Opened");
        }

        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualWarning =
                driver.findElement(By.cssSelector(".alert-danger"))
                        .getText();

        if (actualWarning.contains(expectedWarning)) {
            System.out.println("PASS - Warning Message Verified");
        } else {
            System.out.println("FAIL - Warning Message Verification Failed");
        }


        driver.findElement(By.id("input-firstname"))
                .sendKeys(firstName33);

        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        try {

            String firstNameError =
                    driver.findElement(
                            By.xpath("//div[contains(text(),'First Name')]"))
                            .getText();

            System.out.println("First Name Validation : "
                    + firstNameError);

        } catch (Exception e) {

            System.out.println(
                    "33 Characters Accepted In First Name");
        }

        driver.findElement(By.id("input-firstname")).clear();

        driver.findElement(By.id("input-firstname"))
                .sendKeys(firstName);

        driver.findElement(By.id("input-lastname"))
                .sendKeys(lastName33);

        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        try {

            String lastNameError =
                    driver.findElement(
                            By.xpath("//div[contains(text(),'Last Name')]"))
                            .getText();

            System.out.println("Last Name Validation : "
                    + lastNameError);

        } catch (Exception e) {

            System.out.println(
                    "33 Characters Accepted In Last Name");
        }

        driver.findElement(By.id("input-lastname")).clear();

        driver.findElement(By.id("input-lastname"))
                .sendKeys(lastName);

        driver.findElement(By.id("input-email"))
                .sendKeys(email);

        driver.findElement(By.id("input-telephone"))
                .sendKeys(telephone);

      
        System.out.println(
                "Address fields are not available in TutorialsNinja Register Page");



        driver.findElement(By.id("input-password"))
                .sendKeys(password);

        driver.findElement(By.id("input-confirm"))
                .sendKeys(password);


        driver.findElement(
                By.xpath("//input[@name='newsletter' and @value='1']"))
                .click();

        driver.findElement(By.name("agree")).click();

        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String successMsg =
                driver.findElement(By.tagName("h1")).getText();

        if (successMsg.equals(expectedSuccessMessage)) {

            System.out.println(
                    "PASS - Account Created Successfully");

        } else {

            System.out.println(
                    "FAIL - Account Creation Failed");
        }

        driver.findElement(By.linkText("Continue")).click();

        try {

            driver.findElement(
                    By.linkText("View your order history"))
                    .click();

            System.out.println(
                    "PASS - Order History Page Opened");

        } catch (Exception e) {

            System.out.println(
                    "Order History Link Not Available");
        }

        Thread.sleep(3000);

        driver.quit();
    }
}