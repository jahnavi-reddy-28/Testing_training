package pac1;
 
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
 
public class TC005_TestNG2 {
 
	WebDriverWait wait;
	WebDriver driver;
  @Test(dataProvider = "dp")
  public void f(String uname, String pword) {
 
	driver.findElement(By.name("username")).sendKeys(uname);
	driver.findElement(By.name("password")).sendKeys(pword);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	  
	  
	  
	  
  }
  @BeforeMethod
  public void beforeMethod() {
	  
	  System.out.println("@BeforeMethod");
	  
	  WebDriverManager.chromedriver().setup();
		
		 driver=new ChromeDriver();
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
  }
 
  @AfterMethod
  public void afterMethod() {
	  System.out.println("@AfterMethod");
	  driver.quit();
  }
 
 
  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "Admin", "admin123" },
      new Object[] { "pooja", "welcome" },
      new Object[] { "uma", "apple" },
    };
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("@BeforeClass");
  }
 
  @AfterClass
  public void afterClass() {
	  System.out.println("@AfterClass");
  }
  
 
  @BeforeTest
  public void beforeTest() {
	  System.out.println("@BeforeTest");
  }
 
  @AfterTest
  public void afterTest() {
	  System.out.println("@AfterTest");
  }
 
  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("@BeforeSuite");
  }
 
  @AfterSuite
  public void afterSuite() {
	  System.out.println("@AfterSuite");
  }
 
}
 