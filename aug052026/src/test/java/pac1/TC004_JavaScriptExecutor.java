package pac1;
 
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
 
public class TC004_JavaScriptExecutor {
 
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://amazon.in");
		Thread.sleep(5000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("alert('Hello')");
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	}
 
}