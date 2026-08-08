package pac1;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class lab7 {

    @Test
    public void verifySimpleAlert() {

        WebDriver driver = new EdgeDriver();

        driver.manage().window().maximize();

        // Launch URL
        driver.get("https://letcode.in/alert/");

        // Click Simple Alert button
        driver.findElement(By.xpath("//button[text()='Simple Alert']")).click();

        // Switch to Alert
        Alert alert = driver.switchTo().alert();

        // Get Alert Text
        String actualText = alert.getText();
        System.out.println("Alert Text : " + actualText);

        // Verify Alert Text
        Assert.assertEquals(actualText, "Hey! Welcome to LetCode");

        // Click OK
        alert.accept();

        driver.quit();
    }
}