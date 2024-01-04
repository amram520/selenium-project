package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
//@Listeners(listeners.TestNGListeners.class)
public class loginTest extends BaseTest{
    LoginPage loginPage;


    @Test
    @Parameters({"typeOfMoviesOptions"})
    public void checkin(String typeOfMoviesOptions){
        loginPage = new LoginPage(driver,wait);
//        loginPage.searchMovie("spiderman");
        loginPage.chooseNavBar(typeOfMoviesOptions);
    }
}
