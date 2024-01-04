package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.util.List;

public class searchPage {

WebDriver driver;
WebDriverWait wait;

@FindBy(xpath = "//li[@class = 'ipc-metadata-list-summary-item ipc-metadata-list-summary-item--click find-result-item find-title-result']")
List<WebElement> tab;

public searchPage(WebDriver driver, WebDriverWait wait){
    this.driver = driver;
    this.wait = wait;
    PageFactory.initElements(this.driver,this);
}
public void choose(){


}
}
