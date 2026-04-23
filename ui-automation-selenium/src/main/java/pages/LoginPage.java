package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/*
 * Page Object representing the Authentication (Login) page.
 * Provides methods for user credential entry and status verification.
 */
public class LoginPage {

    private final WebDriver driver;
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    // Locators defined using By strategy for reusability and maintenance
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button i");
    private final By statusAlert = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Inputs the provided string into the username field.
    public void setUsername(String username) {
        log.info("Entering username: {}", username);
        driver.findElement(usernameField).sendKeys(username);
    }

    // Inputs the provided string into the password field.
    public void setPassword(String password) {
        log.info("Entering password...");
        driver.findElement(passwordField).sendKeys(password);
    }

    /*
     * Executes the login submission.
     * @return A new instance of the SecureAreaPage upon successful navigation.
     */
    public SecureAreaPage clickLoginButton() {
        log.info("Clicking Login button");
        driver.findElement(loginButton).click();
        return new SecureAreaPage(driver);
    }

    /*
     * Retrieves the text from the status banner (flash message).
     * Implements Explicit Wait to ensure element visibility before interaction.
     * This mitigates NoSuchElementException in asynchronous environments.
     * @return The text content of the status alert.
     */
    public String getStatusAlertText() {
        log.info("Waiting for status alert to appear...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(statusAlert)).getText();
    }
}