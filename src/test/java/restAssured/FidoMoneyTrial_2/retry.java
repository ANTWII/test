package restAssured.FidoMoneyTrial_2;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public  class retry implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
