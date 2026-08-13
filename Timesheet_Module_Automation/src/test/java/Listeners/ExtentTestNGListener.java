package Listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Reports.ExtentManager;
import Utilities.DriverManager;
import Utilities.Screenshotutility;

public class ExtentTestNGListener implements ITestListener{
	
private static ExtentReports extent=ExtentManager.getinstance();

private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();

@Override
public void onTestStart(ITestResult result)
{
	ExtentTest extendtest=extent.createTest(result.getMethod().getMethodName());
	test.set(extendtest);
	test.get().info("Test Started");
	
	
}
@Override
public void onTestSuccess(ITestResult result)
{
	test.get().pass("Test Passed");
	
}

@Override
public void onTestFailure(ITestResult result)
{
	WebDriver driver=DriverManager.getDriver();
	try {
		test.get().fail("Test Failed").addScreenCaptureFromPath(Screenshotutility.capturescreenshot(driver,result.getMethod().getMethodName()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	test.get().fail(result.getThrowable());
	
	
}


@Override
public void onTestSkipped(ITestResult result) {

    if (test.get() != null) {

        test.get().skip("Test Skipped");

        if (result.getThrowable() != null) {

            test.get().skip(result.getThrowable());
        }
    }
}


@Override
public void onFinish(ITestContext context)
{
extent.flush();
	
	
}

}