package tests;


import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.github.agomezmoron.multimedia.recorder.VideoRecorder;
import com.github.agomezmoron.multimedia.recorder.configuration.VideoRecorderConfiguration;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    ATUTestRecorder recorder;
    WebDriver driver;
    WebDriverWait wait;
    Properties properties;

    ExtentReports report;
    ExtentTest test;


    @BeforeTest
    public void BTest() throws ATUTestRecorderException {

            report = new ExtentReports(System.getProperty("user.dir")+"\\src\\test\\resources\\ExtentReports");
            test = report.startTest("ExtentDemo");
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
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path+"\\src\\test\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
        recorder.start();
    }
    

    @AfterTest
    public void quit() throws ATUTestRecorderException, MalformedURLException {
        driver.quit();
        recorder.stop();
    }
}
