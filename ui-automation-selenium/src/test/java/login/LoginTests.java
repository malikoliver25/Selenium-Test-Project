package login;

import base.BaseTests;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/*
 * Test suite for validating authentication flows.
 * Extends BaseTests to leverage the thread-safe WebDriver lifecycle and Allure reporting.
 */
@Epic("User Management")
@Feature("Login Functionality")
public class LoginTests extends BaseTests {

    /*
     * Positive testing scenario for valid user credentials.
     * This test validates the end-to-end 'Happy Path' of the login module.
     * * STRATEGY:
     * 1. Navigate to Form Authentication.
     * 2. Input valid credentials via Page Objects.
     * 3. Verify the redirection to the SecureAreaPage by asserting the success banner.
     */
    @Test(description = "Validates the successful login flow with correct credentials")
    @Severity(SeverityLevel.BLOCKER) // Highlights this as a critical business flow
    @Description("Test Goal: Verify that a user is redirected to the secure area after entering valid credentials.")
    @Story("User logs in with valid username and password")
    public void testSuccessfulLogin() {
        // Navigating through the application using Fluent Interface design
        var loginPage = homePage.clickFormAuthentication();

        // Inputting credentials (these could be moved to a properties file or data provider in the future)
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");

        // Clicking login transitions the state to the SecureAreaPage
        var secureAreaPage = loginPage.clickLoginButton();

        // Verification: Assert that the UI displays the correct success message
        String alertText = secureAreaPage.getAlertText();
        assertTrue(alertText.contains("You logged into a secure area!"),
                "Assertion Failed: The success alert message was not found or was incorrect.");
    }
}