package restAssured.Config;

import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;


public class Listeners implements ITestListener {



    public void onTestStart(ITestResult result) {
    }


    public void onTestSuccess(ITestResult result) {

    
    }

    public void onTestFailure(ITestResult result) {

        // Get the name of the failed test method
        String failedTestMethod = result.getName();

        // Log the test method name and status
        System.out.println("Test Method '" + failedTestMethod + "' has failed.");

        // Get the exception that caused the test failure
        Throwable exception = result.getThrowable();

        // Log the exception stack trace
        System.out.println("Exception Stack Trace:");
        exception.printStackTrace();
    }

    public void onTestSkipped(ITestResult result) {
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }


    public void onTestFailedWithTimeout(ITestResult result) {
    }


    public void onStart(ITestContext context) {
    }


    public void onFinish(ITestContext context) {
    }
}
