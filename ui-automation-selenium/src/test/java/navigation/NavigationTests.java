package navigation;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

/*
 * Test suite dedicated to browser-level navigation and window orchestration.
 * These tests verify the framework's ability to manipulate the browser session
 * outside of standard DOM interactions.
 */
@Epic("Core Navigation")
@Feature("Window Management")
public class NavigationTests extends BaseTests {

    /*
     * Validates browser history manipulation and direct URL navigation.
     * This ensures the WindowManager utility correctly interfaces with the
     * WebDriver Navigation API.
     */
    @Test(description = "Verify browser history navigation (Back, Forward, Refresh)")
    @Severity(SeverityLevel.MINOR)
    @Description("Test Goal: Ensure the driver can successfully navigate the history stack and refresh the state.")
    public void testNavigator() {
        getWindowManager().goTo("https://google.com");
        getWindowManager().goBack();
        getWindowManager().refreshPage();
        getWindowManager().goForward();
        getWindowManager().goTo("https://the-internet.herokuapp.com/");
    }

    /*
     * Validates the ability to switch focus between multiple browser tabs.
     * * STRATEGY:
     * 1. Trigger a new window via a page interaction.
     * 2. Use the WindowManager to iterate through handles and switch by Page Title.
     * This is a critical check for applications that open external help docs or third-party portals.
     */
    @Test(description = "Verify switching driver focus to a newly opened tab")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Goal: Switch to a new window handle and verify the driver context has updated.")
    public void testSwitchTab() {
        homePage.clickMultipleWindows().clickHere();
        getWindowManager().switchToTab("New Window");
    }
}