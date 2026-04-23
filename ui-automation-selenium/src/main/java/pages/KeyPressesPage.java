package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/*
 * Page Object for the Key Presses interaction page.
 * This class demonstrates handling of keyboard events and real-time
 * DOM updates based on user input.
 */
public class KeyPressesPage {

    private WebDriver driver;
    private By inputField = By.id("target");
    private By resultText = By.id("result");

    public KeyPressesPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Sends a sequence of keys to the input field.
     * Can be used for standard strings or Selenium Keys (e.g., Keys.BACK_SPACE).
     * @param text The string or Key sequence to be simulated.
     */
    public void enterText(String text) {
        driver.findElement(inputField).sendKeys(text);
    }

    /*
     * Retrieves the confirmation text displayed after a key press.
     * * STRATEGY: Re-locates the element every time it is called. This prevents
     * StaleElementReferenceException, which often occurs during fast-paced
     * Data-Driven testing when the DOM refreshes the result node.
     * * @return The updated text content of the result element.
     */
    public String getResult() {
        // Explicit Wait ensures the driver synchronizes with the JavaScript
        // update before attempting to grab the text.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(resultText));

        return driver.findElement(resultText).getText();
    }
}