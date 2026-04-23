package utils;

import org.openqa.selenium.WebDriver;

/*
 * Utility class designed to handle browser-level window and navigation management.
 * By abstracting window switching and navigation into a utility, we maintain
 * a clean separation of concerns between Page Objects and browser controls.
 */
public class WindowManager {

    private WebDriver driver;

    public WindowManager(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Switches the driver focus to a specific browser tab or window based on its title.
     * This method iterates through all open handles to find a matching title,
     * which is a more robust approach than relying on hardcoded index positions.
     * @param tabTitle The exact title of the window/tab to switch to.
     */
    public void switchToTab(String tabTitle) {
        var windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);

            // If the current window's title matches the target, we have arrived
            if (tabTitle.equals(driver.getTitle())) {
                break;
            }
        }
    }

    // Navigates the browser one step back in the session history.
    public void goBack() { driver.navigate().back(); }

    // Navigates the browser one step forward in the session history. */
    public void goForward() { driver.navigate().forward(); }

    // Reloads the current page.
    public void refreshPage() { driver.navigate().refresh(); }

    /* Navigates the browser to a specific URL.
     * @param url The full destination string (e.g., https://example.com).
     */
    public void goTo(String url) { driver.navigate().to(url); }
}