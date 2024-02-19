package tests;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class baTest {
    ATUTestRecorder recorder;
    public static WebDriver driver;
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
        recorder = new ATUTestRecorder("C:\\Users\\Daniel A\\Desktop\\seleniumProject\\src\\test\\resources\\reports","mewLogin"+d,false);

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "fireFox":
                driver= new FirefoxDriver();

        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://in.search.yahoo.com/?fr2=inr&guccounter=1");
        driver.manage().window().maximize();
        recorder.start();
    }

    public void loadProperties(){

    }


    @AfterMethod
    public void quit() throws ATUTestRecorderException, MalformedURLException {
        driver.quit();
        recorder.stop();
        System.out.println("this is after test");
//        reports.flush();
    }
}
