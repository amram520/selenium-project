package pages;

import Utilities.customSoftAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import tests.BaseTest;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends BaseTest{

    WebElement element;

    @FindBy(xpath = "//input[@id = 'suggestion-search']")
    WebElement searchBox;
    @FindBy(id = "home_img")
    WebElement IMDBButton;
    @FindBy(xpath = "//h1[text()='Search \"spiderman\"']")
    WebElement title;
    @FindBy(id ="imdbHeader-navDrawerOpen")
    WebElement menuButton;
    @FindBy(xpath = "//span[@class = 'navlinkcat__targetWrapper']")
    List<WebElement>listOptionsNavBar;
    @FindBy(xpath = "//a[@class = 'ipc-list__item nav-link sc-gXfWyg ggHJvJ ipc-list__item--indent-one']")
    List<WebElement>listOptions;
    @FindBy(xpath = "//span[@class = 'ipc-rating-star ipc-rating-star--base ipc-rating-star--imdb ratingGroup--imdb-rating']")
    List<WebElement>ratesList;
    @FindBy(id = "home_img")
    WebElement homeButton;
    @FindBy(linkText = "Sign In")
    WebElement signInButton;

    public LoginPage(){
        PageFactory.initElements(driver.get(),this);
    }
     public void searchMovie(String movie) throws IOException {
        System.out.println("jjjjj");
         customSoftAssert softAssert = new customSoftAssert();
         softAssert.assertEquals(signInButton.isDisplayed(), true);
         softAssert.assertEquals(homeButton.isDisplayed(), true);

       wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(movie);
        searchBox.sendKeys(Keys.ENTER);
       wait.until(ExpectedConditions.visibilityOf(title));
         softAssert.assertAll();
     }

     public void chooseNavBar(String typeOptionOfMovies){
        Boolean isHigher = true;
        double rate1 =0, rate2 = 0;
         int i =0;
         Matcher m1, m2;
         Pattern p;
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        switch (typeOptionOfMovies){
            case "Release Calendar":
                listOptions.get(0).click();

                break;
            case "Top 250 Movies":
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                wait.until(ExpectedConditions.elementToBeClickable(listOptions.get(1))).click();
                wait.until(ExpectedConditions.visibilityOfAllElements(ratesList));
                while(isHigher && i < 98) {
                    for ( i = 0; i < 99; i++) {
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

    public void getDataBySql() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "124312");
        statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery("SELECT name FROM students WHERE id = 1");
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));

        }
    }
}
