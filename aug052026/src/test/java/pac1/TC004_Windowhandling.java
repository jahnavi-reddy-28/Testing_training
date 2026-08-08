package pac1;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC004_Windowhandling {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            // Open URL
            driver.get("https://letcode.in/window");
            driver.manage().window().maximize();

            // Wait for page to load
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Get Parent Window Handle
            String parentWindow = driver.getWindowHandle();
            System.out.println("Parent Window Handle: " + parentWindow);

            // Click on Multi Window Button
            driver.findElement(By.id("multi")).click();

            // Wait for child windows to open
            Thread.sleep(3000);

            // Get all window handles
            Set<String> allWindows = driver.getWindowHandles();

            System.out.println("Total Windows Opened: " + allWindows.size());

            // Switch to child windows
            for (String window : allWindows) {

                if (!window.equals(parentWindow)) {

                    driver.switchTo().window(window);

                    System.out.println("--------------------------------");
                    System.out.println("Window Handle: " + window);
                    System.out.println("Window Title : " + driver.getTitle());
                    System.out.println("Current URL  : " + driver.getCurrentUrl());
                }
            }

            // Switch back to Parent Window
            driver.switchTo().window(parentWindow);

            System.out.println("--------------------------------");
            System.out.println("Back to Parent Window");
            System.out.println("Parent Title: " + driver.getTitle());

        } catch (Exception e) {

            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();

        } finally {

            // Close all browser windows
            driver.quit();
        }
    }
}