package pac;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestNgListener implements ITestListener {

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        Object[] parameters =
                result.getParameters();

        if (parameters.length > 0) {

            testName += " - ";

            for (Object parameter : parameters) {
                testName += parameter + " ";
            }
        }

        ExtentTest extentTest =
                BaseTest.extent.createTest(testName);

        test.set(extentTest);

        test.get().info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");

        try {

            String screenshotName =
                    result.getMethod().getMethodName()
                            + "_"
                            + System.currentTimeMillis();

            String path =
                    BaseTest.capturescreenshot(screenshotName);

            test.get().addScreenCaptureFromPath(path);

        } catch (Exception e) {

            test.get().info("Screenshot not captured");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail("Test Failed");

        test.get().fail(result.getThrowable());

        try {

            String screenshotName =
                    result.getMethod().getMethodName()
                            + "_"
                            + System.currentTimeMillis();

            String path =
                    BaseTest.capturescreenshot(screenshotName);

            test.get().addScreenCaptureFromPath(path);

        } catch (Exception e) {

            test.get().info("Screenshot not captured");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}