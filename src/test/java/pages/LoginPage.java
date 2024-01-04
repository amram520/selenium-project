package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    WebElement element;

    @FindBy(xpath = "//input[@id = 'suggestion-search']")
    WebElement searchBox;
    @FindBy(id = "home_img")
    WebElement IMDBButton;
    @FindBy(id ="imdbHeader-navDrawerOpen")
    WebElement navBar;
    @FindBy(xpath = "//span[@class = 'navlinkcat__targetWrapper']")
    List<WebElement>listOptionsNavBar;
    @FindBy(xpath = "//a[@class = 'ipc-list__item nav-link sc-gXfWyg ggHJvJ ipc-list__item--indent-one']")
    List<WebElement>listOptions;

    @FindBy(xpath = "//span[@class = 'ipc-rating-star ipc-rating-star--base ipc-rating-star--imdb sc-9ab53865-1 iXEijC ratingGroup--imdb-rating']")
    List<WebElement>ratesList;

    public LoginPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
     public void searchMovie(String movie){
       wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(movie);
        searchBox.sendKeys(Keys.ENTER);
     }

     public void chooseNavBar(String typeOptionOfMovies){
        Boolean isHigher = true;
        double rate1 =0, rate2 = 0;
         int i =0;
         Matcher m1, m2;
         Pattern p;
        wait.until(ExpectedConditions.elementToBeClickable(navBar)).click();
//        listOptionsNavBar.get(0).click();
        switch (typeOptionOfMovies){
            case "Release Calendar":
                listOptions.get(0).click();

                break;
            case "Top 250 Movies":
                wait.until(ExpectedConditions.elementToBeClickable(listOptions.get(1))).click();
                wait.until(ExpectedConditions.visibilityOfAllElements(ratesList));
                while(isHigher && i < 249) {
                    for ( i = 0; i < ratesList.size() - 1; i++) {
                         p = Pattern.compile("\\d+\\.\\d+");
                         m1 = p.matcher(ratesList.get(i).getAttribute("aria-label"));
                         m2 = p.matcher(ratesList.get(i+1).getAttribute("aria-label"));
                        if (m1.find()&& m2.find()) {
                            rate1 = Double.parseDouble(m1.group());
                            rate2 = Double.parseDouble(m2.group());
                        }
                        if(rate1<rate2){
                            isHigher = false;
                        }
                        else {
                            isHigher = true;
                        }

                    }
                }
                Assert.assertEquals(isHigher, true);
                break;
        }





     }


}
