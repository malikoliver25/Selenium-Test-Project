package alerts;

import base.BaseTests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AlertsTests extends BaseTests {

    //Tests the basic JavaScript Alert (OK button only).
    @Test
    public void closeAlert() {
        var alertsPage = homePage.clickJavaScriptAlerts();
        alertsPage.triggerAlert();
        alertsPage.alert_clickToAccept();

        assertEquals(alertsPage.getResult(), "You successfully clicked an alert", "Result is incorrect");
    }

     // Tests the JavaScript Confirm popup by reading its text and dismissing it.
    @Test
    public void getTextFromAlert() {
        var alertsPage = homePage.clickJavaScriptAlerts();
        alertsPage.triggerConfirm();

        String alertText = alertsPage.alert_getText();
        alertsPage.alert_clickToDismiss();

        assertEquals(alertText, "I am a JS Confirm", "Result is incorrect");
    }

     // Tests the JavaScript Prompt by sending input text and verifying the result on the page.
    @Test
    public void setInputInAlert() {
        var alertsPage = homePage.clickJavaScriptAlerts();
        alertsPage.triggerPrompt();

        String alertText = "Input Test";

        // Step 1: Send the text to the alert input field
        alertsPage.alert_setInput(alertText);

        // Step 2: Accept the alert to submit the text
        alertsPage.alert_clickToAccept();

        // Step 3: Verify the page updated the 'Result' section with our input
        assertEquals(alertsPage.getResult(), "You entered: " + alertText, "Result is incorrect");
    }
}