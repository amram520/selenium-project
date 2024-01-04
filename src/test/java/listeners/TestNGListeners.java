package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {

@Override
    public void onTestStart(ITestResult result) {
        System.out.println("the test is" + result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("the test" + result.getName() + "succeed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("the test" + result.getName() + "failed");
    }
    @Override
    public void onTestSkipped(ITestResult result) {
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    @Override

    public  void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }
    @Override
    public void onStart(ITestContext context) {
        System.out.println("now it start");
    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("the test is finish");

    }
}
