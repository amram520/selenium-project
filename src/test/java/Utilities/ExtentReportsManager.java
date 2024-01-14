package Utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportsManager {
   private static String path = System.getProperty("user.dir");
   static ExtentReports reports;

   public static ExtentReports createInstance() {
       ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path + "\\src\\test\\resources\\ExtentReports\\eli.html");
       reports = new ExtentReports();
       reports.setSystemInfo("Browser", "chrome");
       reports.setSystemInfo("environment", "test");
       htmlReporter.setAppendExisting(true);
       reports.attachReporter(htmlReporter);
       return reports;
    }

   public static String capture(WebDriver driver) throws IOException {
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
      Date date1 = new Date();
      String date = formatter.format(date1);
      File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File Dest = new File(path+"\\src\\test\\resources\\ExtentReports\\" + date+ ".png");
      String errflpath = Dest.getAbsolutePath();
      FileUtils.copyFile(scrFile,Dest);
      return date+".png";

   }

}
