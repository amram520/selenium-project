package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class jkh {
    WebDriver driver;
    @Test
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Daniel A\\IdeaProjects\\seleniumProject\\src\\test\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.imdb.com/");
        driver.manage().window().maximize();
    }
}