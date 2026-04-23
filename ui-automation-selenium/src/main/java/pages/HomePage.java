package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * The HomePage class serves as the main entry point for the application under test.
 * It utilizes the Page Object Model (POM) to centralize navigation and expose
 * the various functional areas of the site as returnable Page Objects.
 */
public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Navigates to the Form Authentication page.
     * @return A new instance of the LoginPage.
     */
    public LoginPage clickFormAuthentication() {
        clickLink("Form Authentication");
        return new LoginPage(driver);
    }

    /*
     * Navigates to the Dropdown management page.
     * @return A new instance of the DropDownPage.
     */
    public DropDownPage clickDropDown() {
        clickLink("Dropdown");
        return new DropDownPage(driver);
    }

    /*
     * Navigates to the Hovers page to test mouse-over interactions.
     * @return A new instance of the HoverPage.
     */
    public HoverPage clickHovers() {
        clickLink("Hovers");
        return new HoverPage(driver);
    }

    /*
     * Navigates to the Key Presses page for keyboard interaction testing.
     * @return A new instance of the KeyPressesPage.
     */
    public KeyPressesPage clickKeyPresses() {
        clickLink("Key Presses");
        return new KeyPressesPage(driver);
    }

    /*
     * Navigates to the JavaScript Alerts page.
     * @return A new instance of the AlertPage.
     */
    public AlertPage clickJavaScriptAlerts() {
        clickLink("JavaScript Alerts");
        return new AlertPage(driver);
    }

    /*
     * Navigates to the Multiple Windows page for tab/window management testing.
     * @return A new instance of the MultipleWindowsPage.
     */
    public MultipleWindowsPage clickMultipleWindows() {
        clickLink("Multiple Windows");
        return new MultipleWindowsPage(driver);
    }

    /*
     * Internal helper method to handle link clicks via linkText.
     * Centralizing this reduces boilerplate code across the class.
     * @param linkText The exact text of the link to be clicked.
     */
    private void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }
}