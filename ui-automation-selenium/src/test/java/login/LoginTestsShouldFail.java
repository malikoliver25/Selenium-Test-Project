package login;

import base.BaseTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import static org.testng.Assert.*;

/*
 * Test suite for verifying negative authentication scenarios (Negative Testing).
 * Validates that the application gracefully handles invalid inputs and prevents
 * unauthorized access by displaying appropriate error messages.
 */
public class LoginTestsShouldFail extends BaseTests {

    /*
     * Executes a negative login test using various invalid credential sets.
     * Validates that the system remains on the login page and provides user feedback.
     */
    @Test(dataProvider = "loginData", description = "Verifies error handling for various invalid login attempts")
    public void testInvalidLogin(String username, String password) {
        // Navigation: Accessing the Form Authentication module
        LoginPage loginPage = homePage.clickFormAuthentication();

        // Interaction: Entering data that should trigger a validation failure
        loginPage.setUsername(username);
        loginPage.setPassword(password);

        /* * Note: We stay on the LoginPage object because a failed login
         * does not trigger navigation to the SecureAreaPage.
         */
        loginPage.clickLoginButton();

        // Verification: Ensuring the UI displays a failure message containing 'invalid'
        String alertText = loginPage.getStatusAlertText();

        assertTrue(alertText.contains("invalid"),
                String.format("FAILED: Input [%s:%s] did not trigger the expected 'invalid' error. Found: %s",
                        username, password, alertText));
    }

    /*
     * Provides a comprehensive set of invalid login data, including:
     * - Empty and whitespace strings
     * - Case sensitivity variations
     * - Trailing spaces
     * - Basic security injection patterns (SQLi/XSS)
     * - Credential swapping
     * * @return Object array for Data-Driven Testing.
     */
    @DataProvider(name = "loginData")
    public Object[][] getInvalidData() {
        return new Object[][]{
                {"invalidUser", "invalidPass"},
                {"", "SuperSecretPassword!"},
                {" ", " "},
                {"tomsmith ", "SuperSecretPassword!"},
                {"TOMSMITH", "SUPERSECRETPASSWORD!"},
                {"admin'--", "password"},
                {"<script>alert(1)</script>", "p"},
                {"short", "pass"},
                {"!@#$%^&*()", "password"},
                {"tomsmith", "wrongPassword"},
                {"passwword", "tomsmith"}
        };
    }
}