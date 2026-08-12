package pac;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected static WebDriver driver;
    protected static ExtentReports extent;
    static String projectpath = System.getProperty("user.dir");

    @BeforeSuite
    public void setup() {

        new File(projectpath + "\\Reports").mkdirs();
        new File(projectpath + "\\Screenshots").mkdirs();

        Reporter.log("Launching Browser", true);

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ExtentSparkReporter reporter =
                new ExtentSparkReporter(projectpath + "\\Reports\\ExtentReport.html");

        reporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Pooja");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Environment", "QA");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://tutorialsninja.com/demo/");
        System.out.println("@BeforeMethod");
    }

    @AfterSuite
    public void tearDown() {

        if (extent != null) {
            extent.flush();
        }

        if (driver != null) {
            driver.quit();
        }
    }

    public static String capturescreenshot(String testname) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        String spath =
                projectpath + "\\Screenshots\\" + testname + ".png";

        File destination = new File(spath);

        FileUtils.copyFile(source, destination);

        return spath;
    }
}