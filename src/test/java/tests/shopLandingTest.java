package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;

import static java.sql.DriverManager.getDriver;

public class shopLandingTest extends baTest {

@Test
    public void printHandling() throws AWTException {
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("setTimeout(function() { window.print(); }, 0);");
    Set<String>windows = driver.getWindowHandles();
    System.out.println(windows);
    Iterator<String>iterator = windows.iterator();
    String parent = iterator.next();
    String child = iterator.next();
    driver.switchTo().window(child);
    js.executeScript( "document.querySelector('print-preview-app').shadowRoot.querySelector('#sidebar').shadowRoot.querySelector('print-preview-button-strip').shadowRoot.querySelector('.cancel-button').click();");

    WebElement handle = driver.findElement(By.cssSelector("print-preview-app"));
    SearchContext shadowRootOne1 = expandRootElement (handle);
    WebElement nestedShadowHost1 = shadowRootOne1.findElement (By.cssSelector ("#sidebar"));
    SearchContext hadowRootOne1 = expandRootElement (nestedShadowHost1);
    WebElement nested2 = hadowRootOne1.findElement(By.cssSelector("print-preview-button-strip"));
    SearchContext shadowRootOne2 = expandRootElement (nested2);
    WebElement nested3 = shadowRootOne2.findElement(By.cssSelector(".cancel-button"));
    nested3.click();


    WebElement settings = driver.findElement(By.cssSelector("shop-app"));
    SearchContext shadowRootOne = expandRootElement (settings);
    WebElement nestedShadowHost = shadowRootOne.findElement (By.linkText ("Ladies T-Shirts"));
    wait.until(ExpectedConditions.elementToBeClickable(nestedShadowHost)).click();
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    WebElement nestesd = shadowRootOne.findElement(By.cssSelector("shop-list[name='list']"));
    SearchContext searchContext = expandRootElement(nestesd);
    WebElement element = searchContext.findElement(By.cssSelector("h1"));
    wait.until(ExpectedConditions.visibilityOf(element));
}

    public SearchContext expandRootElement (WebElement element) {
        SearchContext shadowRoot = (SearchContext) ((JavascriptExecutor) driver).executeScript (
                "return arguments[0].shadowRoot", element);
        return shadowRoot;
    }
}
