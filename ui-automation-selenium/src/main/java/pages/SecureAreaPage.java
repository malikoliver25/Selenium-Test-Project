package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/*
 * Page Object for the Secure Area, accessed after a successful login.
 * This class serves as a verification point for authentication flows,
 * housing the logic to confirm system-level success messages.
 */
public class SecureAreaPage {

    private WebDriver driver;
    private By statusAlert = By.id("flash");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Retrieves the text from the status alert (flash message).
     * * STRATEGY: Implements an Explicit Wait to synchronize with the page load.
     * This ensures the alert element is not only present in the DOM but also
     * visible to the user before attempting to interact, mitigating 'NoSuchElement'
     * exceptions on slower environments.
     * * @return The text content of the success or error banner.
     */
    public String getAlertText() {
        // Create a wait object with a 5-second timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Wait until the flash message is fully rendered and visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(statusAlert));

        return driver.findElement(statusAlert).getText();
    }
}