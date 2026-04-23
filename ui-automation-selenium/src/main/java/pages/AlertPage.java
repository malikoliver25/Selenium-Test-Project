package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * Page Object for the JavaScript Alerts page.
 * Handles three types of popups: basic alerts, confirmations, and prompts.
 */
public class AlertPage {

    private WebDriver driver;

    // Locators for the three buttons that trigger different JavaScript popups
    private By triggerAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    private By triggerConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    private By triggerPromptButton = By.xpath("//button[text()='Click for JS Prompt']");

    // Locator for the text area that updates after an alert is closed
    private By results = By.id("result");
    public AlertPage(WebDriver driver) {
        this.driver = driver;
    }

    // --- TRIGGER METHODS (Actions on the main page) ---

    public void triggerAlert() {
        driver.findElement(triggerAlertButton).click();
    }
    public void triggerConfirm() {
        driver.findElement(triggerConfirmButton).click();
    }
    public void triggerPrompt() {
        driver.findElement(triggerPromptButton).click();
    }

    // --- ALERT INTERACTION METHODS (Actions inside the popup) ---

    //Clicks the "OK" button on the active alert.
    public void alert_clickToAccept() {
        driver.switchTo().alert().accept();
    }

    //Clicks the "Cancel" button on a confirmation or prompt alert.
    public void alert_clickToDismiss() {
        driver.switchTo().alert().dismiss();
    }

    // Reads the message text displayed inside the alert popup.
    public String alert_getText() {
        return driver.switchTo().alert().getText();
    }

    // Types text into a Prompt alert's input field.
    public void alert_setInput(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    // --- RESULT METHODS (Reading from the main page again) ---
    // Retrieves the confirmation message displayed in the 'Result' section of the page.
    public String getResult(){
        return driver.findElement(results).getText();
    }
}