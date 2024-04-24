package tests;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.IOException;
import java.sql.SQLException;


public class loginTest extends BaseTest{
    LoginPage loginPage;


    @Test
    @Parameters({"typeOfMoviesOptions"})
    public void checkin(String typeOfMoviesOptions) throws IOException, SQLException {
        loginPage = new LoginPage();
        loginPage.searchMovie("spiderman");
//        loginPage.chooseNavBar(typeOfMoviesOptions);
        loginPage.getDataBySql();
    }
}
