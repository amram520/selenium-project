package tests;


import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

//@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
//        MethodListener.class })
public class BaseTest {

    ATUTestRecorder recorder;
    public static WebDriver driver;
    public static WebDriverWait wait;
    Properties properties;
//    ExtentReports reports;
//
//    ExtentHtmlReporter htmlReporter;
//    public ExtentTest test;
//    ExtentSparkReporter extentSparkReporter;
//    {
//        System.setProperty("atu.reporter.config", "C:\\Users\\Daniel A\\Desktop\\seleniumProject\\src\\test\\resources\\cofigFiles\\atu.properties");
//    }


   static String path = System.getProperty("user.dir");

    @BeforeTest
    public void BTest() throws ATUTestRecorderException, IOException {
//        htmlReporter = new ExtentHtmlReporter(path+"\\src\\test\\resources\\ExtentReports\\eli.html");
//        reports = new ExtentReports();
//        reports.setSystemInfo("Browser", "chrome");
//        htmlReporter.setAppendExisting(true);
//        reports.attachReporter(htmlReporter);
//        test= reports.createTest("imdb tests");
        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream("C:\\Users\\Daniel A\\IdeaProjects\\seleniumProject\\src\\test" +
                    "\\resources\\cofigFiles\\config.properties");
            properties.load(ip);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String d = formatter.format(date);
        recorder = new ATUTestRecorder("C:\\Users\\Daniel A\\Desktop\\seleniumProject\\src\\test\\resources\\reports","mewLogin"+d,false);
        System.setProperty("webdriver.chrome.driver", path+"\\src\\test\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();

//        test.log(Status.INFO,"starting");
//        test.pass("navigate");
//        test.log(Status.PASS,"secc", MediaEntityBuilder.createScreenCaptureFromPath(capture(driver,d)).build());
        recorder.start();
    }
//        public static String capture(WebDriver driver, String screenShotName) throws IOException {
//        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File Dest = new File(path+"\\src\\test\\resources\\ExtentReports\\" + screenShotName+ ".png");
//            String errflpath = Dest.getAbsolutePath();
//            FileUtils.copyFile(scrFile,Dest);
//        System.out.println("####    "+errflpath);
//        return screenShotName+".png";
//
//    }



    @AfterTest
    public void quit() throws ATUTestRecorderException, MalformedURLException {
        driver.quit();
        recorder.stop();
        System.out.println("this is after test");
//        reports.flush();
    }
}
