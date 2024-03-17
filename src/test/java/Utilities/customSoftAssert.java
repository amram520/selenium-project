package Utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import java.io.IOException;

import static listeners.TestNGListeners.test;

public class customSoftAssert extends SoftAssert {

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        try {
            test.log(Status.FAIL,"", MediaEntityBuilder.createScreenCaptureFromPath(ExtentReportsManager.capture(BaseTest.driver.get())).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
