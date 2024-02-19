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
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

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
    public static ThreadLocal<WebDriver> driver= new ThreadLocal<>();
    public static WebDriverWait wait;
    Properties properties;


   static String path = System.getProperty("user.dir");

    @BeforeMethod
    @Parameters({"browser"})
    public void BTest(@Optional("chrome") String browser) throws ATUTestRecorderException, IOException {
        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream("C:\\Users\\Daniel A\\Desktop\\seleniumProject\\src\\test\\resources\\logs\\config.properties");
            properties.load(ip);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String d = formatter.format(date);
        recorder = new ATUTestRecorder("C:\\Users\\Daniel A\\Desktop\\seleniumProject\\src\\test\\resources\\videos","mewLogin"+d,false);
//        System.setProperty("webdriver.chrome.driver", path+"\\src\\test\\resources\\driver\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(ThreadGuard.protect(new ChromeDriver()));
                break;
            case "fireFox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(ThreadGuard.protect(new FirefoxDriver()));

        }
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        driver.get().get(properties.getProperty("url"));
        driver.get().manage().window().maximize();
        recorder.start();
    }

public void loadProperties(){

}


    @AfterMethod
    public void quit() throws ATUTestRecorderException, MalformedURLException {
        driver.get().quit();
        driver.remove();
        recorder.stop();
        System.out.println("this is after test");
    }
}
