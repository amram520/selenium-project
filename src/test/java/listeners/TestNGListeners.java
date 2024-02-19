package listeners;

import Utilities.ExtentReportsManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;
import tests.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TestNGListeners extends BaseTest implements ITestListener {
    int a = 1;
ExtentReports reportsManager = ExtentReportsManager.createInstance();
ExtentTest test;
    ExtentTest test1;

@Override
    public void onTestStart(ITestResult result) {

        System.out.println("the test is" +a+ " "+ result.getName());
        test1 = reportsManager.createTest(Feature.class,"imdb"+a).assignCategory("regression");
       test = test1.createNode(result.getName()+a+" Test");
       a++;


}
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("the test" + result.getName() + "succeed");
        try {
            test.log(Status.PASS,"pass", MediaEntityBuilder.createScreenCaptureFromPath(ExtentReportsManager.capture(driver.get())).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
    System.out.println("the test" + result.getName() + "failed");
    String exceptionMsg = Arrays.toString(result.getThrowable().getStackTrace());
        try {
            test.log(Status.FAIL,exceptionMsg, MediaEntityBuilder.createScreenCaptureFromPath(ExtentReportsManager.capture(driver.get())).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            test.log(Status.SKIP,result.getName()+" is skip", MediaEntityBuilder.createScreenCaptureFromPath(ExtentReportsManager.capture(driver.get())).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        reportsManager.flush();

    }
}
