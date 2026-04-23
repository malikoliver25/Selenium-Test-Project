package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * Page Object representing the Multiple Windows interaction page.
 * This class facilitates testing of the WebDriver's ability to switch
 * contexts between different browser tabs or windows.
 */
public class MultipleWindowsPage {

    private WebDriver driver;
    private By clickHereLink = By.linkText("Click Here");

    public MultipleWindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    //Triggers the opening of a new browser window/tab by clicking the "Click Here" link.
    public void clickHere() {
        driver.findElement(clickHereLink).click();
    }
}